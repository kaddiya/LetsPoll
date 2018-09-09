package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.models.Poll;
import com.serverless.letspoll.models.generated.tables.records.PollRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import com.serverless.letspoll.commons.DatabaseAccessUtils;

import java.sql.Timestamp;
import java.util.List;


public class PollGetter implements RequestHandler<String,Poll> {

    public Poll handleRequest(String pollId, Context context) {
        System.out.println("poll is is " + pollId);
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();

        PollRecord userRecord = dslContext.fetchOne(com.serverless.letspoll.models.generated.tables.Poll.POLL, com.serverless.letspoll.models.generated.tables.Poll.POLL.POLL_ID.eq(pollId));
        if (userRecord!=null) {
            return userRecord.into(Poll.class);
        }
        return null;
    }
}
