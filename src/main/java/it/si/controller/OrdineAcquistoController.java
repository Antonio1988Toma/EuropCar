package it.si.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.si.model.OrdineAcquisto;
import it.si.model.OrdineAcquistoDettaglio;
import it.si.model.SpesaInvestimento;
import it.si.service.FornitoreService;
import it.si.service.OrdineAcquistoDettaglioService;
import it.si.service.OrdineAcquistoService;
import it.si.service.ProgettoService;
import it.si.service.SottocategoriaService;
import it.si.service.SpesaInvestimentoService;

@Controller
@RequestMapping("/ordine-acquisto")
public class OrdineAcquistoController {
	
	@Autowired	
	private SpesaInvestimentoService spesaInvestimentoService;
	@Autowired	
	private SottocategoriaService sottocategoriaService;
	@Autowired
	private FornitoreService fornitoreService;
	@Autowired
	private ProgettoService progettoService;
	@Autowired
	private OrdineAcquistoService ordineAcquistoService;
	@Autowired
	private OrdineAcquistoDettaglioService ordineAcquistoDettaglioService;
	
	List<OrdineAcquistoDettaglio> listaDettagli = new ArrayList<OrdineAcquistoDettaglio>();
	List<Integer> listaDettagliDaRimuovere = new ArrayList<Integer>();
	
	@GetMapping("/creazione")
	public ModelAndView registraOrdine(HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-creazione");
		maw.addObject("listFornitori", fornitoreService.findAll());
		if(session.getAttribute("OrdineAcquisto") == null)
			maw.addObject("ordineForm", new OrdineAcquisto());
		else
			maw.addObject("ordineForm", (OrdineAcquisto)session.getAttribute("OrdineAcquisto"));
		return maw;
	}
	
