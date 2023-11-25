package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.ProductConfigEntity;

@Repository
@Transactional
public interface ProductConfigRepo extends JpaRepository<ProductConfigEntity, Integer> {

	public ProductConfigEntity findByUserType(String usertype);
}
