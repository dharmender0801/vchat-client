package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.VoiceChatUnsubEntity;

@Repository
@Transactional
public interface VoiceChatUnsubRepo extends JpaRepository<VoiceChatUnsubEntity, String> {

	
}
