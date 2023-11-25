package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.CallPatchLogicLogEntity;

@Repository
@Transactional
public interface CallPatchLogicLogRepo extends JpaRepository<CallPatchLogicLogEntity, String> {

}
