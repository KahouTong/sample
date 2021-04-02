package com.app.repository.covid;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;

public interface CovidCasesRepository extends JpaRepository<CovidCasesAreaEntity, UUID>  {
	
	// Practical bonus
	// Change the Query above to Spring JPA Query or JPQL
	// Simple SQL Should not use native query as change of db the query syntax need to be changed. 
	// If use Spring JPQL, the SQL below can be maintained even change of DB
	
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordsHQL(Pageable pageable);
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordswithsizeHQL(Pageable pageable);
	
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast2RecordsHQL();
}