package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.OrdineAcquisto;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto, Integer>{

	@Query("SELECT o FROM OrdineAcquisto o JOIN o.fornitore f WHERE f.id = :idFornitore")
	public List<OrdineAcquisto> findAllByFornitore(@Param("idFornitore") int idFornitore);
	
	@Query("SELECT o.id FROM OrdineAcquisto o JOIN o.fornitore f WHERE f.id = :idFornitore")
	public List<Integer> findOrdiniFornitore(@Param("idFornitore") int idFornitore);
	
	//RICERCA PER FORNITORE
	@Query("SELECT DISTINCT o.nomeOrdineAcquisto,s.nomeSpesaInvestimento,sc.sottocategoria,p.nomeProgetto,d.importo,d.quantita,o.numero,f.ragioneSociale "
			+ "FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o JOIN o.fornitore f "
			+ "JOIN d.spesaInvestimento s JOIN s.sottocategoria sc JOIN d.progetto p "
			+ "WHERE f.id = :idFornitore")
	public List<Object[]> cercaPerFornitore(@Param("idFornitore") int idFornitore);
	
	@Query("SELECT DISTINCT o.nomeOrdineAcquisto,s.nomeSpesaInvestimento,sc.sottocategoria,p.nomeProgetto,d.importo,d.quantita,o.numero,f.ragioneSociale "
			+ "FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o JOIN o.fornitore f "
			+ "JOIN d.spesaInvestimento s JOIN s.sottocategoria sc JOIN d.progetto p ")
	public List<Object[]> cercaPerFornitore();
	
	//RICERCA PER PROGETTO
	@Query("SELECT o.nomeOrdineAcquisto,s.nomeSpesaInvestimento,o.nomeOrdineAcquisto,d.importo,d.quantita,o.numero,f.ragioneSociale "
			+ "FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o JOIN d.spesaInvestimento s JOIN d.progetto p JOIN o.fornitore f "
			+ "WHERE p.id = :idProgetto")
	public List<Object[]> cercaPerProgetto(@Param("idProgetto") int idProgetto);
	
	@Query("SELECT o.nomeOrdineAcquisto,s.nomeSpesaInvestimento,o.nomeOrdineAcquisto,d.importo,d.quantita,o.numero,f.ragioneSociale "
			+ "FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o JOIN d.spesaInvestimento s JOIN d.progetto p JOIN o.fornitore f ")
	public List<Object[]> cercaPerProgetto();
	
	//RICERCA PER SOTTOCATEGORIA
	@Query("SELECT o.nomeOrdineAcquisto,s.nomeSpesaInvestimento,f.ragioneSociale,p.nomeProgetto,o.importo,o.numero,f.ragioneSociale "
			+ "FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o JOIN o.fornitore f "
			+ "JOIN d.spesaInvestimento s JOIN s.sottocategoria sc JOIN d.progetto p "
			+ "WHERE sc.id = :idSottocategoria")
	public List<Object[]> cercaPerSottocategoria(@Param("idSottocategoria") int idSottocategoria);
	
	@Query("SELECT o.nomeOrdineAcquisto,s.nomeSpesaInvestimento,f.ragioneSociale,p.nomeProgetto,o.importo,o.numero,f.ragioneSociale "
			+ "FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o JOIN o.fornitore f "
			+ "JOIN d.spesaInvestimento s JOIN s.sottocategoria sc JOIN d.progetto p ")
	public List<Object[]> cercaPerSottocategoria();

}
