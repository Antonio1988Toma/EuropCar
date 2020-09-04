package it.si.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
import it.si.model.SpesaInvestimento;
import it.si.service.AreaService;
import it.si.service.OrdineAcquistoDettaglioService;
import it.si.service.ProgettoService;
import it.si.service.SottocategoriaService;
import it.si.service.SpesaInvestimentoService;
import it.si.util.UDate;

@Controller
@RequestMapping("/budget")
public class BudgetController {
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private SottocategoriaService sottocategoriaService;
	@Autowired
	private SpesaInvestimentoService spesaInvestimentoService;
	@Autowired
	private ProgettoService progettoService;
	@Autowired
	private OrdineAcquistoDettaglioService ordineAcquistoDettaglioService;
	
	@GetMapping("/definizione/list")
	public ModelAndView caricaLista() {
		ModelAndView maw = new ModelAndView("budget/definizione-list");
		maw.addObject("listAree", areaService.findAll());
		return maw;
	}
	
	@GetMapping("/avanzamento/list")
	public ModelAndView caricaListaAvanzamento() {
		ModelAndView maw = new ModelAndView("budget/avanzamento-list");
		maw.addObject("listAree", areaService.findAll());
		return maw;
	}
	
	@GetMapping("/spesa-investimento/list")
	public ModelAndView caricaListaSottocategorie() {
		ModelAndView maw = new ModelAndView("budget/spesa-investimento-list");
		maw.addObject("listSottocategorie", sottocategoriaService.getAllSortByArea());
		return maw;
	}
	
	@GetMapping("/search/{id}")
	public ModelAndView caricaListaSottoCategorie(@PathVariable int id, HttpSession session) {
		
		ModelAndView maw = new ModelAndView("budget/definizione-list");
		maw.addObject("listAree", areaService.findAll());
		session.setAttribute("chiaveArea", id);
		maw.addObject("listSottocategorie", sottocategoriaService.findAllByArea(id));
		calcolaTotale(maw, id);
		return maw;
	}
	
	@GetMapping("/search-spesa/{id}")
	public ModelAndView caricaListaSpesa(@PathVariable int id, HttpSession session) {
		
		ModelAndView maw = new ModelAndView("budget/spesa-investimento-list");
		maw.addObject("listSottocategorie", sottocategoriaService.getAllSortByArea());
		session.setAttribute("chiaveSottocategoria", id);
		maw.addObject("listSpeseInvestimento", spesaInvestimentoService.findAllBySottocategoria(id));
		return maw;
	}
	
	@GetMapping("/add-spesa/{idSottocategoria}")
	public ModelAndView nuovaSpesa(@PathVariable int idSottocategoria) {
		ModelAndView maw = new ModelAndView("budget/spesa-investimento-edit");
		maw.addObject("listProgetti", progettoService.findAll());
		maw.addObject("Sottocategoria", sottocategoriaService.findById(idSottocategoria));
		maw.addObject("spesaForm", new SpesaInvestimento());
		return maw;
	}
	
	@GetMapping("/update-spesa/{id}")
	public ModelAndView modificaSpesa(@PathVariable int id, HttpSession session) {
		ModelAndView maw = new ModelAndView("budget/spesa-investimento-edit");
		maw.addObject("listProgetti", progettoService.findAll());
		maw.addObject("Sottocategoria", sottocategoriaService.findById((int)session.getAttribute("chiaveSottocategoria")));
		maw.addObject("spesaForm", spesaInvestimentoService.findById(id));
		return maw;
	}
	
	@GetMapping("/delete-spesa/{id}")
	public String eliminaSPesa(@PathVariable int id, HttpSession session) {
		spesaInvestimentoService.delete(id);
		return "redirect:/budget/search-spesa/" + session.getAttribute("chiaveSottocategoria");
	}
	
	@PostMapping("/save-spesa")
	public ModelAndView salvaSpesa(@Valid @ModelAttribute(name="spesaForm") SpesaInvestimento spesa,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/budget/spesa-investimento-edit");
		}
		else {
			maw.setViewName("redirect:/budget/search-spesa/" + spesa.getSottocategoria().getId());
			spesaInvestimentoService.save(spesa);
		}
		
		return maw;
	}
	
	@PostMapping("/update")
	public ModelAndView aggiornaBudget(@Valid @ModelAttribute(name="definizioneForm") Sottocategoria sottocategoria, HttpSession session,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if (br.hasErrors()) {
			maw.setViewName("/budget/definizione-edit");
		}
		else {
			maw.setViewName("redirect:/budget/search/" + session.getAttribute("chiaveArea"));
			sottocategoriaService.updateBudget(sottocategoria);
		}
		
		return maw;
	}
	
	@GetMapping("/search-avanzamento/{id}")
	public ModelAndView caricaListaSottoCategorieAvanzmento(@PathVariable int id, HttpSession session) {
		
		ModelAndView maw = new ModelAndView("budget/avanzamento-list");
		maw.addObject("listAree", areaService.findAll());
		session.setAttribute("chiaveArea", id);
		maw.addObject("listSottocategorie", sottocategoriaService.findAllByArea(id));
		calcolaTotale(maw, id);
		return maw;
	}
	
	public ModelAndView calcolaTotale(ModelAndView maw,int id) {
		List<Sottocategoria> lista = sottocategoriaService.findAllByArea(id);
		double totale = 0, totaleSpeso = 0, totaleAvanzamento = 0;
		for(int i = 0;i<lista.size();i++) {
			totale += lista.get(i).getBudget();
			totaleSpeso += lista.get(i).getBudgetSpeso();
			totaleAvanzamento +=  lista.get(i).getBudget() - lista.get(i).getBudgetSpeso();
		}
		maw.addObject("Totale",totale);
		maw.addObject("TotaleSpeso",totaleSpeso);
		maw.addObject("TotaleAvanzamento",totaleAvanzamento);
		return maw;
	}
		
	@GetMapping("/definisci/{id}")
	public ModelAndView definisciBudget(@PathVariable int id) {
		ModelAndView maw = new ModelAndView("budget/definizione-edit");
		maw.addObject("definizioneForm", sottocategoriaService.findById(id));
		return maw;
	}
	
	@GetMapping("/sottocategoria/{id}")
	public ModelAndView menuSottocategoria(@PathVariable int id, HttpSession session) {
		ModelAndView maw = new ModelAndView();
		session.setAttribute("chiaveArea", id);
		maw.setViewName("redirect:/sottocategoria/list");
		return maw;
	}
	
	@GetMapping("/riconciliazione/list")
	public ModelAndView riconcilia() {
		ModelAndView maw = new ModelAndView("budget/riconcilia");
		return maw;
	}
	
	@RequestMapping("/riconcilia")
	public String riconciliaBudget(@ModelAttribute("dataDal") String dal,@ModelAttribute("dataAl") String al) throws ParseException {
		Date dataDal = UDate.ctrlData(dal);
		Date dataAl  = UDate.ctrlData(al);
		sottocategoriaService.riconciliaBudget(ordineAcquistoDettaglioService.cercaPerDate(dataDal,dataAl));
		return "redirect:/menu/sottomenu-budget/";
	}

}
