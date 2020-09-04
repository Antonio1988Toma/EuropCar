package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.si.model.AnnoContabile;

@Repository
public interface AnnoContabileRepository extends JpaRepository<AnnoContabile, Integer>{

	@Query("SELECT a FROM AnnoContabile a Order By a.dataInizio")
	public List<AnnoContabile> findAllSort();
	
	@Query("SELECT TO_CHAR(MAX(a.dataFine),'YYYY') FROM AnnoContabile a")
	public int findLastDate();
}

