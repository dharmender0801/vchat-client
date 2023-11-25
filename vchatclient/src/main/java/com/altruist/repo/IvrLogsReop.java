package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.IvrLogsEntity;

@Repository
@Transactional
public interface IvrLogsReop extends JpaRepository<IvrLogsEntity, Integer> {

}
