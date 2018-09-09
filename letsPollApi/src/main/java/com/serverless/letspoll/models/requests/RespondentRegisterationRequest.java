package com.serverless.letspoll.models.requests;

/**
 * Created by Webonise on 09/09/18.
 */
public class RespondentRegisterationRequest {

    private String token;
    private String emailId;
    private String displayName;

    public RespondentRegisterationRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
