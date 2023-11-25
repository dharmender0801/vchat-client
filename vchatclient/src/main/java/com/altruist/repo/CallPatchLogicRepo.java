package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.CallPatchLogicEntity;

@Repository
@Transactional
public interface CallPatchLogicRepo extends JpaRepository<CallPatchLogicEntity, String> {

	public CallPatchLogicEntity findByAni(String ani);

	public int deleteByAni(String ani);

}
