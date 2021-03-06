package com.serverless.letspoll.models.requests;

/**
 * Created by Webonise on 08/09/18.
 */
public class PollCreationRequest {
    private String pollTitle;
    private String pollQuestion;
    private String createdBy;

    public PollCreationRequest() {
    }

    public String getPollTitle() {
        return pollTitle;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
