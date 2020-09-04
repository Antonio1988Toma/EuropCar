package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.Progetto;

@Repository
public interface ProgettoRepository extends JpaRepository<Progetto, Integer>{

}
