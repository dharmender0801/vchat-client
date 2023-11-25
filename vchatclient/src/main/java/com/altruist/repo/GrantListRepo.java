package com.altruist.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.altruist.entity.GrantListEntity;

@Repository
@Transactional
public interface GrantListRepo extends JpaRepository<GrantListEntity, Long> {

	public Long countByAuthorizer(String ani);

	public GrantListEntity findByAuthorizerAndGrantType(String ani,String granttype); 
	
	public int deleteByAuthorizerAndAuthorizedChatId(String authorizer, String authorizedchatid);

	public int deleteByAuthorizer(String ani);
	
	public Long countByAuthorizerChatIdAndGrantType(String chatid,String granttype);
	
//	SELECT COUNT(*) FROM TBL_GRANT_LIST WHERE (AUTHRIZERCHATID\='<CHATID>' OR AUTHRIZEDCHATID\='<CHATID>') and GRANTTYPE\='BUSY'
	public Long countByAuthorizerChatIdOrAuthorizedChatIdAndGrantType(String chatid,String chatid1,String granttype);
	
//	SELECT COUNT(*) FROM TBL_GRANT_LIST WHERE AUTHRIZEDCHATID\='<CHATID>' and GRANTTYPE\='BLOCK' AND AUTHRIZER\='<ANI>'
	public Long countByAuthorizedChatIdAndGrantTypeAndAuthorizer(String chatid, String granttype, String ani);
	
	
	
}
