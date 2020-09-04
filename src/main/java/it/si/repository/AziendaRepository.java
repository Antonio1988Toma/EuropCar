package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.Azienda;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Integer> {

}
