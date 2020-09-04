package it.si.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.Area;
import it.si.repository.AreaRepository;

@Service
public class AreaService extends AbstractService<AreaRepository,Area> {
	
	public List<Area> findAllSort() {
		return repository.findAllSort();
	}
	
	public List<Area> findAllByAnno(int id) {
		return repository.findAllByAnno(id);
	}

	public void settaNullAnno(int id) {
		List<Area> lista = repository.findAllByAnno(id);
		for(int i=0;i<lista.size();i++) {
			Area area = repository.getOne(lista.get(i).getId());
			area.setAnnoContabile(null);
			repository.save(area);
		}
	}
}
