package it.si.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.si.model.TipoVenditore;
import it.si.model.Utente;
import it.si.model.Venditore;
import it.si.service.TipoVenditoreService;
import it.si.service.UtenteService;
import it.si.service.VenditoreService;

@Controller
@RequestMapping("/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	@Autowired
	private VenditoreService venditoreService;
	@Autowired
	private TipoVenditoreService tipoVenditoreService;
	
	
	@PostMapping("/login")
	public ModelAndView login(@Valid @ModelAttribute(name="loginForm") Utente utente,BindingResult br,HttpSession session) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/utente/utente-login");
			maw.addObject("listVenditori", venditoreService.findAll());
			session.setAttribute("Accesso", "Errato");
		}
		else {
			if(utenteService.verificaAccesso(utente.getUsername(),utente.getPassword(),utente.getVenditore().getId())) {
				maw.setViewName("redirect:/menu/principale");
				utenteService.save(utente);
				session.removeAttribute("Accesso");
			}
			else {
				maw.setViewName("/utente/utente-login");
				maw.addObject("listVenditori", venditoreService.findAll());
				session.setAttribute("Accesso", "Errato");
			}
		}
		
		return maw;
	}
	
	
	@GetMapping("/utente-login")
	public ModelAndView login() {
		ModelAndView maw = new ModelAndView("utente/utente-login");
		maw.addObject("listVenditori", venditoreService.findAll());
		maw.addObject("loginForm", new Utente());
		return maw;
	}
	
	@GetMapping("/utente-registrazione")
	public ModelAndView registrazione() {
		ModelAndView maw = new ModelAndView("utente/utente-registrazione");
		maw.addObject("listVenditori", venditoreService.findAll());
		maw.addObject("registrazioneForm", new Utente());
		return maw;
	}
	
	
	@PostMapping("/registrazione-save")
	public ModelAndView salvaAnno(@Valid @ModelAttribute(name="registrazioneForm") Utente utente,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/utente/utente-registrazione");
		}
		else {
			maw.setViewName("redirect:/utente/utente-login");
			utenteService.save(utente);
		}
		
		return maw;
	}
	
	@GetMapping("/venditore-list")
	public ModelAndView caricaVenditori() {
		ModelAndView maw = new ModelAndView("utente/venditore-list");
		maw.addObject("listVenditori", venditoreService.findAll());
		return maw;
	}
	
	@GetMapping("/venditore-add")
	public ModelAndView nuovoVenditore() {
		ModelAndView maw = new ModelAndView("utente/venditore-edit");
		maw.addObject("listTipi", tipoVenditoreService.findAll());
		maw.addObject("venditoreForm", new Venditore());
		return maw;
	}
	
	@GetMapping("/venditore-update/{id}")
	public ModelAndView modificaVenditore(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("utente/venditore-edit");
		maw.addObject("listTipi", tipoVenditoreService.findAll());
		session.setAttribute("ChiaveTipo", venditoreService.findById(id).getTipoVenditore().getId());
		maw.addObject("venditoreForm", venditoreService.findById(id));
		return maw;
	}
	
	@GetMapping("/venditore-delete/{id}")
	public String eliminaVenditore(@PathVariable int id) {
		venditoreService.delete(id);
		return "redirect:/utente/venditore-list";
	}
	
	@PostMapping("/venditore-save")
	public ModelAndView salvaVenditore(@Valid @ModelAttribute(name="venditoreForm") Venditore venditore,BindingResult br,HttpSession session) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/utente/venditore-edit");
		}
		else {
			maw.setViewName("redirect:/utente/venditore-list");
			venditoreService.save(venditore);
		}
		session.removeAttribute("ChiaveTipo");
		return maw;
	}
	
	@GetMapping("/tipo-venditore-list")
	public ModelAndView caricaTipi() {
		ModelAndView maw = new ModelAndView("utente/tipo-venditore-list");
		maw.addObject("listTipi", tipoVenditoreService.findAll());
		return maw;
	}
	
	@GetMapping("/tipo-venditore-add")
	public ModelAndView nuovoTipo() {
		ModelAndView maw = new ModelAndView("utente/tipo-venditore-edit");
		maw.addObject("tipoForm", new TipoVenditore());
		return maw;
	}
	
	@GetMapping("/tipo-venditore-update/{id}")
	public ModelAndView modificaTipo(@PathVariable int id) {
		ModelAndView maw = new ModelAndView("utente/tipo-venditore-edit");
		maw.addObject("tipoForm", tipoVenditoreService.findById(id));
		return maw;
	}
	
	@GetMapping("/tipo-venditore-delete/{id}")
	public String eliminaAnno(@PathVariable int id) {
		tipoVenditoreService.delete(id);
		return "redirect:/utente/tipo-venditore-list";
	}
	
	@PostMapping("/tipo-venditore-save")
	public ModelAndView salvaAnno(@Valid @ModelAttribute(name="tipoForm") TipoVenditore tipo,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/utente/tipo-venditore-edit");
		}
		else {
			maw.setViewName("redirect:/utente/tipo-venditore-list");
			tipoVenditoreService.save(tipo);
		}
		return maw;
	}

}
