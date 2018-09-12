package com.serverless.letspoll.models;

public enum PollResponseOptions {


    YES("YES"), NO("NO");

    private String response;

    private PollResponseOptions(String response) {
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }


}
