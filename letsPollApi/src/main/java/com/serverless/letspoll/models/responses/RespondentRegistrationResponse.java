package com.serverless.letspoll.models.responses;

/**
 * Created by Webonise on 09/09/18.
 */
public class RespondentRegistrationResponse {
    public RespondentRegistrationResponse(String letsPollRespondentId) {
        this.letsPollRespondentId = letsPollRespondentId;
    }

    private String letsPollRespondentId;

    public RespondentRegistrationResponse() {
    }


    public String getLetsPollRespondentId() {
        return letsPollRespondentId;
    }

    public void setLetsPollRespondentId(String letsPollRespondentId) {
        this.letsPollRespondentId = letsPollRespondentId;
    }
}
