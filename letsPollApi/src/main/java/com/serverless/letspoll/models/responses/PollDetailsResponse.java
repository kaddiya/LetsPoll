package com.serverless.letspoll.models.responses;

import com.serverless.letspoll.models.Poll;

import java.util.List;


/**
 * Created by Webonise on 09/09/18.
 */
public class PollDetailsResponse {
    private Poll poll;
    private RespondentDetails createdBy;
    private List<PollResponseStatistics> statistics;

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

    public List<PollResponseStatistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<PollResponseStatistics> statistics) {
        this.statistics = statistics;
    }
}
