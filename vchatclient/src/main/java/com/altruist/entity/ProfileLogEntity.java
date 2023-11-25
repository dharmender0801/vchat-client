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
@Table(name = "tbl_profile_log")
@Data
public class ProfileLogEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rowno")
    private Integer rowno;

    @Column(name = "datetime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    @Column(name = "ANI", length = 15, columnDefinition = "CHARACTER SET latin1 COLLATE latin1_swedish_ci")
    private String ani;

    @Column(name = "LANGID", length = 5)
    private String langid;

    @Column(name = "CIRCLEID", length = 10)
    private String circleid;

    @Column(name = "GENDER", length = 5)
    private String gender;

    @Column(name = "RECORDPROFILE", length = 100)
    private String recordProfile;

    @Column(name = "IS_ACTIVE", length = 5)
    private String isActive;
    
    @Column(name = "log_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logdatetime;
}
