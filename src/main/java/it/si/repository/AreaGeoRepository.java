package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.AreaGeo;

@Repository
public interface AreaGeoRepository extends JpaRepository<AreaGeo, Integer> {

}
