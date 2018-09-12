package com.serverless.letspoll.handlers


import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.letspoll.commons.DatabaseAccessUtils
import com.serverless.letspoll.models.ApiGatewayResponse
import com.serverless.letspoll.models.Poll
import com.serverless.letspoll.models.generated.tables.records.PollRecord
import org.jooq.DSLContext

import java.util.Collections

class PollsGetter : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        val dslContext = DatabaseAccessUtils.getDatabaseConnection()
        val polls = dslContext!!.selectFrom<PollRecord>(com.serverless.letspoll.models.generated.tables.Poll.POLL)
                .fetchInto(Poll::class.java)
        return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(polls)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build()
    }
}
