package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.SpesaInvestimento;

@Repository
public interface SpesaInvestimentoRepository extends JpaRepository<SpesaInvestimento, Integer>{
	
	@Query("SELECT s FROM SpesaInvestimento s JOIN s.sottocategoria a WHERE a.id = :idSottocategoria")
	public List<SpesaInvestimento> findAllBySottocategoria(@Param("idSottocategoria") int idSottocategoria);
}
