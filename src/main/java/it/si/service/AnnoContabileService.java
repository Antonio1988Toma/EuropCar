package it.si.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.AnnoContabile;
import it.si.repository.AnnoContabileRepository;
import it.si.util.UDate;

@Service
public class AnnoContabileService  extends AbstractService<AnnoContabileRepository,AnnoContabile> {

	public List<AnnoContabile> findAllSort() {
		return repository.findAllSort();
	}
	
	public AnnoContabile nuovoAnno() throws ParseException {
		AnnoContabile anno = new AnnoContabile();
		int annoPrecedente = repository.findLastDate();
		int annoSuccessivo = annoPrecedente + 1;
		anno.setDataInizio(UDate.ctrlData("01/07/" + annoPrecedente));
		anno.setDataFine(UDate.ctrlData("30/06/" + annoSuccessivo));
		anno.setDescrizione("Anno " + annoPrecedente + "/" + annoSuccessivo);
		return anno;
		
	}

}
