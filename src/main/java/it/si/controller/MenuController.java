package it.si.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {
	
	@GetMapping("/")
	public String start() {
		//return "redirect:/utente/utente-login";
		return "redirect:/anno-contabile/start";
		//return "redirect:/menu/principale";
	}
	@GetMapping("/menu/principale")
	public String caricaMenu() {
		return "menu/principale";
	}
	@GetMapping("/menu/{sottomenu}")
	public String caricaSottomenu(@PathVariable String sottomenu,HttpSession session) {
		//cleanSession(session);
		return "menu/" + sottomenu;
	}

	@GetMapping("/menu/sottomenu-archivio/{archivio}")
	public String caricaArchivio(@PathVariable String archivio) {
		return "redirect:/" + archivio + "/list";
	}
	
	@GetMapping("/menu/sottomenu-budget/{budget}")
	public String caricaBudget(@PathVariable String budget) {
		return "redirect:/budget/" + budget + "/list";
	}	
	
	@GetMapping("/menu/sottomenu-preventivo/{preventivo}")
	public String caricaPreventivo(@PathVariable String preventivo) {
		return "redirect:/" + preventivo + "/list";
	}
	
	@GetMapping("/menu/sottomenu-anno-contabile")
	public String caricaAnnoContabile() {
		return "redirect:/anno-contabile/list";
	}	
	
	@GetMapping("/menu/sottomenu-fattura-passiva/{fatturaPassiva}")
	public String caricaFatturaPassiva(@PathVariable String fatturaPassiva) {
		return "redirect:/fattura-passiva/" + fatturaPassiva;
	}	
	
	@GetMapping("/menu/sottomenu-ordine-acquisto/{ordineAcquisto}")
	public String caricaOrdineAcquisto(@PathVariable String ordineAcquisto) {
		return "redirect:/ordine-acquisto/" + ordineAcquisto;
	}	
	
	@GetMapping("/anno-contabile")
	public String caricaAnno(HttpSession session) {
		cleanSession(session);
		return "redirect:/anno-contabile/start";
	}
	
	@GetMapping("/menu/azienda")
	public String caricaAzienda() {
		return "redirect:/azienda/list";
	}	
	
	@GetMapping("/menu/area-geo")
	public String caricaArea() {
		return "redirect:/area-geo/list";
	}	
	
	private void cleanSession(HttpSession session) {
		Enumeration <String> attribute = session.getAttributeNames();
		while(attribute.hasMoreElements()) {
			String element = attribute.nextElement();
			session.removeAttribute(element);
		}
	}
	
}

