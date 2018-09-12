package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.models.ApiGatewayResponse;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.generated.tables.RespondentPollResponse;
import com.serverless.letspoll.models.generated.tables.records.PollRecord;
import com.serverless.letspoll.models.generated.tables.records.RespondentPollResponseRecord;
import com.serverless.letspoll.models.generated.tables.records.RespondentRecord;
import com.serverless.letspoll.models.responses.PollDetailsResponse;
import com.serverless.letspoll.models.responses.PollResponseStatistics;
import com.serverless.letspoll.models.responses.RespondentDetails;
import org.jooq.DSLContext;

import java.util.*;


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


            List<RespondentPollResponse> pollResponse = dslContext.selectFrom(
                RespondentPollResponse.RESPONDENT_POLL_RESPONSE).where(
                RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_POLL_ID

                    .eq(pollRecord.getAPollId())).fetchInto(RespondentPollResponse.class);



            PollResponseStatistics s1 = new PollResponseStatistics();
            s1.setCount(10);
            s1.setResponse("NO");

            PollResponseStatistics s2 = new PollResponseStatistics();
            s1.setCount(90);
            s1.setResponse("YES");
            if (pollRecord != null) {
                Poll poll = new Poll();
                poll.setPollId(pollRecord.getPollId());
                poll.setPollTitle(pollRecord.getPollTitle());
                poll.setPollQuestion(pollRecord.getPollQuestion());

                RespondentDetails respondentDetails = new RespondentDetails();
                respondentDetails
                    .setRespondentDisplayName(respondentRecord.getRespondentDisplayName());
                respondentDetails.setRespondentEmail(respondentRecord.getRespondentEmailId());

                PollDetailsResponse pollDetailsResponse = new PollDetailsResponse();
                pollDetailsResponse.setPoll(poll);
                pollDetailsResponse.setCreatedBy(respondentDetails);
                pollDetailsResponse.setStatistics(Arrays.asList(s1,s2));
                return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(pollDetailsResponse)
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
