package it.si.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.FatturaPassivaDettaglio;
import it.si.repository.FatturaPassivaDettaglioRepository;
@Service
public class FatturaPassivaDettaglioService extends AbstractService<FatturaPassivaDettaglioRepository,FatturaPassivaDettaglio> {
	
	public List<FatturaPassivaDettaglio> findAllByFatturaPassiva(int idFattura) {
		return repository.findAllByFatturaPassiva(idFattura);
	}

	public List<FatturaPassivaDettaglio> findAllBySottocategoria(int idSottocategoria) {
		return repository.findAllBySottocategoria(idSottocategoria);
	}

	public List<FatturaPassivaDettaglio> cercaSpese(int idSpesa,Date dal,Date al) {
		return repository.cercaSpese(idSpesa,dal,al);
	}
	
}
