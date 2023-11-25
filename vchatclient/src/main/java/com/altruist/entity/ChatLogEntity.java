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
@Table(name = "tbl_chat_logs")
public class ChatLogEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rowId")
    private int rowId;

    @Column(name = "line")
    private Integer line;

    @Column(name = "Aparty")
    private String Aparty;

    @Column(name = "Achatid")
    private String Achatid;

    @Column(name = "ACircle")
    private String ACircle;

    @Column(name = "Duration")
    private Integer Duration;

    @Column(name = "Bparty")
    private String Bparty;

    @Column(name = "Bchatid")
    private String Bchatid;

    @Column(name = "CallStartDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CallStartDateTime;

    @Column(name = "CallEndDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CallEndDateTime;

    @Column(name = "ServiceSelect")
    private String ServiceSelect;

    @Column(name = "Dnis")
    private String Dnis;

    @Column(name = "callstatus")
    private String callstatus;

    @Column(name = "platform")
    private String platform;

    @Column(name = "Aparty_Gender")
    private String Aparty_Gender;

    @Column(name = "Bparty_Gender")
    private String Bparty_Gender;

    @Column(name = "pulses")
    private Integer pulses;

    @Column(name = "region")
    private String region;

   
}
