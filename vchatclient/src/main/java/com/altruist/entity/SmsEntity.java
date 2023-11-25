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

@Data
@Entity
@Table(name = "tbl_sms")
public class SmsEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rowid")
    private Long rowid;

    @Column(name = "ani", length = 20)
    private String ani;

    @Column(name = "messageid")
    private String messageid;

    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    @Column(name = "msg", length = 400)
    private String msg;

    @Column(name = "dnis", length = 20)
    private String dnis;

    @Column(name = "status", length = 5)
    private String status;

    @Column(name = "type", length = 10)
    private String type;

    @Column(name = "priority")
    private Integer priority;

 
}