	@GetMapping("/creazione/{idFornitore}")
	public ModelAndView registraFattura(@PathVariable int idFornitore) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-creazione");
		maw.addObject("listFornitori", fornitoreService.findAll());
		maw.addObject("ordineForm", new OrdineAcquisto());
		return maw;
	}
	
	@GetMapping("/gestione")
	public ModelAndView caricaOrdine() {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ordini");
		maw.addObject("listFornitori", fornitoreService.findAll());
		return maw;
	}
	
	@GetMapping("/search-ordine/{id}")
	public ModelAndView caricaListaOrdiniFornitore(@PathVariable int id, HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ordini");
		maw.addObject("listFornitori", fornitoreService.findAll());
		session.setAttribute("chiaveFornitore", id);
		maw.addObject("listOrdini", ordineAcquistoService.findAllByFornitore(id));
		return maw;
	}
	
	@GetMapping("/dettaglio")
	public ModelAndView registraDettaglio(@ModelAttribute OrdineAcquisto ordineAcquisto,HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-dettaglio");
		session.setAttribute("OrdineAcquisto", ordineAcquisto);
		maw.addObject("listSpese", spesaInvestimentoService.findAll());
		maw.addObject("dettaglioForm",new OrdineAcquistoDettaglio());
		return maw;
	}
	
	@GetMapping("/dettaglio/{idSpesa}")
	public ModelAndView registraDettaglio(@ModelAttribute OrdineAcquisto ordineAcquisto,@PathVariable int idSpesa,HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-dettaglio");
		session.setAttribute("chiaveSpesa", idSpesa);
		List<SpesaInvestimento> listaSpese = spesaInvestimentoService.findAll();
		for(int i=0;i<listaSpese.size();i++) {
			if(listaSpese.get(i).getId() == idSpesa) {
				maw.addObject("IdProgetto", listaSpese.get(i).getProgetto().getId());
				maw.addObject("Progetto", listaSpese.get(i).getProgetto().getNomeProgetto());
				maw.addObject("Sottocategoria", listaSpese.get(i).getSottocategoria().getSottocategoria());
			}
		}
		maw.addObject("listSpese", spesaInvestimentoService.findAll());
		maw.addObject("dettaglioForm",new OrdineAcquistoDettaglio());
		return maw;
	}
	
	@GetMapping("/add")
	public ModelAndView aggiungiDettaglio(HttpSession session){
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-dettaglio");
		maw.addObject("listSpese", spesaInvestimentoService.findAll());
		maw.addObject("dettaglioForm",new OrdineAcquistoDettaglio());
		maw.addObject("listForm", (OrdineAcquisto) session.getAttribute("OrdineAcquisto"));
		return maw;
	}
	
	@GetMapping("/update/{indice}")
	public ModelAndView modificaOrdineDettaglio(@PathVariable int indice,HttpSession session){
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-dettaglio");
		session.setAttribute("chiaveSpesa", listaDettagli.get(indice).getSpesaInvestimento().getId());
		maw.addObject("listSpese", spesaInvestimentoService.findAll());
		maw.addObject("dettaglioForm", (OrdineAcquistoDettaglio) listaDettagli.get(indice));
		maw.addObject("fatturaForm", ordineAcquistoService.findById((int) session.getAttribute("chiaveOrdine")));
		return maw;
	}
	
	@PostMapping("/list")
	public ModelAndView listaFattura(@Valid @ModelAttribute OrdineAcquistoDettaglio ordineAcquistoDettaglio,HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-list");
		listaDettagli.add(ordineAcquistoDettaglio);
		caricaOggettiLista(maw);
		maw.addObject("listForm", (OrdineAcquisto) session.getAttribute("OrdineAcquisto"));
		session.removeAttribute("chiaveSpesa");
		return maw;
	}
	
	@GetMapping("/delete/{indice}")
	public ModelAndView cancellaDettaglio(@PathVariable int indice,HttpSession session){
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-list");
		if(listaDettagli.get(indice).getId() != null)
			listaDettagliDaRimuovere.add(listaDettagli.get(indice).getId());
		listaDettagli.remove(indice);
		caricaOggettiLista(maw);
		maw.addObject("listForm", session.getAttribute("OrdineAcquisto"));
		return maw;
	}

	private ModelAndView caricaOggettiLista(ModelAndView maw) {
		double tot = 0;
		for(int i = 0;i<listaDettagli.size();i++) {
			tot += listaDettagli.get(i).getImporto() * listaDettagli.get(i).getQuantita();
		}
		maw.addObject("Totale", tot);
		maw.addObject("listDettagli", listaDettagli);
		maw.addObject("listFornitori", fornitoreService.findAll());
		return maw;
	}
	
	//Pagina oa/list con idOrdine
	//dal dettaglio
	@PostMapping("/update-ordine/{id}")
	public ModelAndView listaFatture(@Valid @ModelAttribute OrdineAcquistoDettaglio ordineAcquistoDettaglio,@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-list");
		if(session.getAttribute("Indice") == null) {
			listaDettagli.add(ordineAcquistoDettaglio);
		}
		else {
			listaDettagli.set((int)session.getAttribute("Indice"), ordineAcquistoDettaglio);
		}
		
		caricaOggettiLista(maw);
		maw.addObject("listForm", ordineAcquistoService.findById(id));
		session.removeAttribute("Indice");
		return maw;
	}
	
	//dagli ordini
	@GetMapping("/update-ordine/{id}")
	public ModelAndView modificaOrdine(@PathVariable int id,HttpSession session){
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-list");
		session.setAttribute("chiaveOrdine", id);
		session.setAttribute("OrdineAcquisto", ordineAcquistoService.findById(id));
		listaDettagli = ordineAcquistoDettaglioService.findAllByOrdineAcquisto(id);
		caricaOggettiLista(maw);
		maw.addObject("listForm", ordineAcquistoService.findById(id));
		return maw;
	}
	
	@GetMapping("/valida-ordine/{id}")
	public String validaOrdine(@PathVariable int id,HttpSession session){
		OrdineAcquisto oa = ordineAcquistoService.findById(id);
		oa.setImporto(ordineAcquistoDettaglioService.calcolaImporto(id));
		ordineAcquistoService.save(oa);
		return "redirect:/ordine-acquisto/search-ordine/" + (int) session.getAttribute("chiaveFornitore");
	}
	
	@GetMapping("/delete-ordini/{id}")
	public String cancellaOrdine(@PathVariable int id,HttpSession session){
		List<OrdineAcquistoDettaglio> lista = ordineAcquistoDettaglioService.findAllByOrdineAcquisto(id);
		for(int i=0;i<lista.size();i++) {
			ordineAcquistoDettaglioService.delete(lista.get(i).getId());
		}
		ordineAcquistoService.delete(id);
		return "redirect:/ordine-acquisto/search-ordine/" + (int) session.getAttribute("chiaveFornitore");
	}
	
	@PostMapping("/save")
	public String salvaOrdine(@ModelAttribute OrdineAcquisto oa,HttpSession session) {
		ordineAcquistoService.save(oa);
		for(int i=0;i<listaDettagli.size();i++) {
			OrdineAcquistoDettaglio oad = listaDettagli.get(i);
			oad.setOrdineAcquisto(oa);
			ordineAcquistoDettaglioService.save(oad);
		}
		for(int i=0;i<listaDettagliDaRimuovere.size();i++) {
			ordineAcquistoDettaglioService.delete(listaDettagliDaRimuovere.get(i));
		}
		session.removeAttribute("OrdineAcquisto");
		listaDettagli.clear();
		listaDettagliDaRimuovere.clear();
	
		if(session.getAttribute("chiaveFornitore") != null) 
			return "redirect:/ordine-acquisto/search-ordine/" + (int) session.getAttribute("chiaveFornitore");
		else
			return "redirect:/ordine-acquisto/creazione";
	}
	
	@GetMapping("/ricerca")
	public ModelAndView ricercaOrdine(HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ricerca");
		return maw;
	}
	
	@GetMapping("/ricerca-fornitore")
	public ModelAndView ricercaOrdineFornitore() {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ricerca-per-fornitore");
		maw.addObject("listFornitori", fornitoreService.findAll());
		return maw;
	}
	
	@GetMapping("/ricerca-fornitore/{id}")
	public ModelAndView ricercaOrdiniPerFornitore(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ricerca-per-fornitore");
		session.setAttribute("chiaveFornitore", id);
		maw.addObject("listFornitori", fornitoreService.findAll());
		maw.addObject("listOrdiniFornitore",ordineAcquistoService.ricercaPerFornitore(id));
		return maw;
	}
	
	@GetMapping("/ricerca-progetto")
	public ModelAndView ricercaOrdineProgetto() {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ricerca-per-progetto");
		maw.addObject("listProgetti", progettoService.findAll());
		return maw;
	}
	
	@GetMapping("/ricerca-progetto/{id}")
	public ModelAndView ricercaOrdiniPerProgetto(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ricerca-per-progetto");
		session.setAttribute("chiaveProgetto", id);
		maw.addObject("listProgetti", progettoService.findAll());
		maw.addObject("listOrdiniProgetti",ordineAcquistoService.ricercaPerProgetto(id));
		return maw;
	}
	
	@GetMapping("/ricerca-sottocategoria")
	public ModelAndView ricercaOrdineSottocategoria() {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ricerca-per-sottocategoria");
		maw.addObject("listSottocategorie", sottocategoriaService.findAll());
		return maw;
	}
	
	@GetMapping("/ricerca-sottocategoria/{id}")
	public ModelAndView ricercaOrdineSottocategoria(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("ordine-acquisto/ordine-acquisto-ricerca-per-sottocategoria");
		session.setAttribute("chiaveSottocategoria", id);
		maw.addObject("listSottocategorie", sottocategoriaService.findAll());
		maw.addObject("listOrdiniSottocategoria",ordineAcquistoService.ricercaPerSottocategoria(id));
		return maw;
	}
	

}
