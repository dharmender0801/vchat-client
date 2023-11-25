package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altruist.entity.VoiceChatEntity;

@Repository
@Transactional
public interface VoiceChatRepo extends JpaRepository<VoiceChatEntity, String> {

	VoiceChatEntity findByAni(String ani);
	
	VoiceChatEntity findByVoiceChatId(String voicechatid);
	
	VoiceChatEntity findTop1ByVoiceChatId(String voicechatid);
	
	
	@Modifying
	@Query("UPDATE VoiceChatEntity e SET e.gender = :gender WHERE e.ani = :ani")
	int updateByGender(@Param("ani") String ani, @Param("gender") String gender);

	@Modifying
	@Query("UPDATE VoiceChatEntity e SET e.age = :age WHERE e.ani = :ani")
	int updateByAge(@Param("ani") String ani, @Param("age") String age);
	
	@Modifying
	@Query("UPDATE VoiceChatEntity e SET e.langId = :langId WHERE e.ani = :ani")
	int updateByLangId(@Param("ani") String ani, @Param("langId") String langid);

	public int  deleteByAni(String ani);
}
