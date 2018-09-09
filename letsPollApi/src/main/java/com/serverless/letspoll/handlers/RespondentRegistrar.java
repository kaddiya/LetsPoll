package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.commons.RandomIdGenerator;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.generated.Tables;
import com.serverless.letspoll.models.requests.RespondentRegisterationRequest;
import com.serverless.letspoll.models.responses.RespondentRegistrationResponse;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class RespondentRegistrar implements RequestHandler<Map<String,Object>, ApiGatewayResponse> {

    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        final ObjectMapper mapper = new ObjectMapper();
        final RespondentRegisterationRequest respondentRegisterationRequest = mapper.convertValue(input, RespondentRegisterationRequest.class);
        String respondentId = RandomIdGenerator.getRandomString("RID");
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        try{
          dslContext.insertInto(Tables.RESPONDENT,Tables.RESPONDENT.RESPONDENT_ID,Tables.RESPONDENT.RESPONDENT_EMAIL_ID,Tables.RESPONDENT.RESPONDENT_TOKEN,Tables.RESPONDENT.RESPONDENT_DISPLAY_NAME)
                .values(respondentId,respondentRegisterationRequest.getEmailId(),respondentRegisterationRequest.getToken(),respondentRegisterationRequest.getEmailId())
                .execute();
        }catch (Exception e){
            e.printStackTrace();
            RespondentRegistrationResponse response = new RespondentRegistrationResponse();
            response.setLetsPollRespondentId(respondentId);
            return ApiGatewayResponse.builder().setStatusCode(409)
                .setObjectBody("Could not create the respondent")
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
        }

        RespondentRegistrationResponse response = new RespondentRegistrationResponse();
        response.setLetsPollRespondentId(respondentId);
        return ApiGatewayResponse.builder().setStatusCode(200)
                .setObjectBody(response)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
        }


}

