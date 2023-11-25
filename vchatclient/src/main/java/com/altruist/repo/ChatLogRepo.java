package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.ChatLogEntity;

@Repository
@Transactional
public interface ChatLogRepo extends JpaRepository<ChatLogEntity, Integer> {

}
