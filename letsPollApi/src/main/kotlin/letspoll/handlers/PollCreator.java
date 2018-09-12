package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.commons.RandomIdGenerator;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.generated.Tables;
import com.serverless.letspoll.models.generated.tables.Respondent;
import com.serverless.letspoll.models.generated.tables.records.RespondentRecord;
import com.serverless.letspoll.models.requests.PollCreationRequest;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class PollCreator implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {



    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        final ObjectMapper mapper = new ObjectMapper();
        final PollCreationRequest pollToCreate;
        try {
            pollToCreate = mapper.readValue((String) input.get("body"), PollCreationRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiGatewayResponse.builder().setStatusCode(400).setObjectBody(
                "could not parse the request body properly so can't create this poll").build();

        }

        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        try {
            dslContext.transaction(configuration -> {
                RespondentRecord respondentRecord = DSL.using(configuration)
                    .fetchOne(Respondent.RESPONDENT,
                        Respondent.RESPONDENT.RESPONDENT_ID.eq(pollToCreate.getCreatedBy()));

                DSL.using(configuration)
                    .insertInto(com.serverless.letspoll.models.generated.tables.Poll.POLL,
                        com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID,
                        com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_TITLE,
                        com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_QUESTION,
                        com.serverless.letspoll.models.generated.tables.Poll.POLL.CREATED_BY)
                    .values(RandomIdGenerator.getRandomString("PID"), pollToCreate.getPollTitle(),
                        pollToCreate.getPollQuestion(), respondentRecord.getARespondentId())
                    .execute();

            });

        } catch (Exception e) {
            e.printStackTrace();
            return ApiGatewayResponse.builder().setStatusCode(409).setObjectBody(
                "Could not create this poll as the respondentId" + pollToCreate.getCreatedBy()
                    + "wasnt found").build();
        }



        List<Poll> polls =
            dslContext.selectFrom(com.serverless.letspoll.models.generated.tables.Poll.POLL)
                .orderBy(Tables.POLL.A_POLL_ID.desc()).fetchInto(Poll.class);
        return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(polls).build();

    }
}
