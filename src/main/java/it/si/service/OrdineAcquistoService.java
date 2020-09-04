package it.si.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.OrdineAcquisto;
import it.si.repository.OrdineAcquistoRepository;

@Service
public class OrdineAcquistoService extends AbstractService<OrdineAcquistoRepository,OrdineAcquisto> {

	public List<OrdineAcquisto> findAllByFornitore(int idFornitore) {
		return repository.findAllByFornitore(idFornitore);
	}
	
	public List<String[]> ricercaPerFornitore(int id) {
		List<Object[]> lista;
		if(id==0)
			lista = repository.cercaPerFornitore();
		else
			lista = repository.cercaPerFornitore(id);	
		return caricaListaFornitori(lista);
	}
	
	public List<String[]> caricaListaFornitori(List<Object[]> lista){
		List<String[]> ordini = new ArrayList<String[]>();
		for(int i=0;i<lista.size();i++) {
			String[] ordine = new String[6];
			ordine[0] = (String)lista.get(i)[6] + " / " + (String)lista.get(i)[0];
			ordine[1] = (String)lista.get(i)[1];
			ordine[2] = (String)lista.get(i)[2];
			ordine[3] = (String)lista.get(i)[3];
			ordine[4] = Double.toString((double) lista.get(i)[4] * (int)lista.get(i)[5]);
			ordine[5] = (String)lista.get(i)[7];
			ordini.add(ordine);
		}
		return ordini;
	}
	
	public List<String[]> ricercaPerProgetto(int id) {
		List<Object[]> lista;
		if(id==0)
			lista = repository.cercaPerProgetto();
		else
			lista = repository.cercaPerProgetto(id);	
		return caricaListaProgetti(lista);
	}
	
	public List<String[]> caricaListaProgetti(List<Object[]> lista){
		List<String[]> ordini = new ArrayList<String[]>();
		for(int i=0;i<lista.size();i++) {
			String[] ordine = new String[6];
			ordine[0] = (String)lista.get(i)[5] + " / " + (String)lista.get(i)[0];
			ordine[1] = (String)lista.get(i)[1];
			ordine[2] = (String)lista.get(i)[2];
			ordine[3] = Double.toString((double) lista.get(i)[3]);
			ordine[4] = Integer.toString((int) lista.get(i)[4]);
			ordine[5] = (String)lista.get(i)[6];
			ordini.add(ordine);
		}
		return ordini;
	}
	
	public List<String[]> ricercaPerSottocategoria(int id) {
		List<Object[]> lista;
		if(id==0)
			lista = repository.cercaPerSottocategoria();
		else
			lista = repository.cercaPerSottocategoria(id);	
		return caricaListaSottocategoria(lista);
	}
	
	public List<String[]> caricaListaSottocategoria(List<Object[]> lista){
		List<String[]> ordini = new ArrayList<String[]>();
		for(int i=0;i<lista.size();i++) {
			String[] ordine = new String[6];
			ordine[0] = (String)lista.get(i)[5] + " / " + (String)lista.get(i)[0];
			ordine[1] = (String)lista.get(i)[1];
			ordine[2] = (String)lista.get(i)[2];
			ordine[3] = (String)lista.get(i)[3];
			ordine[4] = Double.toString((double) lista.get(i)[4]);
			ordine[5] = (String)lista.get(i)[6];
			ordini.add(ordine);
		}
		return ordini;
	}
	
	


}
