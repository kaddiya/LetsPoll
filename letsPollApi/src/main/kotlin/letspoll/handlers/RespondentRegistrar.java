package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.commons.RandomIdGenerator;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.generated.Tables;
import com.serverless.letspoll.models.requests.RespondentRegisterationRequest;
import com.serverless.letspoll.models.responses.RespondentRegistrationResponse;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;


public class RespondentRegistrar
    implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        final ObjectMapper mapper = new ObjectMapper();
        RespondentRegisterationRequest respondentRegisterationRequest;
        try {
            respondentRegisterationRequest =
                mapper.readValue((String) input.get("body"), RespondentRegisterationRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiGatewayResponse.builder().setStatusCode(400)
                .setObjectBody("could not parse the request body properly")
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();

        }
        String respondentId = RandomIdGenerator.getRandomString("RID");
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        try {
            dslContext.insertInto(Tables.RESPONDENT, Tables.RESPONDENT.RESPONDENT_ID,
                Tables.RESPONDENT.RESPONDENT_EMAIL_ID, Tables.RESPONDENT.RESPONDENT_TOKEN,
                Tables.RESPONDENT.RESPONDENT_DISPLAY_NAME)
                .values(respondentId, respondentRegisterationRequest.getEmailId(),
                    respondentRegisterationRequest.getToken(),
                    respondentRegisterationRequest.getDisplayName()).execute();
        } catch (Exception e) {
            e.printStackTrace();
            return ApiGatewayResponse.builder().setStatusCode(409)
                .setObjectBody("Could not create the respondent")
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
        }

        RespondentRegistrationResponse response = new RespondentRegistrationResponse();
        response.setLetsPollRespondentId(respondentId);
        return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(response)
            .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
            .build();
    }


}

