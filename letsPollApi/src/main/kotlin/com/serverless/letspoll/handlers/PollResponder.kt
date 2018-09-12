package com.serverless.letspoll.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.serverless.letspoll.commons.DatabaseAccessUtils
import com.serverless.letspoll.models.ApiGatewayResponse
import com.serverless.letspoll.models.generated.tables.Poll
import com.serverless.letspoll.models.generated.tables.Respondent
import com.serverless.letspoll.models.generated.tables.RespondentPollResponse
import com.serverless.letspoll.models.generated.tables.records.RespondentPollResponseRecord
import com.serverless.letspoll.models.requests.PollResponseRequest
import com.serverless.letspoll.models.responses.PollResponseResponse
import org.jooq.impl.DSL
import java.util.*

class PollResponder : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {

        val pollResponseRequest: PollResponseRequest?
        try {
            pollResponseRequest = ObjectMapper().readValue(input["body"] as String, PollResponseRequest::class.java)
            if (pollResponseRequest == null || !Arrays.asList("YES", "NO").contains(pollResponseRequest.pollResponse)) {
                return ApiGatewayResponse.builder().setStatusCode(400).setObjectBody(
                        "Invalid response Type found")
                        .build()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiGatewayResponse.builder().setStatusCode(400).setObjectBody(
                    "could not parse the request body properly so can't record the response to this poll")
                    .build()
        }

        val dslContext = DatabaseAccessUtils.getDatabaseConnection()
        dslContext?.let {

            try {
                it.transaction { configuration ->
                    val respondentRecord = DSL.using(configuration)
                            .fetchOne(Respondent.RESPONDENT, Respondent.RESPONDENT.RESPONDENT_ID
                                    .eq(pollResponseRequest.respondentId))

                    val pollRecord = DSL.using(configuration)
                            .fetchOne(Poll.POLL, Poll.POLL.POLL_ID.eq(pollResponseRequest.pollId))


                    DSL.using(configuration)
                            .insertInto<RespondentPollResponseRecord, Int, Int, String>(RespondentPollResponse.RESPONDENT_POLL_RESPONSE,
                                    RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_RESPONDENT_ID,
                                    RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_POLL_ID,
                                    RespondentPollResponse.RESPONDENT_POLL_RESPONSE.RESPONSE)
                            .values(pollRecord.aPollId, respondentRecord.aRespondentId,
                                    pollResponseRequest.pollResponse).execute()


                }

            } catch (e: Exception) {
                e.printStackTrace()
                return ApiGatewayResponse.builder().setStatusCode(409).setObjectBody(
                        "Could not record this response").build()
            }
        } ?: kotlin.run {
            return ApiGatewayResponse.builder().setStatusCode(409).setObjectBody(
                    "Could not record this response").build()
        }
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(PollResponseResponse("Successfully responded to the poll"))
                .build()

    }
}
