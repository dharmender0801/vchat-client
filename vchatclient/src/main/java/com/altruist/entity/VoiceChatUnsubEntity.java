package com.altruist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_voicechat_unsub")
public class VoiceChatUnsubEntity {
	@Id
    @Column(name = "ani")
    private String ani;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "operatorid")
    private String operatorId;

    @Column(name = "CIRCLEID")
    private String circleId;

    @Column(name = "langid")
    private String langId;

    @Column(name = "dnis")
    private String dnis;

    @Column(name = "unsubdnis")
    private String unsubDnis;

    @Column(name = "sub_date_time")
    private Date subDateTime;

    @Column(name = "unsub_date_time")
    private Date unsubDateTime;

    @Column(name = "last_billed_date")
    private Date lastBilledDate;

    @Column(name = "next_billed_date")
    private Date nextBilledDate;

    @Column(name = "recordstatus")
    private String recordStatus;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "recordprofile", length = 300)
    private String recordProfile;

    @Column(name = "CAMPAIGNID")
    private Long campaignId;

    @Column(name = "planid")
    private Long planId;

    @Column(name = "subscriptionid")
    private Long subscriptionId;

    @Column(name = "mact")
    private String mact;

    @Column(name = "mdact")
    private String mdact;

    @Column(name = "ren_pack")
    private String renPack;

    @Column(name = "default_amount")
    private Integer defaultAmount;

    @Column(name = "voicechatid")
    private String voiceChatId;

    @Column(name = "subamount")
    private Long subAmount;

    @Column(name = "ACTIVE_LEVEL")
    private String activeLevel;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name = "gendermode")
    private String genderMode;

    @Column(name = "gender_datetime")
    private Date genderDateTime;

    @Column(name = "SubscriberUID")
    private String subscriberUid;

    @Column(name = "GENDER_UPDATE_DATE")
    private Date genderUpdateDate;

    @Column(name = "gender_update_mode")
    private String genderUpdateMode;

    @Column(name = "VENDOR_ID", columnDefinition = "utf8mb4_0900_ai_ci")
    private String vendorId;
    
    @Column(name="UNSUBMODE")
    private String unsubmode;


}
