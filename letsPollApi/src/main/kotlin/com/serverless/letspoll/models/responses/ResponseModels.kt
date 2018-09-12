package com.serverless.letspoll.models.responses

import com.serverless.letspoll.models.Poll

data class PollDetailsResponse(val poll: Poll, val createdBy: RespondentDetails, val statistics: List<PollResponseStatistics>)

data class PollResponseResponse(val message: String)

data class PollResponseStatistics(val response: String, val count: Int)

data class RespondentDetails(val respondentDisplayName: String, val respondentEmail: String)

data class RespondentRegistrationResponse(val letsPollRespondentId: String)