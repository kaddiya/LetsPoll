package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.models.ApiGatewayResponse;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Created by Webonise on 14/09/18.
 */
public class DatabaseMigrator implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
    Connection conn = DatabaseAccessUtils.getSimpleConnection();
    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        System.out.println("connection is "+ conn);
        try{
            Database dataBase = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                new JdbcConnection(conn));
            Liquibase liquiBase = new liquibase.Liquibase("letsPoll.changelog.xml", new ClassLoaderResourceAccessor(), dataBase);
            liquiBase.update(new Contexts(), new LabelExpression());
        }catch (Exception e){
            e.printStackTrace();
        }


        return ApiGatewayResponse.builder()
            .setStatusCode(200)
            .setObjectBody("Successfully migrated the database")
            .build();

    }
}
