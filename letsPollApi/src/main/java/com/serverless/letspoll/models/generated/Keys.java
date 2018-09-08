/*
 * This file is generated by jOOQ.
 */
package com.serverless.letspoll.models.generated;


import com.serverless.letspoll.models.generated.tables.Poll;
import com.serverless.letspoll.models.generated.tables.Respondent;
import com.serverless.letspoll.models.generated.tables.RespondentPollResponse;
import com.serverless.letspoll.models.generated.tables.records.PollRecord;
import com.serverless.letspoll.models.generated.tables.records.RespondentPollResponseRecord;
import com.serverless.letspoll.models.generated.tables.records.RespondentRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<PollRecord, Integer> IDENTITY_POLL = Identities0.IDENTITY_POLL;
    public static final Identity<RespondentRecord, Integer> IDENTITY_RESPONDENT = Identities0.IDENTITY_RESPONDENT;
    public static final Identity<RespondentPollResponseRecord, Integer> IDENTITY_RESPONDENT_POLL_RESPONSE = Identities0.IDENTITY_RESPONDENT_POLL_RESPONSE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PollRecord> POLL_PKEY = UniqueKeys0.POLL_PKEY;
    public static final UniqueKey<RespondentRecord> RESPONDENT_PKEY = UniqueKeys0.RESPONDENT_PKEY;
    public static final UniqueKey<RespondentRecord> RESPONDENT_RESPONDENT_ID_KEY = UniqueKeys0.RESPONDENT_RESPONDENT_ID_KEY;
    public static final UniqueKey<RespondentRecord> RESPONDENT_RESPONDENT_EMAIL_ID_KEY = UniqueKeys0.RESPONDENT_RESPONDENT_EMAIL_ID_KEY;
    public static final UniqueKey<RespondentRecord> RESPONDENT_RESPONDENT_DISPLAY_NAME_KEY = UniqueKeys0.RESPONDENT_RESPONDENT_DISPLAY_NAME_KEY;
    public static final UniqueKey<RespondentPollResponseRecord> RESPONDENT_POLL_RESPONSE_PKEY = UniqueKeys0.RESPONDENT_POLL_RESPONSE_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<PollRecord, RespondentRecord> POLL__POLL_CREATED_BY_FKEY = ForeignKeys0.POLL__POLL_CREATED_BY_FKEY;
    public static final ForeignKey<RespondentPollResponseRecord, RespondentRecord> RESPONDENT_POLL_RESPONSE__RESPONDENT_POLL_RESPONSE_A_RESPONDENT_ID_FKEY = ForeignKeys0.RESPONDENT_POLL_RESPONSE__RESPONDENT_POLL_RESPONSE_A_RESPONDENT_ID_FKEY;
    public static final ForeignKey<RespondentPollResponseRecord, PollRecord> RESPONDENT_POLL_RESPONSE__RESPONDENT_POLL_RESPONSE_A_POLL_ID_FKEY = ForeignKeys0.RESPONDENT_POLL_RESPONSE__RESPONDENT_POLL_RESPONSE_A_POLL_ID_FKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<PollRecord, Integer> IDENTITY_POLL = Internal.createIdentity(Poll.POLL, Poll.POLL.A_POLL_ID);
        public static Identity<RespondentRecord, Integer> IDENTITY_RESPONDENT = Internal.createIdentity(Respondent.RESPONDENT, Respondent.RESPONDENT.A_RESPONDENT_ID);
        public static Identity<RespondentPollResponseRecord, Integer> IDENTITY_RESPONDENT_POLL_RESPONSE = Internal.createIdentity(RespondentPollResponse.RESPONDENT_POLL_RESPONSE, RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_RESPONDENT_POLL_RESPONSE_ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<PollRecord> POLL_PKEY = Internal.createUniqueKey(Poll.POLL, "poll_pkey", Poll.POLL.A_POLL_ID);
        public static final UniqueKey<RespondentRecord> RESPONDENT_PKEY = Internal.createUniqueKey(Respondent.RESPONDENT, "respondent_pkey", Respondent.RESPONDENT.A_RESPONDENT_ID);
        public static final UniqueKey<RespondentRecord> RESPONDENT_RESPONDENT_ID_KEY = Internal.createUniqueKey(Respondent.RESPONDENT, "respondent_respondent_id_key", Respondent.RESPONDENT.RESPONDENT_ID);
        public static final UniqueKey<RespondentRecord> RESPONDENT_RESPONDENT_EMAIL_ID_KEY = Internal.createUniqueKey(Respondent.RESPONDENT, "respondent_respondent_email_id_key", Respondent.RESPONDENT.RESPONDENT_EMAIL_ID);
        public static final UniqueKey<RespondentRecord> RESPONDENT_RESPONDENT_DISPLAY_NAME_KEY = Internal.createUniqueKey(Respondent.RESPONDENT, "respondent_respondent_display_name_key", Respondent.RESPONDENT.RESPONDENT_DISPLAY_NAME);
        public static final UniqueKey<RespondentPollResponseRecord> RESPONDENT_POLL_RESPONSE_PKEY = Internal.createUniqueKey(RespondentPollResponse.RESPONDENT_POLL_RESPONSE, "respondent_poll_response_pkey", RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_RESPONDENT_POLL_RESPONSE_ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<PollRecord, RespondentRecord> POLL__POLL_CREATED_BY_FKEY = Internal.createForeignKey(
            Keys.RESPONDENT_PKEY, Poll.POLL, "poll__poll_created_by_fkey", Poll.POLL.CREATED_BY);
        public static final ForeignKey<RespondentPollResponseRecord, RespondentRecord> RESPONDENT_POLL_RESPONSE__RESPONDENT_POLL_RESPONSE_A_RESPONDENT_ID_FKEY = Internal.createForeignKey(
            Keys.RESPONDENT_PKEY, RespondentPollResponse.RESPONDENT_POLL_RESPONSE, "respondent_poll_response__respondent_poll_response_a_respondent_id_fkey", RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_RESPONDENT_ID);
        public static final ForeignKey<RespondentPollResponseRecord, PollRecord> RESPONDENT_POLL_RESPONSE__RESPONDENT_POLL_RESPONSE_A_POLL_ID_FKEY = Internal.createForeignKey(
            Keys.POLL_PKEY, RespondentPollResponse.RESPONDENT_POLL_RESPONSE, "respondent_poll_response__respondent_poll_response_a_poll_id_fkey", RespondentPollResponse.RESPONDENT_POLL_RESPONSE.A_POLL_ID);
    }
}
