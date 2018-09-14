package com.serverless.letspoll.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.letspoll.commons.DatabaseAccessUtils;
import com.serverless.letspoll.models.ApiGatewayResponse;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
import org.jooq.DSLContext;

import java.util.Map;

/**
 * Created by Webonise on 14/09/18.
 */
public class DatabaseMigrator implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    @Override public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        DSLContext dslContext = DatabaseAccessUtils.getDatabaseConnection();

        Database database = null;
        try {
            database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(
                    new JdbcConnection(dslContext.parsingConnection()));
            Liquibase liquibase = new Liquibase(
                "letsPoll.changelog.xml",
                new ClassLoaderResourceAccessor(),
                database
            );
            liquibase.update( "" );
        } catch (DatabaseException e) {
            e.printStackTrace();
            return ApiGatewayResponse.builder()
                .setStatusCode(409)
                .setObjectBody("Could not delete the poll")
                .build();

        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
        return ApiGatewayResponse.builder()
            .setStatusCode(200)
            .setObjectBody("Successfully migrated the database")
            .build();

    }
}
