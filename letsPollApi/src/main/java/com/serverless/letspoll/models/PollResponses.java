package com.serverless.letspoll.models;


public class PollResponses {

    private String pollId;
    private PollResponseOptions response;
    private String respondentId;

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public PollResponseOptions getResponse() {
        return response;
    }

    public void setResponse(PollResponseOptions response) {
        this.response = response;
    }

    public String getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(String respondentId) {
        this.respondentId = respondentId;
    }
}
