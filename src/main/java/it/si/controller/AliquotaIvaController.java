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

import it.si.model.AliquotaIva;
import it.si.service.AliquotaIvaService;

@Controller
@RequestMapping("/aliquotaIva")
public class AliquotaIvaController {

	@Autowired
	private AliquotaIvaService aliquotaIvaService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("aliquotaIva/aliquotaIva-list");
		maw.addObject("list", aliquotaIvaService.findAll());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovaAliquotaIva() {
		ModelAndView maw = new ModelAndView("aliquotaIva/aliquotaIva-edit");
		maw.addObject("aliquotaIvaForm", new AliquotaIva());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaAliquotaIva(@PathVariable int id) {
		ModelAndView maw = new ModelAndView("aliquotaIva/aliquotaIva-edit");
		maw.addObject("aliquotaIvaForm", aliquotaIvaService.findById(id));
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaAliquotaIva(@PathVariable int id) {
		aliquotaIvaService.delete(id);
		return "redirect:/aliquotaIva/list";
	}
	
	@PostMapping("/save")
	public ModelAndView salvaAliquota(@Valid @ModelAttribute(name="aliquotaIvaForm") AliquotaIva aliquota,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/aliquotaIva/aliquotaIva-edit");
		}
		else {
			maw.setViewName("redirect:/aliquotaIva/list");
			aliquotaIvaService.save(aliquota);
		}
		
		return maw;
	}
}
