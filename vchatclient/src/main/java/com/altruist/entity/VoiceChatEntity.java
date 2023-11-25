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
import lombok.ToString;

@Data
@Entity
@Table(name = "tbl_voicechat")
public class VoiceChatEntity {

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date subDateTime;

	@Column(name = "unsub_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date unsubDateTime;

	@Column(name = "last_billed_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastBilledDate;

	@Column(name = "next_billed_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date nextBilledDate;

	@Column(name = "recordstatus")
	private String recordStatus;

	@Column(name = "user_type")
	private String userType;

	@Column(name = "recordprofile")
	private String recordProfile;

	@Column(name = "subamount")
	private Long subAmount;

	@Column(name = "CAMPAIGNID")
	private Long cmpid;

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

	@Column(name = "gender_update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date genderUpdateDate;

	@Column(name = "GENDER_UPDATE_MODE")
	private String genderupdatemode;

	@Column(name = "VENDOR_ID")
	private String vendorid;

	@Column(name = "gendermode")
	private String genderMode;

	@Column(name = "fallback")
	private Integer fallback;

	@Column(name = "grace")
	private Integer grace;

	@Column(name = "offer")
	private String offer;

	@Column(name = "send_message_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendMessageDate;

	@Override
	public String toString() {
		return "VoiceChatEntity [ ani=" + ani + ", gender=" + gender + ", age=" + age + ", operatorId=" + operatorId
				+ ", circleId=" + circleId + ", langId=" + langId + ", dnis=" + dnis + ", unsubDnis=" + unsubDnis
				+ ", subDateTime=" + subDateTime + ", unsubDateTime=" + unsubDateTime + ", lastBilledDate="
				+ lastBilledDate + ", nextBilledDate=" + nextBilledDate + ", recordStatus=" + recordStatus
				+ ", userType=" + userType + ", recordProfile=" + recordProfile + ", subAmount=" + subAmount
				+ ", cmpid=" + cmpid + ", planId=" + planId + ", subscriptionId=" + subscriptionId + ", mact=" + mact
				+ ", mdact=" + mdact + ", renPack=" + renPack + ", defaultAmount=" + defaultAmount + ", voiceChatId="
				+ voiceChatId + ", fallback=" + fallback + ", grace=" + grace + ", genderUpdateDate=" + genderUpdateDate
				+ ", genderMode=" + genderMode + ", offer=" + offer + ", sendMessageDate=" + sendMessageDate + "]";
	}

}
