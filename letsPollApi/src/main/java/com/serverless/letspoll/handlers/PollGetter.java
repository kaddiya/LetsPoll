package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.generated.tables.records.PollRecord;
import com.serverless.letspoll.models.generated.tables.records.RespondentRecord;
import com.serverless.letspoll.models.responses.PollDetailsResponse;
import com.serverless.letspoll.models.responses.RespondentDetails;
import org.jooq.DSLContext;

import java.util.Collections;
import java.util.Map;


public class PollGetter implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
        String pollId = pathParameters.get("pollId");
        System.out.println("pollId passed is " + pollId);

        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        try {
            PollRecord pollRecord = dslContext
                .fetchOne(com.serverless.letspoll.models.generated.tables.Poll.POLL,
                    com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID.eq(pollId));

            RespondentRecord respondentRecord = dslContext
                .fetchOne(com.serverless.letspoll.models.generated.tables.Respondent.RESPONDENT,
                    com.serverless.letspoll.models.generated.tables.Respondent.RESPONDENT.A_RESPONDENT_ID
                        .eq(pollRecord.getCreatedBy()));

            if (pollRecord != null) {
                Poll poll = new Poll();
                poll.setPollId(pollRecord.getPollId());
                poll.setPollTitle(pollRecord.getPollTitle());
                poll.setPollQuestion(pollRecord.getPollQuestion());

                RespondentDetails respondentDetails = new RespondentDetails();
                respondentDetails
                    .setRespondentDisplayName(respondentRecord.getRespondentDisplayName());
                respondentDetails.setRespondentEmail(respondentRecord.getRespondentEmailId());

                PollDetailsResponse response = new PollDetailsResponse();
                response.setPoll(poll);
                response.setCreatedBy(respondentDetails);
                return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(response)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                    .build();
            } else {
                return ApiGatewayResponse.builder().setStatusCode(404)
                    .setObjectBody("The requested Poll with id doesnt exist")
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                    .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ApiGatewayResponse.builder().setStatusCode(409)
                .setObjectBody("Could not fetch the poll details")
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();

        }

    }
}
