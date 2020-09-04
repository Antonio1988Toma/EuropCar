package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.Previsione;

@Repository
public interface PrevisioneRepository extends JpaRepository<Previsione, Integer> {

}
