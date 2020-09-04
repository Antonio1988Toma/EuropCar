package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.FatturaPassiva;

@Repository
public interface FatturaPassivaRepository extends JpaRepository<FatturaPassiva, Integer>{

	@Query("SELECT fp FROM FatturaPassiva fp JOIN fp.fornitore f WHERE f.id = :idFornitore")
	public List<FatturaPassiva> findAllByFornitore(@Param("idFornitore") int idFornitore);
	
	//RICERCA PER FORNITORE
	@Query("SELECT SUM(d.importo),f.id FROM FatturaPassivaDettaglio d JOIN d.fatturaPassiva f JOIN f.fornitore for "
			+ "WHERE for.id = :idFornitore "
			+ "GROUP BY f.id")
	public List<Object[]> cercaPerFornitore(@Param("idFornitore") int idFornitore);
	
	@Query("SELECT SUM(d.importo),f.id FROM FatturaPassivaDettaglio d JOIN d.fatturaPassiva f JOIN f.fornitore for GROUP BY f.id")
	public List<Object[]> cercaPerFornitore();
	
	//RICERCA PER PREVENTIVO
	@Query("SELECT SUM(d.importo),f.id FROM FatturaPassivaDettaglio d JOIN d.fatturaPassiva f JOIN d.preventivo p "
			+ "WHERE p.id = :idPreventivo "
			+ "GROUP BY f.id")
	public List<Object[]> cercaPerPreventivo(@Param("idPreventivo") int idPreventivo);
	
	@Query("SELECT SUM(d.importo),f.id FROM FatturaPassivaDettaglio d JOIN d.fatturaPassiva f JOIN d.preventivo p GROUP BY f.id")
	public List<Object[]> cercaPerPreventivo();
	
	//RICERCA PER SOTTOCATEGORIA
	@Query("SELECT SUM(d.importo),f.id FROM FatturaPassivaDettaglio d JOIN d.fatturaPassiva f JOIN d.spesaInvestimento s JOIN s.sottocategoria sc "
			+ "WHERE sc.id = :idSottocategoria "
			+ "GROUP BY f.id")
	public List<Object[]> cercaPerSottocategoria(@Param("idSottocategoria") int idSottocategoria);
	
	@Query("SELECT SUM(d.importo),f.id FROM FatturaPassivaDettaglio d JOIN d.fatturaPassiva f JOIN d.spesaInvestimento s JOIN s.sottocategoria sc GROUP BY f.id")
	public List<Object[]> cercaPerSottocategoria();

}
