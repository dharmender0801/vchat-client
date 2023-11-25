package com.altruist.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UtilityResultEntity {
	@Id
    private int id;
    private String outStr;
}
