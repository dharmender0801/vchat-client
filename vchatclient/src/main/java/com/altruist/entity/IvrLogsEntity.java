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
@Table(name = "tbl_ivr_logs")
@Data
public class IvrLogsEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logid")
    private Integer logid;

    @Column(name = "ani", length = 20)
    private String ani;

    @Column(name = "dnis", length = 20)
    private String dnis;

    @Column(name = "pulse")
    private Integer pulse;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "circle", length = 20)
    private String circle;

    @Column(name = "starttime", length = 30)
    private String starttime;

    @Column(name = "endtime", length = 30)
    private String endtime;

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name = "historybutton", length = 700)
    private String historyButton;

    @Column(name = "recordstatus", length = 2)
    private String recordStatus;
    
    
    
}
