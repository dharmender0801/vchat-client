package com.altruist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "tbl_billing")
public class BillingEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWNO")
    private Long rowNo;

    @Column(name = "ANI")
    private String ani;

    @Column(name = "TOTAL_AMOUNT")
    private String totalAmount;

    @Column(name = "DEDUCTED_AMOUNT")
    private String deductedAmount;

    @Column(name = "USER_BALANCE")
    private String userBalance;

    @Column(name = "ISPREPAID")
    private String isPrepaid;

    @Column(name = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name = "RECORDSTATUS")
    private Integer recordStatus;

    @Column(name = "ERRORDESC")
    private String errorDesc;

    @Column(name = "ERROR_REASON")
    private String errorReason;

    @Column(name = "CIRCLEID")
    private String circleId;

    @Column(name = "TYPE_EVENT")
    private String typeEvent;

    @Column(name = "IS_EMM")
    private String isEmm;

    @Column(name = "MODE")
    private String mode;

    @Column(name = "PROCESS_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date processDateTime;

    @Column(name = "PREVIOUS_PROCESS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date previousProcessDate;

    @Column(name = "SRC")
    private String src;

    @Column(name = "NOOFATTEMPT")
    private Integer noOfAttempt;

    @Column(name = "SERVICENAME")
    private String serviceName;

    @Column(name = "NEXT_PROCESS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextProcessDate;

    @Column(name = "SYSRESPONSE")
    private String sysResponse;

    @Column(name = "sub_consent")
    private String subConsent;

    @Column(name = "ren_consent")
    private String renConsent;

    @Column(name = "SubscriberUID")
    private String subscriberUid;

    @Column(name = "total_noofattempt")
    private Integer totalNoOfAttempt;

    @Column(name = "DAILYCOUNTER")
    private Integer dailyCounter;

    @Column(name = "MONTHLYCOUNTER")
    private Integer monthlyCounter;

    @Column(name = "SUBSCRIPTION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionTime;
}
