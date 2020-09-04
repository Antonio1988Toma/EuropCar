package it.si.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.FatturaPassiva;
import it.si.repository.FatturaPassivaRepository;
import it.si.util.UDate;

@Service
public class FatturaPassivaService extends AbstractService<FatturaPassivaRepository,FatturaPassiva> {
	
	public List<FatturaPassiva> findAllByFornitore(int idFornitore) {
		return repository.findAllByFornitore(idFornitore);
	}
	
	public List<String[]> ricercaPerFornitore(int id) {
		List<Object[]> lista;
		if(id==0)
			lista = repository.cercaPerFornitore();
		else
			lista = repository.cercaPerFornitore(id);	
		return caricaListaPerFornitore(lista);
	}
	
	public List<String[]> caricaListaPerFornitore(List<Object[]> lista){
		List<String[]> fattureFornitore = new ArrayList<String[]>();
		for(int i = 0;i<lista.size();i++) {
			String[] fatture = new String[3];
			FatturaPassiva fp = repository.getOne((int)lista.get(i)[1]);
			fatture[0] = UDate.ctrlStringa(fp.getData());
			fatture[1] = fp.getNumero();
			fatture[2] = Double.toString((double)lista.get(i)[0]);
			fattureFornitore.add(fatture);
		}
		return fattureFornitore;
	}
	
	public List<String[]> ricercaPerPreventivo(int id) {
		List<Object[]> lista;
		if(id==0)
			lista = repository.cercaPerPreventivo();
		else
			lista = repository.cercaPerPreventivo(id);
		return caricaListaPerPreventivo(lista);
	}
	
	private List<String[]> caricaListaPerPreventivo(List<Object[]> lista) {
		List<String[]> fatturePreventivo = new ArrayList<String[]>();
		for(int i = 0;i<lista.size();i++) {
			String[] fatture = new String[4];
			FatturaPassiva fp = repository.getOne((int)lista.get(i)[1]);
			fatture[0] = fp.getFornitore().getRagioneSociale();
			fatture[1] = UDate.ctrlStringa(fp.getData());
			fatture[2] = fp.getNumero();
			fatture[3] = Double.toString((double)lista.get(i)[0]);
			fatturePreventivo.add(fatture);
		}
		return fatturePreventivo;
	}
	
	public List<String[]> ricercaPerSottocategoria(int id) {
		List<Object[]> lista;
		if(id==0)
			lista = repository.cercaPerSottocategoria();
		else
			lista = repository.cercaPerSottocategoria(id);
		return caricaListaPerSottocategoria(lista);
	}
	
	private List<String[]> caricaListaPerSottocategoria(List<Object[]> lista) {
		List<String[]> fatturePreventivo = new ArrayList<String[]>();
		for(int i = 0;i<lista.size();i++) {
			String[] fatture = new String[4];
			FatturaPassiva fp = repository.getOne((int)lista.get(i)[1]);
			fatture[0] = fp.getFornitore().getRagioneSociale();
			fatture[1] = UDate.ctrlStringa(fp.getData());
			fatture[2] = fp.getNumero();
			fatture[3] = Double.toString((double)lista.get(i)[0]);
			fatturePreventivo.add(fatture);
		}
		return fatturePreventivo;
	}
}
