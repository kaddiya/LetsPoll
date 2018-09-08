package com.serverless.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.commons.DatabaseAccessUtils;
import org.jooq.Record;

import java.sql.Timestamp;
import java.util.Map;


public class PollGetter implements RequestHandler<String,String> {

    public String handleRequest(String greetee, Context context) {
        Record result = DatabaseAccessUtils.getDatabaseConnection().fetchOne("SELECT * FROM now()");
        Timestamp ts = (java.sql.Timestamp) result.get("now");
        return "Hello, " + greetee + " on "+ts.toString() +" as per the time on database server";
    }
}
