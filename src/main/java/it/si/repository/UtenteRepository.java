package it.si.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.si.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	@Query("SELECT u FROM Utente u JOIN u.venditore v WHERE v.id = :idVenditore")
	public List<Utente> findVenditore(@Param("idVenditore") int idVenditore);

}
