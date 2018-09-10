package com.serverless.letspoll.models.responses;

/**
 * Created by Webonise on 10/09/18.
 */
public class PollResponseStatistics {
    private String response;
    private int count;


    public PollResponseStatistics() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
