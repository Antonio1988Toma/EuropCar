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

import it.si.model.Fornitore;
import it.si.service.FornitoreService;

@Controller
@RequestMapping("/fornitore")
public class FornitoreController {
	
	@Autowired
	private FornitoreService fornitoreService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("fornitore/fornitore-list");
		maw.addObject("list", fornitoreService.findAll());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovoFornitore() {
		ModelAndView maw = new ModelAndView("fornitore/fornitore-edit");
		maw.addObject("fornitoreForm", new Fornitore());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaFornitore(@PathVariable int id) {
		ModelAndView maw = new ModelAndView("fornitore/fornitore-edit");
		maw.addObject("fornitoreForm", fornitoreService.findById(id));
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaFornitore(@PathVariable int id) {
		fornitoreService.delete(id);
		return "redirect:/fornitore/list";
	}
	
	
	@PostMapping("/save")
	public ModelAndView salvaFornitore(@Valid @ModelAttribute(name="fornitoreForm") Fornitore fornitore,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/fornitore/fornitore-edit");
		}
		else {
			maw.setViewName("redirect:/fornitore/list");
			fornitoreService.save(fornitore);
		}
		
		return maw;
	}
}
