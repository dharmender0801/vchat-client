package com.altruist.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.ProfileEntity;

@Repository
@Transactional
public interface ProfileRepo extends JpaRepository<ProfileEntity, Integer> {

	public List<ProfileEntity> findTop50ByGenderAndIsActiveOrderByRownoDesc(String gender, String status);

	public List<ProfileEntity> findTop50ByRecordProfileAndIsActiveOrderByRownoDesc(String gender, String status);

	public ProfileEntity findByAni(String ani);

	public int deleteByAni(String ani);
}
