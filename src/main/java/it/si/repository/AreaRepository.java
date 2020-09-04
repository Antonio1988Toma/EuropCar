package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

	@Query("SELECT a FROM Area a Order By a.nomeArea")
	public List<Area> findAllSort();
	
	@Query("SELECT a FROM Area a JOIN a.annoContabile an WHERE an.id = :idAnno")
	public List<Area> findAllByAnno(@Param("idAnno") int idAnno);
	
}
