package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.Fornitore;

@Repository
public interface FornitoreRepository extends JpaRepository<Fornitore, Integer>{

}
