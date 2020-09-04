package it.si.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.si.model.Preventivo;
import it.si.service.FornitoreService;
import it.si.service.PreventivoService;

@Controller
@RequestMapping("/preventivo")
public class PreventivoController {

	@Autowired
	private PreventivoService preventivoService;
	@Autowired
	private FornitoreService fornitoreService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("preventivo/preventivo-list");
		maw.addObject("listFornitori", fornitoreService.findAll());
		return maw;
	}
	
	@GetMapping("/search/{id}")
	public ModelAndView caricaListaFornitori(@PathVariable int id, HttpSession session) {
		ModelAndView maw = new ModelAndView("preventivo/preventivo-list");
		maw.addObject("listFornitori", fornitoreService.findAll());
		session.setAttribute("chiaveFornitore", id);
		maw.addObject("listPreventivi", preventivoService.findAllByFornitore(id));
		return maw;
	}
	
	@GetMapping("/add/{idFornitore}")
	public ModelAndView nuovoPreventivo(@PathVariable int idFornitore) {
		ModelAndView maw = new ModelAndView("preventivo/preventivo-edit");
		maw.addObject("Fornitore", fornitoreService.findById(idFornitore));
		maw.addObject("preventivoForm", new Preventivo());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaPreventivo(@PathVariable int id, HttpSession session) {
		ModelAndView maw = new ModelAndView("preventivo/preventivo-edit");
		maw.addObject("Fornitore", fornitoreService.findById((int)session.getAttribute("chiaveFornitore")));
		maw.addObject("preventivoForm", preventivoService.findById(id));
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaPreventivo(@PathVariable int id, HttpSession session) {
		preventivoService.delete(id);
		return "redirect:/preventivo/search/" + session.getAttribute("chiaveFornitore");
	}
	
	@PostMapping("/save")
	public String salvaPreventivo(@ModelAttribute Preventivo preventivo) {
		preventivoService.save(preventivo);
		return "redirect:/preventivo/search/" + preventivo.getFornitore().getId();
	}
}
