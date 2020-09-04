package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.Venditore;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore, Integer> {

}
