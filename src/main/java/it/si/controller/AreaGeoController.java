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

import it.si.model.AreaGeo;
import it.si.service.AreaGeoService;

@Controller
@RequestMapping("/area-geo")
public class AreaGeoController {

	@Autowired
	private AreaGeoService areaGeoService;
	
	@GetMapping("/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("area-geo/area-geo-list");
		maw.addObject("list", areaGeoService.findAll());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView nuovaArea() {
		ModelAndView maw = new ModelAndView("area-geo/area-geo-edit");
		maw.addObject("areaForm", new AreaGeo());
		return maw;
	}
	
	@GetMapping("/update/{id}")
	public ModelAndView modificaArea(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("area-geo/area-geo-edit");
		session.setAttribute("IDArea", id);
		maw.addObject("areaForm", areaGeoService.findById(id));
		return maw;
	}
	
	@GetMapping("/delete/{id}")
	public String eliminaArea(@PathVariable int id) {
		areaGeoService.delete(id);
		return "redirect:/area-geo/list";
	}
	
	@PostMapping("/save")
	public ModelAndView salvaArea(@Valid @ModelAttribute(name="areaForm") AreaGeo areaGeo,BindingResult br,HttpSession session) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/area-geo/area-geo-edit");
		}
		else {
			maw.setViewName("redirect:/area-geo/list");
			areaGeoService.save(areaGeo);
		}
		session.removeAttribute("IDArea");
		return maw;
	}
}
