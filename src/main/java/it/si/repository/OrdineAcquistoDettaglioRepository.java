package it.si.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.OrdineAcquistoDettaglio;

@Repository
public interface OrdineAcquistoDettaglioRepository extends JpaRepository<OrdineAcquistoDettaglio, Integer>{

	@Query("SELECT d FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o WHERE o.id = :idOrdine")
	public List<OrdineAcquistoDettaglio> findAllByOrdineAcquisto(@Param("idOrdine") int idOrdine);

	@Query("SELECT d "
			+ "FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o JOIN d.spesaInvestimento s JOIN s.sottocategoria "
			+ "WHERE o.data BETWEEN :dataDal AND :dataAl")
	public List<OrdineAcquistoDettaglio> cercaPerDate(@Param("dataDal") Date dataDal,@Param("dataAl") Date dataAl);

	@Query("SELECT SUM(d.importo * d.quantita) FROM OrdineAcquistoDettaglio d JOIN d.ordineAcquisto o WHERE o.id = :idOrdine")
	public double calcolaImporto(@Param("idOrdine") int idOrdine);
	
}
