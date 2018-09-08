package com.serverless;

import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.handlers.PollGetter;
import org.jooq.DSLContext;

/**
 * Created by Webonise on 08/09/18.
 */
public class Main {
    public static void main(String args[]){
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();
        dslContext.selectFrom(com.serverless.letspoll.models.generated.tables.Poll.POLL).fetch();

    }
}
