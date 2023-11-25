package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.ProfileLogEntity;

@Repository
@Transactional
public interface ProfileLogRepo extends JpaRepository<ProfileLogEntity, Integer> {

}
