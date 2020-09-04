package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.AliquotaIva;

@Repository
public interface AliquotaIvaRepository extends JpaRepository<AliquotaIva, Integer>{

}
