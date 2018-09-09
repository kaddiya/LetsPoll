package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.commons.RandomIdGenerator;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.requests.PollCreationRequest;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.*;


public class PollCreator implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {



    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final PollCreationRequest pollToCreate = mapper.convertValue(input, PollCreationRequest.class);
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        dslContext.transaction(configuration ->{
            DSL.using(configuration).insertInto(com.serverless.letspoll.models.generated.tables.Poll.POLL,
                com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID,
                com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_TITLE,
                com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_QUESTION,
                com.serverless.letspoll.models.generated.tables.Poll.POLL.CREATED_BY)
                .values(RandomIdGenerator.getRandomString("PID"),pollToCreate.getPollTitle(), pollToCreate.getPollQuestion(),1).execute();

        });

        //return dslContext.selectFrom(com.serverless.letspoll.models.generated.tables.Poll.POLL).fetchInto(Poll.class);
        List <Poll> polls = dslContext.selectFrom(com.serverless.letspoll.models.generated.tables.Poll.POLL).fetchInto(Poll.class);
        return ApiGatewayResponse.builder()
            .setStatusCode(200)
            .setObjectBody(polls)
            .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
            .build();

    }
}
