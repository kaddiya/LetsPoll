package com.serverless.commons;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;


public class DatabaseAccessUtils {
    public static DSLContext dslContext;

    public static DSLContext getDatabaseConnection() {

        if (dslContext != null) {
            return dslContext;
        } else {

            String userName = LambdaEnvironmentUtils.getValue("databaseUsername");
            String password = LambdaEnvironmentUtils.getValue("databasePassword");
            String databaseUrl = LambdaEnvironmentUtils.getValue("databaseUrl");
            String databasePort = LambdaEnvironmentUtils.getValue("databasePort");
            String databaseName = LambdaEnvironmentUtils.getValue("databaseName");


            StringBuilder sb = new StringBuilder();
            sb.append("jdbc:postgresql://");
            sb.append(databaseUrl);
            sb.append(":");
            sb.append(databasePort);
            sb.append("/");
            sb.append(databaseName);

            String url = sb.toString();
            System.out.println(url);
            try {
                System.out.println(url);
                System.out.println(userName);
                System.out.println(password);
                Connection conn = DriverManager.getConnection(url, userName, password);
                dslContext = DSL.using(conn, SQLDialect.POSTGRES);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return dslContext;
        }
    }
}
