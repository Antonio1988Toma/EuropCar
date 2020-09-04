package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.Gruppo;

@Repository
public interface GruppoRepository extends JpaRepository<Gruppo, Integer> {

}
