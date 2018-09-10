package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.commons.RandomIdGenerator;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.generated.tables.Poll;
import com.serverless.letspoll.models.generated.tables.Respondent;
import com.serverless.letspoll.models.generated.tables.RespondentPollResponse;
import com.serverless.letspoll.models.generated.tables.records.PollRecord;
import com.serverless.letspoll.models.generated.tables.records.RespondentRecord;
import com.serverless.letspoll.models.requests.PollCreationRequest;
import com.serverless.letspoll.models.requests.PollResponseRequest;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Webonise on 09/09/18.
 */
public class PollResponder implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {


    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        final ObjectMapper mapper = new ObjectMapper();
        final PollResponseRequest pollResponseRequest;
        try {
            pollResponseRequest = mapper.readValue((String) input.get("body"), PollResponseRequest.class);
            if(pollResponseRequest!=null && Arrays.asList("YES","NO").contains(
                pollResponseRequest.getPollResponse())) {
                return ApiGatewayResponse.builder().setStatusCode(400).setObjectBody(
                    "Invalid response Type found")
                    .build();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ApiGatewayResponse.builder().setStatusCode(400).setObjectBody(
                "could not parse the request body properly so can't record the response to this poll")
                .build();
        }
            DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
            try {
                dslContext.transaction(configuration -> {
                    RespondentRecord respondentRecord = DSL.using(configuration)
                        .fetchOne(Respondent.RESPONDENT, Respondent.RESPONDENT.RESPONDENT_ID
                            .eq(pollResponseRequest.getRespondentId()));

                    PollRecord pollRecord = DSL.using(configuration)
                        .fetchOne(Poll.POLL, Poll.POLL.POLL_ID.eq(pollResponseRequest.getPollId()));


                    DSL.using(configuration)
                        .insertInto(RespondentPollResponse.RESPONDENT_POLL_RESPONSE,
                            RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_RESPONDENT_ID,
                            RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_POLL_ID,
                            RespondentPollResponse.RESPONDENT_POLL_RESPONSE.RESPONSE)
                        .values(pollRecord.getAPollId(), respondentRecord.getARespondentId(),
                            pollResponseRequest.getPollResponse()).execute();



                });

            } catch (Exception e) {
                e.printStackTrace();
                return ApiGatewayResponse.builder().setStatusCode(409).setObjectBody(
                    "Could not record this response").build();
            }

        return ApiGatewayResponse.builder().setStatusCode(201).setObjectBody(
            "Sucessfully recorded the response for the poll"+pollResponseRequest.getPollId() + " by "+pollResponseRequest.getRespondentId()).build();
    }
}
