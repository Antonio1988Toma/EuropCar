package it.si.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.si.model.Utente;
import it.si.repository.UtenteRepository;

@Service
public class UtenteService extends AbstractService<UtenteRepository,Utente> {

	public boolean verificaAccesso(String username, String password, Integer id) {
		boolean accesso = false;
		List<Utente> utente = repository.findVenditore(id);
		if(utente.size()>0) {
			if(utente.get(0).getUsername().equals(username) && utente.get(0).getPassword().equals(password)) {
				accesso = true;
			}
		}
		return accesso;
	}

}
