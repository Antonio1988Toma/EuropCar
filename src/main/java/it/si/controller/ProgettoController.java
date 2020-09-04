package it.si.controller;

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

import it.si.model.Progetto;
import it.si.service.ProgettoService;

@Controller
@RequestMapping("/progetto")
public class ProgettoController {
	
	@Autowired
	private ProgettoService progettoService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("progetto/progetto-list");
		maw.addObject("list", progettoService.findAll());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovoProgetto() {
		ModelAndView maw = new ModelAndView("progetto/progetto-edit");
		maw.addObject("progettoForm", new Progetto());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaProgetto(@PathVariable int id) {
		ModelAndView maw = new ModelAndView("progetto/progetto-edit");
		maw.addObject("progettoForm", progettoService.findById(id));
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaProgetto(@PathVariable int id) {
		progettoService.delete(id);
		return "redirect:/progetto/list";
	}
	
	@PostMapping("/save")
	public ModelAndView salvaProgetto(@Valid @ModelAttribute(name="progettoForm") Progetto progetto,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/progetto/progetto-edit");
		}
		else {
			maw.setViewName("redirect:/progetto/list");
			progettoService.save(progetto);
		}
		
		return maw;
	}
}
