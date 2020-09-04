package it.si.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.Preventivo;
import it.si.repository.PreventivoRepository;

@Service
public class PreventivoService extends AbstractService<PreventivoRepository,Preventivo>{

	public List<Preventivo> findAllByFornitore(int idFornitore) {
		return repository.findAllByFornitore(idFornitore);
	}

}
