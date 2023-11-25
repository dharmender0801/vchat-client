package com.altruist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "tbl_callpatch_logic")
@Data
public class CallPatchLogicEntity {

	@Id
    @Column(name = "ANI", length = 15)
    private String ani;

    @Column(name = "CHATID", length = 10)
    private String chatId;

    @Column(name = "CIRCLEID", length = 10)
    private String circleId;

    @Column(name = "GENDER", length = 5)
    private String gender;

    @Column(name = "USER_IDENTITY")
    private Integer userIdentity;

    @Column(name = "REGION", length = 20)
    private String region;

    @Column(name = "CREATEDON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "successcalls")
    private Integer successCalls;

    @Column(name = "failedcalls")
    private Integer failedCalls;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "lastsuccesstime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSuccessTime;

    @Column(name = "lastfailedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFailedTime;

    @Column(name = "lastcalltime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCallTime;

    @Column(name = "state", length = 5, columnDefinition = "VARCHAR(5) default 'IDLE'")
    private String state;

    @Column(name = "login_status", length = 7, columnDefinition = "VARCHAR(7) default 'LOGIN'")
    private String loginStatus;

    @Column(name = "a_party", length = 10)
    private String aParty;

    @Column(name = "counts", columnDefinition = "INT default 0")
    private Integer counts;

    @Column(name = "line", length = 4)
    private String line;

    @Column(name = "login_counter")
    private Integer loginCounter;
}
