package com.serverless.letspoll.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.letspoll.commons.DatabaseAccessUtils
import com.serverless.letspoll.models.ApiGatewayResponse
import com.serverless.letspoll.models.Poll
import com.serverless.letspoll.models.generated.tables.records.PollRecord
import com.serverless.letspoll.models.responses.PollDetailsResponse
import com.serverless.letspoll.models.responses.PollResponseStatistics
import com.serverless.letspoll.models.responses.RespondentDetails
import java.util.*


class PollGetter : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        val pathParameters = (input["pathParameters"] as? Map<String, String>)
        val pollId = pathParameters?.get("pollId")

        println("pollId passed is $pollId")

        val dslContext = DatabaseAccessUtils.getDatabaseConnection()
        dslContext?.let {
            try {
                val pollRecord: PollRecord? = it
                        .fetchOne(com.serverless.letspoll.models.generated.tables.Poll.POLL,
                                com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID.eq(pollId))

                val respondentRecord = it
                        .fetchOne(com.serverless.letspoll.models.generated.tables.Respondent.RESPONDENT,
                                com.serverless.letspoll.models.generated.tables.Respondent.RESPONDENT.A_RESPONDENT_ID
                                        .eq(pollRecord!!.createdBy))

                return if (pollRecord != null) {
                    val poll = Poll(pollRecord.pollId, pollRecord.pollTitle, pollRecord.pollQuestion)

                    val respondentDetails = RespondentDetails(respondentDisplayName = respondentRecord.respondentDisplayName,
                            respondentEmail = respondentRecord.respondentEmailId)

                    val stateOne = PollResponseStatistics("NO", 10)

                    val stateTwo = PollResponseStatistics("YES", 90)

                    val pollDetailsResponse = PollDetailsResponse(poll, respondentDetails, listOf(stateOne, stateTwo))

                    ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(pollDetailsResponse)
                            .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                            .build()
                } else {
                    ApiGatewayResponse.builder().setStatusCode(404)
                            .setObjectBody("The requested Poll with id doesnt exist")
                            .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                            .build()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return ApiGatewayResponse.builder().setStatusCode(409)
                        .setObjectBody("Could not fetch the poll details")
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                        .build()

            }

        }
        return ApiGatewayResponse.builder().setStatusCode(409)
                .setObjectBody("Could not fetch the poll details")
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build()
    }
}
