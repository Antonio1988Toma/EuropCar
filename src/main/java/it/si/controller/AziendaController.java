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

import it.si.model.Azienda;
import it.si.model.Gruppo;
import it.si.service.AziendaService;
import it.si.service.GruppoService;


@Controller
@RequestMapping("/azienda")
public class AziendaController {

	@Autowired
	private AziendaService aziendaService;
	@Autowired
	private GruppoService gruppoService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("azienda/azienda-list");
		maw.addObject("list", aziendaService.findAll());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovaAzienda() {
		ModelAndView maw = new ModelAndView("azienda/azienda-edit");
		maw.addObject("aziendaForm", new Azienda());
		maw.addObject("listGruppi", gruppoService.findAll());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaAzienda(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("azienda/azienda-edit");
		session.setAttribute("IDAzienda", id);
		maw.addObject("aziendaForm", aziendaService.findById(id));
		maw.addObject("listGruppi", gruppoService.findAll());
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaAzienda(@PathVariable int id) {
		aziendaService.delete(id);
		return "redirect:/azienda/list";
	}
	
	@PostMapping("/save")
	public ModelAndView salvaAzienda(@Valid @ModelAttribute(name="aziendaForm") Azienda azienda,BindingResult br,HttpSession session) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/azienda/azienda-edit");
		}
		else {
			maw.setViewName("redirect:/azienda/list");
			aziendaService.save(azienda);
		}
		session.removeAttribute("IDAzienda");
		return maw;
	}
	
	@GetMapping("/azienda-gruppo-list")
	public ModelAndView caricaListaGruppi() {
		ModelAndView maw = new ModelAndView("azienda/azienda-gruppo-list");
		maw.addObject("listGruppi", gruppoService.findAll());
		return maw;
	}
	
	@GetMapping("/azienda-gruppo-add")
	public ModelAndView nuovogruppo() {
		ModelAndView maw = new ModelAndView("azienda/azienda-gruppo-edit");
		maw.addObject("gruppoForm", new Gruppo());
		return maw;
	}
	
	@GetMapping("/azienda-gruppo-update/{id}")
	public ModelAndView modificaGruppo(@PathVariable int id) {
		ModelAndView maw = new ModelAndView("azienda/azienda-gruppo-edit");
		maw.addObject("gruppoForm", gruppoService.findById(id));
		return maw;
	}
	
	@GetMapping("/azienda-gruppo-delete/{id}")
	public String eliminaGruppo(@PathVariable int id) {
		gruppoService.delete(id);
		return "redirect:/azienda/azienda-gruppo-list";
	}
	
	@PostMapping("/azienda-gruppo-save")
	public ModelAndView salvaGruppo(@Valid @ModelAttribute(name="gruppoForm") Gruppo gruppo,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/azienda/azienda-gruppo-edit");
		}
		else {
			maw.setViewName("redirect:/azienda/azienda-gruppo-list");
			gruppoService.save(gruppo);
		}
		return maw;
	}
}
