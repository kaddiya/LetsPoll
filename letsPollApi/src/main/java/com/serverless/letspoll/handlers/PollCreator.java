package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.PollCreationRequest;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PollCreator implements RequestHandler<PollCreationRequest,List<Poll>>{

    private String getPollId(){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    @Override public List<Poll> handleRequest(PollCreationRequest pollToCreate, Context context) {
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        dslContext.transaction(configuration ->{
            DSL.using(configuration).insertInto(com.serverless.letspoll.models.generated.tables.Poll.POLL,
                com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID,
                com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_TITLE,
                com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_QUESTION)
                .values(getPollId(),pollToCreate.getPollTitle(), pollToCreate.getPollQuestion()).execute();

        });

        return dslContext.selectFrom(com.serverless.letspoll.models.generated.tables.Poll.POLL).fetchInto(Poll.class);



    }
}
