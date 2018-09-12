package com.serverless.letspoll.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.serverless.letspoll.commons.DatabaseAccessUtils
import com.serverless.letspoll.commons.getRandomString
import com.serverless.letspoll.models.ApiGatewayResponse
import com.serverless.letspoll.models.Poll
import com.serverless.letspoll.models.generated.Tables
import com.serverless.letspoll.models.generated.tables.Poll.POLL
import com.serverless.letspoll.models.generated.tables.Respondent
import com.serverless.letspoll.models.generated.tables.records.PollRecord
import com.serverless.letspoll.models.requests.PollCreationRequest
import org.jooq.impl.DSL
import java.io.IOException


class PollCreator : RequestHandler<Map<String, Any>, ApiGatewayResponse> {


    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        val pollToCreate: PollCreationRequest
        try {
            pollToCreate = ObjectMapper().readValue(input["body"] as? String, PollCreationRequest::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            return ApiGatewayResponse.builder().setStatusCode(400).setObjectBody(
                    "could not parse the request body properly so can't create this poll").build()

        }

        val dslContext = DatabaseAccessUtils.getDatabaseConnection()
        dslContext?.let {
            try {
                it.transaction { configuration ->
                    val respondentRecord = DSL.using(configuration)
                            .fetchOne(Respondent.RESPONDENT,
                                    Respondent.RESPONDENT.RESPONDENT_ID.eq(pollToCreate.createdBy))

                    DSL.using(configuration)
                            .insertInto<PollRecord, String, String, String, Int>(POLL,
                                    POLL.POLL_ID,
                                    POLL.POLL_TITLE,
                                    POLL.POLL_QUESTION,
                                    POLL.CREATED_BY)
                            .values(getRandomString("PID"), pollToCreate.pollTitle,
                                    pollToCreate.pollQuestion, respondentRecord.aRespondentId)
                            .execute()

                }

            } catch (e: Exception) {
                e.printStackTrace()
                return ApiGatewayResponse.builder().setStatusCode(409).setObjectBody(
                        "Could not create this poll as the respondentId" + pollToCreate.createdBy
                                + "wasnt found").build()
            }
        }


        val polls = dslContext?.selectFrom<PollRecord>(com.serverless.letspoll.models.generated.tables.Poll.POLL)
                ?.orderBy(Tables.POLL.A_POLL_ID.desc())?.fetchInto(Poll::class.java)
        return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(polls).build()

    }
}
