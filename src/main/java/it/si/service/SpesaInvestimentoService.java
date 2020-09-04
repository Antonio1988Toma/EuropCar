package it.si.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.SpesaInvestimento;
import it.si.repository.SpesaInvestimentoRepository;

@Service
public class SpesaInvestimentoService extends AbstractService<SpesaInvestimentoRepository,SpesaInvestimento>{

	public List<SpesaInvestimento> findAllBySottocategoria(int idSottocategoria) {
		return repository.findAllBySottocategoria(idSottocategoria);
	}
}