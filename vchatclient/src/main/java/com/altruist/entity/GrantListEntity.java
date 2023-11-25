package com.altruist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_grant_list")
public class GrantListEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRANTID")
    private Long grantId;

    @Column(name = "GRANTTYPE")
    private String grantType;

    @Column(name = "AUTHRIZED")
    private String authorized;

    @Column(name = "AUTHRIZEDCHATID")
    private String authorizedChatId;

    @Column(name = "AUTHRIZER")
    private String authorizer;

    @Column(name = "AUTHRIZERCHATID")
    private String authorizerChatId;
}
