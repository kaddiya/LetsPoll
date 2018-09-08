/*
 * This file is generated by jOOQ.
 */
package com.serverless.letspoll.models.generated.tables.records;


import com.serverless.letspoll.models.generated.tables.RespondentPollResponse;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class RespondentPollResponseRecord extends UpdatableRecordImpl<RespondentPollResponseRecord> implements Record4<Integer, Integer, Integer, String> {

    private static final long serialVersionUID = -754849488;

    /**
     * Setter for <code>public.respondent_poll_response.a_respondent_poll_response_id</code>.
     */
    public void setARespondentPollResponseId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.respondent_poll_response.a_respondent_poll_response_id</code>.
     */
    public Integer getARespondentPollResponseId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.respondent_poll_response.a_respondent_id</code>.
     */
    public void setARespondentId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.respondent_poll_response.a_respondent_id</code>.
     */
    public Integer getARespondentId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.respondent_poll_response.a_poll_id</code>.
     */
    public void setAPollId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.respondent_poll_response.a_poll_id</code>.
     */
    public Integer getAPollId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.respondent_poll_response.response</code>.
     */
    public void setResponse(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.respondent_poll_response.response</code>.
     */
    public String getResponse() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Integer, Integer, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Integer, Integer, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_RESPONDENT_POLL_RESPONSE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_RESPONDENT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_POLL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return RespondentPollResponse.RESPONDENT_POLL_RESPONSE.RESPONSE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getARespondentPollResponseId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getARespondentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getAPollId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getResponse();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getARespondentPollResponseId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getARespondentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getAPollId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getResponse();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespondentPollResponseRecord value1(Integer value) {
        setARespondentPollResponseId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespondentPollResponseRecord value2(Integer value) {
        setARespondentId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespondentPollResponseRecord value3(Integer value) {
        setAPollId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespondentPollResponseRecord value4(String value) {
        setResponse(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RespondentPollResponseRecord values(Integer value1, Integer value2, Integer value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RespondentPollResponseRecord
     */
    public RespondentPollResponseRecord() {
        super(RespondentPollResponse.RESPONDENT_POLL_RESPONSE);
    }

    /**
     * Create a detached, initialised RespondentPollResponseRecord
     */
    public RespondentPollResponseRecord(Integer aRespondentPollResponseId, Integer aRespondentId, Integer aPollId, String response) {
        super(RespondentPollResponse.RESPONDENT_POLL_RESPONSE);

        set(0, aRespondentPollResponseId);
        set(1, aRespondentId);
        set(2, aPollId);
        set(3, response);
    }
}
