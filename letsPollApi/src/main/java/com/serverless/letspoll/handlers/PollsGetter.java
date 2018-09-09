package com.serverless.letspoll.handlers;



import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.Poll;
import org.jooq.DSLContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PollsGetter implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {


    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        List<Poll> polls = dslContext.selectFrom(
            com.serverless.letspoll.models.generated.tables.Poll.POLL).fetchInto(Poll.class);
        return ApiGatewayResponse.builder()
            .setStatusCode(200)
            .setObjectBody(polls)
            .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
            .build();
    }
}
