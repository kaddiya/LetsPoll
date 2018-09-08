package com.serverless.letspoll.models;

import java.util.Map;


public class PollResults {
    private String pollId;
    private String pollTitle;
    private String pollQuestion;

    private Map<PollResponseOptions,Integer> responses;

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public Map<PollResponseOptions, Integer> getResponses() {
        return responses;
    }

    public void setResponses(Map<PollResponseOptions, Integer> responses) {
        this.responses = responses;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public String getPollTitle() {
        return pollTitle;
    }

    public void setPollTitle(String pollTitle) {
        this.pollTitle = pollTitle;
    }
}
