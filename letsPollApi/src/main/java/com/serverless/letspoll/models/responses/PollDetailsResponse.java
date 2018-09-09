package com.serverless.letspoll.models.responses;

import com.serverless.letspoll.models.Poll;


/**
 * Created by Webonise on 09/09/18.
 */
public class PollDetailsResponse {
    private Poll poll;
    private RespondentDetails createdBy;

    public PollDetailsResponse() {
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public RespondentDetails getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(RespondentDetails createdBy) {
        this.createdBy = createdBy;
    }
}
