package com.serverless.letspoll.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.serverless.letspoll.commons.DatabaseAccessUtils
import com.serverless.letspoll.commons.getRandomString
import com.serverless.letspoll.models.ApiGatewayResponse
import com.serverless.letspoll.models.generated.Tables
import com.serverless.letspoll.models.generated.tables.records.RespondentRecord
import com.serverless.letspoll.models.requests.RespondentRegisterationRequest
import com.serverless.letspoll.models.responses.RespondentRegistrationResponse
import java.io.IOException
import java.util.*


class RespondentRegistrar : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

    override fun handleRequest(input: Map<String, Any>, context: Context): ApiGatewayResponse {
        val respondentRegisterationRequest: RespondentRegisterationRequest
        try {
            respondentRegisterationRequest = ObjectMapper().readValue(input["body"] as String, RespondentRegisterationRequest::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            return ApiGatewayResponse.builder().setStatusCode(400)
                    .setObjectBody("could not parse the request body properly")
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                    .build()

        }

        val respondentId = getRandomString("RID")
        val dslContext = DatabaseAccessUtils.getDatabaseConnection()
        dslContext?.let {
            try {
                it.insertInto<RespondentRecord, String, String, String, String>(Tables.RESPONDENT, Tables.RESPONDENT.RESPONDENT_ID,
                        Tables.RESPONDENT.RESPONDENT_EMAIL_ID, Tables.RESPONDENT.RESPONDENT_TOKEN,
                        Tables.RESPONDENT.RESPONDENT_DISPLAY_NAME)
                        .values(respondentId, respondentRegisterationRequest.emailId,
                                respondentRegisterationRequest.token,
                                respondentRegisterationRequest.displayName).execute()
                return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(RespondentRegistrationResponse(respondentId))
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                        .build()
            } catch (e: Exception) {
                e.printStackTrace()
                return ApiGatewayResponse.builder().setStatusCode(409)
                        .setObjectBody("Could not create the respondent")
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                        .build()
            }
        } ?: kotlin.run {
            return ApiGatewayResponse.builder().setStatusCode(409)
                    .setObjectBody("Could not create the respondent")
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                    .build()
        }
    }

}

