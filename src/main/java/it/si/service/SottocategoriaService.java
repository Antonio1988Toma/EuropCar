package it.si.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.OrdineAcquistoDettaglio;
import it.si.model.Sottocategoria;
import it.si.repository.SottocategoriaRepository;

@Service
public class SottocategoriaService extends AbstractService<SottocategoriaRepository,Sottocategoria>{

	public List<Sottocategoria> findAllByArea(int idArea) {
		return repository.findAllByArea(idArea);
	}
	
	public List<Sottocategoria> getAllSortByArea() {
		return repository.findAllSortByArea();
	}
	
	public void updateBudget(Sottocategoria sottocategoria) {
		Sottocategoria sc = findById(sottocategoria.getId());
		sc.setBudget(sottocategoria.getBudget());
		save(sc);
	}


	public void riconciliaBudget(List<OrdineAcquistoDettaglio> lista) {

		for(int i=0;i<lista.size();i++) {
			double totale = lista.get(i).getImporto() * lista.get(i).getQuantita();
			Sottocategoria s = repository.getOne(lista.get(i).getSpesaInvestimento().getSottocategoria().getId());
			s.setBudgetSpeso(s.getBudgetSpeso() + totale);
			repository.save(s);
		}
		
	}
}
