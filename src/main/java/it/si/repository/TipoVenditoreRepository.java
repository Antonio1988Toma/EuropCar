package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.TipoVenditore;

@Repository
public interface TipoVenditoreRepository extends JpaRepository<TipoVenditore, Integer> {

}
