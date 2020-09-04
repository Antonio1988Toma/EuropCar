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

import it.si.model.Sottocategoria;
import it.si.service.AreaService;
import it.si.service.SottocategoriaService;

@Controller
@RequestMapping("/sottocategoria")
public class SottocategoriaController {

	@Autowired
	private SottocategoriaService sottocategoriaService;
	@Autowired 
	private AreaService areaService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista(HttpSession session) {
		ModelAndView maw = new ModelAndView("sottocategoria/sottocategoria-list");
		maw.addObject("list", sottocategoriaService.findAll());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovaSottocategoria() {
		ModelAndView maw = new ModelAndView("sottocategoria/sottocategoria-edit");
		maw.addObject("listAree", areaService.findAll());
		maw.addObject("sottocategoriaForm", new Sottocategoria());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaSottocategoria(@PathVariable int id) {
		ModelAndView maw = new ModelAndView("sottocategoria/sottocategoria-edit");
		maw.addObject("listAree", areaService.findAll());
		maw.addObject("sottocategoriaForm", sottocategoriaService.findById(id));
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaSottocategoria(@PathVariable int id) {
		sottocategoriaService.delete(id);
		return "redirect:/sottocategoria/list";
	}
	
	@PostMapping("/save")
	public ModelAndView salvaSottocategoria(@Valid @ModelAttribute(name = "sottocategoriaForm") Sottocategoria sottocategoria, BindingResult bindingResult) {
		ModelAndView maw = new ModelAndView();

		if (bindingResult.hasErrors()) {
			maw.addObject("listaAree", areaService.findAll());
			maw.setViewName("/sottocategoria/sottocategoria-edit");
		} else {
			maw.setViewName("redirect:/sottocategoria/list");
			sottocategoriaService.save(sottocategoria);

		}
		return maw;
	}
	
	@GetMapping("/budget/{id}")
	public ModelAndView menuBudget(@PathVariable int id) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("redirect:/budget/search/" + id);
		return maw;
	}
}
