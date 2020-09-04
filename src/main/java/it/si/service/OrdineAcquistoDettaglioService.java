package it.si.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.OrdineAcquistoDettaglio;
import it.si.repository.OrdineAcquistoDettaglioRepository;

@Service
public class OrdineAcquistoDettaglioService extends AbstractService<OrdineAcquistoDettaglioRepository,OrdineAcquistoDettaglio> {

	public List<OrdineAcquistoDettaglio> findAllByOrdineAcquisto(int idOrdine) {
		return repository.findAllByOrdineAcquisto(idOrdine);
	}
	
	public List<OrdineAcquistoDettaglio> cercaPerDate(Date dataDal, Date dataAl) {
		return repository.cercaPerDate(dataDal,dataAl);
	}

	public double calcolaImporto(int idOrdine) {
		return repository.calcolaImporto(idOrdine);
	}

}