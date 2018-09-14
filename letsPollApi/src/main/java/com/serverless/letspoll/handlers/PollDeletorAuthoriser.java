package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.models.ApiGatewayResponse;

import java.util.Map;

/**
 * Created by Webonise on 11/09/18.
 */
public class PollDeletorAuthoriser implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        return null;
    }
}
