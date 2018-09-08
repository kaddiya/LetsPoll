/*
 * This file is generated by jOOQ.
 */
package com.serverless.letspoll.models.generated.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Respondent implements Serializable {

    private static final long serialVersionUID = -1036889745;

    private Integer aRespondentId;
    private String  respondentId;
    private String  respondentEmailId;
    private String  respondentDisplayName;
    private String  respondentToken;

    public Respondent() {}

    public Respondent(Respondent value) {
        this.aRespondentId = value.aRespondentId;
        this.respondentId = value.respondentId;
        this.respondentEmailId = value.respondentEmailId;
        this.respondentDisplayName = value.respondentDisplayName;
        this.respondentToken = value.respondentToken;
    }

    public Respondent(
        Integer aRespondentId,
        String  respondentId,
        String  respondentEmailId,
        String  respondentDisplayName,
        String  respondentToken
    ) {
        this.aRespondentId = aRespondentId;
        this.respondentId = respondentId;
        this.respondentEmailId = respondentEmailId;
        this.respondentDisplayName = respondentDisplayName;
        this.respondentToken = respondentToken;
    }

    public Integer getARespondentId() {
        return this.aRespondentId;
    }

    public void setARespondentId(Integer aRespondentId) {
        this.aRespondentId = aRespondentId;
    }

    public String getRespondentId() {
        return this.respondentId;
    }

    public void setRespondentId(String respondentId) {
        this.respondentId = respondentId;
    }

    public String getRespondentEmailId() {
        return this.respondentEmailId;
    }

    public void setRespondentEmailId(String respondentEmailId) {
        this.respondentEmailId = respondentEmailId;
    }

    public String getRespondentDisplayName() {
        return this.respondentDisplayName;
    }

    public void setRespondentDisplayName(String respondentDisplayName) {
        this.respondentDisplayName = respondentDisplayName;
    }

    public String getRespondentToken() {
        return this.respondentToken;
    }

    public void setRespondentToken(String respondentToken) {
        this.respondentToken = respondentToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Respondent other = (Respondent) obj;
        if (aRespondentId == null) {
            if (other.aRespondentId != null)
                return false;
        }
        else if (!aRespondentId.equals(other.aRespondentId))
            return false;
        if (respondentId == null) {
            if (other.respondentId != null)
                return false;
        }
        else if (!respondentId.equals(other.respondentId))
            return false;
        if (respondentEmailId == null) {
            if (other.respondentEmailId != null)
                return false;
        }
        else if (!respondentEmailId.equals(other.respondentEmailId))
            return false;
        if (respondentDisplayName == null) {
            if (other.respondentDisplayName != null)
                return false;
        }
        else if (!respondentDisplayName.equals(other.respondentDisplayName))
            return false;
        if (respondentToken == null) {
            if (other.respondentToken != null)
                return false;
        }
        else if (!respondentToken.equals(other.respondentToken))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.aRespondentId == null) ? 0 : this.aRespondentId.hashCode());
        result = prime * result + ((this.respondentId == null) ? 0 : this.respondentId.hashCode());
        result = prime * result + ((this.respondentEmailId == null) ? 0 : this.respondentEmailId.hashCode());
        result = prime * result + ((this.respondentDisplayName == null) ? 0 : this.respondentDisplayName.hashCode());
        result = prime * result + ((this.respondentToken == null) ? 0 : this.respondentToken.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Respondent (");

        sb.append(aRespondentId);
        sb.append(", ").append(respondentId);
        sb.append(", ").append(respondentEmailId);
        sb.append(", ").append(respondentDisplayName);
        sb.append(", ").append(respondentToken);

        sb.append(")");
        return sb.toString();
    }
}
