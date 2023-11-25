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
@Table(name="tbl_product_info")
public class ProductConfigEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private String price;

    @Column(name = "validity")
    private Integer validity;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "productid")
    private String productId;

    @Column(name = "periodicity")
    private String periodicity;

    @Column(name = "user_type")
    private String userType;
}
