package com.serverless.letspoll.commons;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseAccessUtils {
    public static DSLContext dslContext;
    public static Connection connection;

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

    public static Connection getSimpleConnection(){

        if(connection != null){
            return connection;

        }else{
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(getConnectionString(), LambdaEnvironmentUtils.getValue("databaseUsername"), LambdaEnvironmentUtils.getValue("databasePassword"));
            } catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static String getConnectionString(){
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

        return sb.toString();

    }
}
