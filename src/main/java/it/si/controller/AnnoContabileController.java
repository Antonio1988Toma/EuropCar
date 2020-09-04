package it.si.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.si.model.AnnoContabile;
import it.si.service.AnnoContabileService;

@Controller
@RequestMapping("/anno-contabile")
public class AnnoContabileController {

	@Autowired
	private AnnoContabileService annoContabileService;
	
	@GetMapping("/start")
	public ModelAndView caricaStart() {
		ModelAndView maw = new ModelAndView("anno-contabile/anno");
		maw.addObject("listAnni", annoContabileService.findAllSort());
		return maw;
	}
	
	@GetMapping("/vai-al-menu/{idAnno}")
	public ModelAndView caricaMenu(@PathVariable int idAnno,HttpSession session) {
		ModelAndView maw = new ModelAndView("redirect:/menu/principale");
		session.setAttribute("ChiaveAnno", idAnno);
		AnnoContabile anno = annoContabileService.findById(idAnno);
		session.setAttribute("AnnoContabile", anno.getDescrizione());
		return maw;
	}
	
	@GetMapping("/list")
	public ModelAndView caricaListaAnni() {
		ModelAndView maw = new ModelAndView("anno-contabile/anno-list");
		maw.addObject("listAnni", annoContabileService.findAllSort());
		return maw;
	}
	
	@GetMapping("/anno-add")
	public String nuovoAnno() throws ParseException {
		AnnoContabile anno = annoContabileService.nuovoAnno();
		annoContabileService.save(anno);
		return "redirect:/anno-contabile/list";
	}
	
}
