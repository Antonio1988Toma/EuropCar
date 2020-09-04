package it.si.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.si.model.DistintaPagamento;

@Repository
public interface DistintaPagamentoRepository extends JpaRepository<DistintaPagamento, Integer> {

}
