package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.commons.RandomIdGenerator;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.generated.tables.Poll;
import com.serverless.letspoll.models.generated.tables.Respondent;
import com.serverless.letspoll.models.generated.tables.RespondentPollResponse;
import com.serverless.letspoll.models.generated.tables.records.RespondentRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.Map;

/**
 * Created by Webonise on 11/09/18.
 */
public class PollDeletor implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {



    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
        String pollId = pathParameters.get("pollId");
        System.out.println("pollId passed is " + pollId);

        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();

        try{
            dslContext.transaction(configuration -> {
                DSL.using(configuration).deleteFrom(RespondentPollResponse.RESPONDENT_POLL_RESPONSE)
                    .where(RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_POLL_ID
                        .eq((DSL.using(configuration).select(Poll.POLL.A_POLL_ID).from(Poll.POLL)
                            .where(Poll.POLL.POLL_ID.eq(pollId))))).execute();

                DSL.using(configuration).delete(Poll.POLL).where(Poll.POLL.POLL_ID.eq(pollId))
                    .execute();
            });

        }catch (Exception e){
            e.printStackTrace();
            return ApiGatewayResponse.builder()
                .setStatusCode(409)
                .setObjectBody("Could not delete the poll")
                .build();

        }

        return ApiGatewayResponse.builder()
            .setStatusCode(200)
            .setObjectBody("Succesfully deleted the poll")
            .build();


    }
}
