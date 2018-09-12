package com.serverless.letspoll.models

//data class ApiGatewayResponse(val statusCode: Int, val body: String, val headers: Map<String, String>, val isBase64Encoded: Boolean)

data class Poll(val pollId: String,
                val pollTitle: String,
                val pollQuestion: String,
                val pollOptions: List<PollResponseOptions> = listOf(PollResponseOptions.YES, PollResponseOptions.NO))

enum class PollResponseOptions(val response: String) {
    YES("YES"), NO("NO")
}

data class PollResponses(val pollId: String, val response: PollResponseOptions, val respondentId: String)

data class PollResults(val pollId: String,
                       val pollTitle: String,
                       val pollQuestion: String,
                       val responses: MutableMap<PollResponseOptions, Int>)

data class Respodent(val respondentId: String, val email: String, val username: String)