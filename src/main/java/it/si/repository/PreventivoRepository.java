package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Preventivo;

@Repository
public interface PreventivoRepository extends JpaRepository<Preventivo, Integer>{

	@Query("SELECT p FROM Preventivo p JOIN p.fornitore f WHERE f.id = :idFornitore")
	public List<Preventivo> findAllByFornitore(@Param("idFornitore") int idFornitore);
}
