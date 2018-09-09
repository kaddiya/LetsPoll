package com.serverless.letspoll.models.requests;

/**
 * Created by Webonise on 09/09/18.
 */
public class PollResponseRequest {
    private String pollResponse;
    private String respondendId;

    public PollResponseRequest() {
    }

    public String getRespondendId() {
        return respondendId;
    }

    public void setRespondendId(String respondendId) {
        this.respondendId = respondendId;
    }

    public String getPollResponse() {
        return pollResponse;
    }

    public void setPollResponse(String pollResponse) {
        this.pollResponse = pollResponse;
    }
}
