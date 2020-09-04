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

import it.si.model.Area;
import it.si.service.AnnoContabileService;
import it.si.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {

	@Autowired
	private AreaService areaService;
	@Autowired
	private AnnoContabileService annoContabileService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("area/area-list");
		maw.addObject("list", areaService.findAllSort());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovaArea() {
		ModelAndView maw = new ModelAndView("area/area-edit");
		maw.addObject("areaForm", new Area());
		maw.addObject("listAnni", annoContabileService.findAllSort());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaArea(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("area/area-edit");
		session.setAttribute("IDArea", id);
		maw.addObject("areaForm", areaService.findById(id));
		maw.addObject("listAnni", annoContabileService.findAll());
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaArea(@PathVariable int id) {
		areaService.delete(id);
		return "redirect:/area/list";
	}
	
	@PostMapping("/save")
	public ModelAndView salvaArea(@Valid @ModelAttribute(name="areaForm") Area area,BindingResult br,HttpSession session) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/area/area-edit");
		}
		else {
			maw.setViewName("redirect:/area/list");
			areaService.save(area);
		}
		session.removeAttribute("IDArea");
		return maw;
	}

}
