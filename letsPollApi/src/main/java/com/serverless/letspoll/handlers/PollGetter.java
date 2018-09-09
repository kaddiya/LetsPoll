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
import java.util.Map;


public class PollGetter implements RequestHandler<Map<String,Object>, ApiGatewayResponse> {

    public ApiGatewayResponse handleRequest(Map<String,Object>input, Context context) {
        Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
        String pollId = pathParameters.get("pollId");
        System.out.println("pollId passed is "+ pollId);

        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        PollRecord pollRecord = dslContext.fetchOne(
            com.serverless.letspoll.models.generated.tables.Poll.POLL,
            com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID.eq(pollId));

        if (pollRecord!=null) {
            Poll poll = new Poll();
            poll.setPollId(pollRecord.getPollId());
            poll.setPollTitle(pollRecord.getPollTitle());
            poll.setPollQuestion(pollRecord.getPollQuestion());
            return ApiGatewayResponse.builder().setStatusCode(200)
                .setObjectBody(poll)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
        }
        else {
            return ApiGatewayResponse.builder().setStatusCode(404)
                .setObjectBody("The requested Poll Doesnt exist")
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
        }
    }
}
