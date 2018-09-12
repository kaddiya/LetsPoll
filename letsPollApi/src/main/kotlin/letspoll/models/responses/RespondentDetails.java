package com.serverless.letspoll.models.responses;

/**
 * Created by Webonise on 09/09/18.
 */
public class RespondentDetails {
    private String respondentDisplayName;
    private String respondentEmail;

    public RespondentDetails() {
    }

    public String getRespondentDisplayName() {
        return respondentDisplayName;
    }

    public void setRespondentDisplayName(String respondentDisplayName) {
        this.respondentDisplayName = respondentDisplayName;
    }

    public String getRespondentEmail() {
        return respondentEmail;
    }

    public void setRespondentEmail(String respondentEmail) {
        this.respondentEmail = respondentEmail;
    }
}
