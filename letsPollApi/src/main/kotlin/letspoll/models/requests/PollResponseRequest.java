package com.serverless.letspoll.models.requests;

/**
 * Created by Webonise on 09/09/18.
 */
public class PollResponseRequest {
    private String pollResponse;
    private String respondentId;
    private String pollId;

    public PollResponseRequest() {
    }

    public String getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(String respondentId) {
        this.respondentId = respondentId;
    }

    public String getPollResponse() {
        return pollResponse;
    }

    public void setPollResponse(String pollResponse) {
        this.pollResponse = pollResponse;
    }

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }
}
