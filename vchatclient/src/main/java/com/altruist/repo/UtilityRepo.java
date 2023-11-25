package com.altruist.repo;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altruist.entity.UtilityResultEntity;

@Repository
@Transactional
public interface UtilityRepo extends JpaRepository<UtilityResultEntity, Integer> {

	@Query(nativeQuery = true, value = "CALL UTILITY(:inUserId, :inFlag, :inValue1, :inValue2, :inValue3, :inValue4, :inValue5, :inValue6, :inValue7, :inValue8, @OUT_STR)")
    void executeUtility(
        @Param("inUserId") String inUserId,
        @Param("inFlag") String inFlag,
        @Param("inValue1") String inValue1,
        @Param("inValue2") String inValue2,
        @Param("inValue3") String inValue3,
        @Param("inValue4") String inValue4,
        @Param("inValue5") String inValue5,
        @Param("inValue6") String inValue6,
        @Param("inValue7") String inValue7,
        @Param("inValue8") String inValue8
    );
	    
	 
	 
	    
	



}
