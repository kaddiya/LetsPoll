package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.models.Poll;
import org.jooq.DSLContext;
import org.jooq.Record;
import com.serverless.letspoll.commons.DatabaseAccessUtils;

import java.sql.Timestamp;
import java.util.List;


public class PollGetter implements RequestHandler<String,List<Poll>> {

    public List<Poll> handleRequest(String greetee, Context context) {
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        return dslContext.selectFrom(com.serverless.letspoll.models.generated.tables.Poll.POLL).fetchInto(
            Poll.class);
    }
}
