package it.si.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.FatturaPassivaDettaglio;

@Repository
public interface FatturaPassivaDettaglioRepository extends JpaRepository<FatturaPassivaDettaglio, Integer>{

	@Query("SELECT d FROM FatturaPassivaDettaglio d JOIN d.fatturaPassiva fp WHERE fp.id = :idFattura")
	public List<FatturaPassivaDettaglio> findAllByFatturaPassiva(@Param("idFattura") int idFattura);
	
	@Query("SELECT d FROM FatturaPassivaDettaglio d JOIN d.spesaInvestimento s JOIN s.sottocategoria sc WHERE sc.id = :idSottocategoria")
	public List<FatturaPassivaDettaglio> findAllBySottocategoria(@Param("idSottocategoria") int idSottocategoria);

	@Query("SELECT d FROM FatturaPassivaDettaglio d JOIN d.spesaInvestimento s JOIN d.fatturaPassiva fp "
			+ "WHERE s.id = :idSpesa AND fp.data BETWEEN :dal AND :al")
	public List<FatturaPassivaDettaglio> cercaSpese(@Param("idSpesa")int idSpesa,@Param("dal")Date dal,@Param("al")Date al);


}
