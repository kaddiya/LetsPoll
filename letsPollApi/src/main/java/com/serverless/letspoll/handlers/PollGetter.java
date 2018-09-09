package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.generated.tables.records.PollRecord;
import org.jooq.DSLContext;
import com.serverless.letspoll.commons.DatabaseAccessUtils;

import java.util.Arrays;
import java.util.Collections;


public class PollGetter implements RequestHandler<String, ApiGatewayResponse> {

    public ApiGatewayResponse handleRequest(String pollId, Context context) {
        System.out.println("poll is is " + pollId);
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();

        PollRecord pollRecord = dslContext.fetchOne(com.serverless.letspoll.models.generated.tables.Poll.POLL, com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID.eq(pollId));
        Poll poll = new Poll();
        if (pollRecord!=null) {
            poll.setPollId(pollRecord.getPollId());
            poll.setPollTitle(pollRecord.getPollTitle());
            poll.setPollQuestion(pollRecord.getPollQuestion());
            //return userRecord.into(Poll.class);
        }
        return ApiGatewayResponse.builder()
            .setStatusCode(200)
            .setObjectBody(poll)
            .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
            .build();

    }
}
