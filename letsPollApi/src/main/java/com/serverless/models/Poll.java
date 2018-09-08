package com.serverless.models;

import java.util.List;


public class Poll {

    private String pollId;
    private String pollTitle;
    private String pollQuestion;
    private List<PollResponseOptions> pollOptions;

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
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

    public List<PollResponseOptions> getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(List<PollResponseOptions> pollOptions) {
        this.pollOptions = pollOptions;
    }
}
