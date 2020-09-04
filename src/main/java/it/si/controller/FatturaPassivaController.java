package it.si.controller;

import java.text.ParseException;
import java.util.ArrayList;
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

import it.si.model.FatturaPassiva;
import it.si.model.FatturaPassivaDettaglio;
import it.si.service.AliquotaIvaService;
import it.si.service.FatturaPassivaDettaglioService;
import it.si.service.FatturaPassivaService;
import it.si.service.FornitoreService;
import it.si.service.PreventivoService;
import it.si.service.SottocategoriaService;
import it.si.service.SpesaInvestimentoService;
import it.si.util.UDate;

@Controller
@RequestMapping("/fattura-passiva")
public class FatturaPassivaController {
	
	@Autowired	
	private SpesaInvestimentoService spesaInvestimentoService;
	@Autowired	
	private SottocategoriaService sottocategoriaService;
	@Autowired
	private PreventivoService preventivoService;
	@Autowired
	private AliquotaIvaService aliquotaIvaService;
	@Autowired
	private FornitoreService fornitoreService;
	@Autowired
	private FatturaPassivaService fatturaPassivaService;
	@Autowired
	private FatturaPassivaDettaglioService fatturaPassivaDettaglioService; 
	//@Autowired
	//private DistintaPagamentoService distintaPagamentoService; 
	
	List<FatturaPassivaDettaglio> listaDettagli = new ArrayList<FatturaPassivaDettaglio>();
	List<Integer> listaDettagliDaRimuovere = new ArrayList<Integer>();
	
	//Pagina fp/registrazione
	@GetMapping("/registrazione")
	public ModelAndView registraFattura(HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-registrazione");
		maw.addObject("listFornitori", fornitoreService.findAll());
		if(session.getAttribute("FatturaPassiva") == null) 
			maw.addObject("fatturaForm", new FatturaPassiva());
		else 
			maw.addObject("fatturaForm", (FatturaPassiva)session.getAttribute("FatturaPassiva"));
		return maw;
	}
	
	//Pagina fp/registrazione con id fornitore
	@GetMapping("/registrazione/{idFornitore}")
	public ModelAndView registraFattura(@PathVariable int idFornitore) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-registrazione");
		maw.addObject("listFornitori", fornitoreService.findAll());
		maw.addObject("fatturaForm", new FatturaPassiva());
		return maw;
	}

	
	//Pagina fp/dettaglio
	//entra dalla registrazione
	@GetMapping("/dettaglio")
	public ModelAndView registraDettaglio(@ModelAttribute FatturaPassiva fatturaPassiva,HttpSession session) {
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fattura-passiva/fattura-passiva-dettaglio");
		session.setAttribute("FatturaPassiva", fatturaPassiva);
		caricaOggettiDettaglio(maw);
		maw.addObject("dettaglioForm",new FatturaPassivaDettaglio());
		return maw;
	}
	//entra dal nuovo della list
	@GetMapping("/add")
	public ModelAndView aggiungiDettaglio(HttpSession session){
		ModelAndView maw = new ModelAndView();
		maw.setViewName("fattura-passiva/fattura-passiva-dettaglio");
		caricaOggettiDettaglio(maw);
		maw.addObject("dettaglioForm",new FatturaPassivaDettaglio());
		maw.addObject("listForm", (FatturaPassiva) session.getAttribute("FatturaPassiva"));
		return maw;
	}
	
	@GetMapping("/update/{indice}")
	public ModelAndView modificaFatturaDettaglio(@PathVariable int indice,HttpSession session){
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-dettaglio");
		maw.addObject("Preventivo", listaDettagli.get(indice).getPreventivo().getId());
		maw.addObject("Aliquota", listaDettagli.get(indice).getAliquotaIva().getId());
		maw.addObject("Spesa", listaDettagli.get(indice).getSpesaInvestimento().getId());
		caricaOggettiDettaglio(maw);
		maw.addObject("dettaglioForm", (FatturaPassivaDettaglio) listaDettagli.get(indice));
		maw.addObject("fatturaForm", fatturaPassivaService.findById((int) session.getAttribute("chiaveFattura")));
		return maw;
	}
	
	public ModelAndView caricaOggettiDettaglio(ModelAndView maw) {
		maw.addObject("listAliquote", aliquotaIvaService.findAll());
		maw.addObject("listSpese", spesaInvestimentoService.findAll());
		maw.addObject("listPreventivi", preventivoService.findAll());
		return maw;
	}
	
	//Pagina fp/list
	
	@GetMapping("/delete/{indice}")
	public ModelAndView cancellaDettaglio(@PathVariable int indice,HttpSession session){
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-list");
		session.setAttribute("Indice", indice);
		if(listaDettagli.get(indice).getId() != null)
			listaDettagliDaRimuovere.add(listaDettagli.get(indice).getId());
		listaDettagli.remove(indice);
		caricaOggettiLista(maw);
		maw.addObject("listForm", session.getAttribute("FatturaPassiva"));
		return maw;
	}
	
	@GetMapping("/delete-fatture/{id}")
	public String cancellaOrdine(@PathVariable int id,HttpSession session){
		List<FatturaPassivaDettaglio> lista = fatturaPassivaDettaglioService.findAllByFatturaPassiva(id);
		for(int i=0;i<lista.size();i++) {
			fatturaPassivaDettaglioService.delete(lista.get(i).getId());
		}
		fatturaPassivaService.delete(id);
		return "redirect:/fattura-passiva/search-fattura/" + (int) session.getAttribute("chiaveFornitore");
	}
	
	
	@PostMapping("/list")
	public ModelAndView listaFattura(@Valid @ModelAttribute FatturaPassivaDettaglio fatturaPassivaDettaglio,HttpSession session,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if(br.hasErrors()) {
			maw.setViewName("fattura-passiva/fattura-passiva-dettaglio");
		}
		else {
			maw.setViewName("fattura-passiva/fattura-passiva-list");
			fatturaPassivaDettaglio.setImponibile((100*fatturaPassivaDettaglio.getImporto())/(100 + fatturaPassivaDettaglio.getAliquotaIva().getAliquota()*100));
			listaDettagli.add(fatturaPassivaDettaglio);
			caricaOggettiLista(maw);
			maw.addObject("listForm", (FatturaPassiva) session.getAttribute("FatturaPassiva"));
		}
		return maw;
	}
	
	//Pagina fp/list con idFattura
	//dal dettaglio
	@PostMapping("/update-fattura/{id}")
	public ModelAndView listaFatture(@Valid @ModelAttribute FatturaPassivaDettaglio fatturaPassivaDettaglio,@PathVariable int id,HttpSession session,BindingResult br) {
		ModelAndView maw = new ModelAndView();
		if(br.hasErrors()) {
			maw.setViewName("fattura-passiva/fattura-passiva-dettaglio");
		}
		else {
			maw.setViewName("fattura-passiva/fattura-passiva-list");
			fatturaPassivaDettaglio.setImponibile((100*fatturaPassivaDettaglio.getImporto())/(100 + fatturaPassivaDettaglio.getAliquotaIva().getAliquota()*100));
			if(session.getAttribute("Indice") == null) {
				listaDettagli.add(fatturaPassivaDettaglio);
			}
			else {
				listaDettagli.set((int)session.getAttribute("Indice"), fatturaPassivaDettaglio);
			}
			maw.addObject("listDettagli", listaDettagli);
			maw.addObject("listFornitori", fornitoreService.findAll());
			calcolaTotali(maw);
			maw.addObject("listForm", fatturaPassivaService.findById(id));
			session.removeAttribute("Indice");
			session.removeAttribute("Aliquota");
			session.removeAttribute("Preventivo");
			session.removeAttribute("Spesa");
		}
		return maw;
	}
	
	//dalla fatture
	@GetMapping("/update-fattura/{id}")
	public ModelAndView modificaFattura(@PathVariable int id,HttpSession session){
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-list");
		session.setAttribute("chiaveFattura", id);
		session.setAttribute("FatturaPassiva", fatturaPassivaService.findById(id));
		listaDettagli = fatturaPassivaDettaglioService.findAllByFatturaPassiva(id);
		maw.addObject("listDettagli", listaDettagli);
		maw.addObject("listFornitori", fornitoreService.findAll());
		calcolaTotali(maw);
		maw.addObject("listForm", fatturaPassivaService.findById(id));
		return maw;
	}
	
	public ModelAndView caricaOggettiLista (ModelAndView maw) {
		maw.addObject("listDettagli", listaDettagli);
		maw.addObject("listFornitori", fornitoreService.findAll());
		calcolaTotali(maw);
		return maw;
	}
	
	public ModelAndView calcolaTotali(ModelAndView maw) {
		double totaleImporto = 0, totaleIva = 0, totaleImponibile = 0;
		for(int i = 0;i<listaDettagli.size();i++) {
			totaleImporto += listaDettagli.get(i).getImporto();
			totaleImponibile += listaDettagli.get(i).getImponibile();
			totaleIva +=  totaleImporto - totaleImponibile;
		}
		maw.addObject("TotaleImporto",totaleImporto);
		maw.addObject("TotaleImponibile",totaleImporto);
		maw.addObject("TotaleIVA",totaleIva);
		return maw;
	}
	
	@GetMapping("/pagamento")
	public ModelAndView pagamentoFattura(HttpSession session){
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-pagamento");
		FatturaPassiva fp = (FatturaPassiva)session.getAttribute("FatturaPassiva");
		maw.addObject("pagamentoForm", fp);
		double totaleImporto = 0;
		for(int i = 0;i<listaDettagli.size();i++) {
			totaleImporto += listaDettagli.get(i).getImporto();
		}
		session.setAttribute("DaPagare", totaleImporto - fp.getPagato());
		return maw;
	}
	
	@PostMapping("/save-pagamento")
	public String salvaPagamento(@ModelAttribute FatturaPassiva fp,HttpSession session) {
		fp.setPagato(fp.getPagato() + (double) session.getAttribute("DaPagare"));
		salva(fp);
		session.removeAttribute("DaPagare");
		return "redirect:/fattura-passiva/update-fattura/" + (int) session.getAttribute("chiaveFattura");
	}
	
	@PostMapping("/save")
	public String salvaFattura(@ModelAttribute FatturaPassiva fp,HttpSession session) {
		salva(fp);
		session.removeAttribute("FatturaPassiva");
		listaDettagli.clear();
		listaDettagliDaRimuovere.clear();
	
		if(session.getAttribute("chiaveFornitore") != null) 
			return "redirect:/fattura-passiva/search-fattura/" + (int) session.getAttribute("chiaveFornitore");
		else
			return "redirect:/fattura-passiva/registrazione";
	}
	
	public void salva (FatturaPassiva fp) {
		fatturaPassivaService.save(fp);
		for(int i=0;i<listaDettagli.size();i++) {
			FatturaPassivaDettaglio fpd = listaDettagli.get(i);
			fpd.setFatturaPassiva(fp);
			fatturaPassivaDettaglioService.save(fpd);
			//DistintaPagamento dp = new DistintaPagamento();
			//dp.setFatturaPassivaDettaglio(fpd);
			//distintaPagamentoService.save(dp);
		}
		for(int i=0;i<listaDettagliDaRimuovere.size();i++) {
			fatturaPassivaDettaglioService.delete(listaDettagliDaRimuovere.get(i));
		}
	}
	
	@GetMapping("/annulla")
	public String annulaFattura(HttpSession session) {
		return "redirect:/fattura-passiva/search-fattura/" + (int) session.getAttribute("chiaveFornitore");

	}
	
	//Pagina fp/fatture
	@GetMapping("/gestione")
	public ModelAndView caricaFatture() {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-fatture");
		maw.addObject("listFornitori", fornitoreService.findAll());
		return maw;
	}
	
	//Pagina fp/fatture con id
	@GetMapping("/search-fattura/{id}")
	public ModelAndView caricaListaFattureFornitore(@PathVariable int id, HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-fatture");
		maw.addObject("listFornitori", fornitoreService.findAll());
		session.setAttribute("chiaveFornitore", id);
		maw.addObject("listFatture", fatturaPassivaService.findAllByFornitore(id));
		return maw;
	}
	
	//Pagina fp/ricerca
	@GetMapping("/ricerca")
	public ModelAndView ricercaFattura(HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-ricerca");
		return maw;
	}
	
	@GetMapping("/ricerca-fornitore")
	public ModelAndView ricercaFatturaPerFornitore() {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-ricerca-per-fornitore");
		maw.addObject("listFornitori", fornitoreService.findAll());
		return maw;
	}
	
	@GetMapping("/ricerca-fornitore/{id}")
	public ModelAndView ricercaFatturaPerFornitore(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-ricerca-per-fornitore");
		session.setAttribute("chiaveFornitore", id);
		maw.addObject("listFornitori", fornitoreService.findAll());
		maw.addObject("listFattureFornitore",fatturaPassivaService.ricercaPerFornitore(id));
		return maw;
	}
	
	@GetMapping("/ricerca-preventivo")
	public ModelAndView ricercaFatturaPerPreventivo() {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-ricerca-per-preventivo");
		maw.addObject("listPreventivi", preventivoService.findAll());
		return maw;
	}
	
	@GetMapping("/ricerca-preventivo/{id}")
	public ModelAndView ricercaFatturaPerPreventivo(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-ricerca-per-preventivo");
		session.setAttribute("chiavePreventivo", id);
		maw.addObject("listPreventivi", preventivoService.findAll());
		maw.addObject("listFatturePreventivi",fatturaPassivaService.ricercaPerPreventivo(id));
		return maw;
	}
	
	@GetMapping("/ricerca-sottocategoria")
	public ModelAndView ricercaFatturaPerSottocategoria() {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-ricerca-per-sottocategoria");
		maw.addObject("listSottocategorie", sottocategoriaService.findAll());
		return maw;
	}
	
	@GetMapping("/ricerca-sottocategoria/{id}")
	public ModelAndView ricercaFatturaPerSottocategoria(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-ricerca-per-sottocategoria");
		session.setAttribute("chiaveSottocategoria", id);
		maw.addObject("listSottocategorie", sottocategoriaService.findAll());
		maw.addObject("listFattureSottocategorie",fatturaPassivaService.ricercaPerSottocategoria(id));
		return maw;
	}
	
	@GetMapping("/statistica")
	public ModelAndView statistica() {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-statistica");
		maw.addObject("listSottocategorie", sottocategoriaService.findAll());
		return maw;
	}
	
	@GetMapping("/statistica-sottocategoria/{id}")
	public ModelAndView statistica(@PathVariable int id,HttpSession session) {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-statistica");
		session.setAttribute("chiaveSottocategoria", id);
		session.setAttribute("chiaveSpesa", null);
		maw.addObject("listSottocategorie", sottocategoriaService.findAll());
		maw.addObject("listSpese", spesaInvestimentoService.findAllBySottocategoria(id));
		return maw;
	}
	
	@PostMapping("/statistica-spesa")
	public ModelAndView statistica(@ModelAttribute("listaSpese") int idSpesa,
			@ModelAttribute("dataDal") String dal,@ModelAttribute("dataAl") String al,HttpSession session) throws ParseException {
		ModelAndView maw = new ModelAndView("fattura-passiva/fattura-passiva-statistica");
		session.setAttribute("chiaveSpesa", idSpesa);
		maw.addObject("listSottocategorie", sottocategoriaService.findAll());
		maw.addObject("listSpese", spesaInvestimentoService.findAllBySottocategoria((int) session.getAttribute("chiaveSottocategoria")));
		List<FatturaPassivaDettaglio> lista = fatturaPassivaDettaglioService.cercaSpese(idSpesa,UDate.ctrlData(dal),UDate.ctrlData(al));
		double totale = 0;
		double totaleFatturato = 0;
		for(int i=0;i<lista.size();i++) {
			totale += lista.get(i).getImporto();
			FatturaPassiva fp = fatturaPassivaService.findById(lista.get(i).getFatturaPassiva().getId());
			if(fp.getPagato() != 0)
				totaleFatturato += lista.get(i).getImporto();;
		}
		maw.addObject("TotOrdinato",totale);
		maw.addObject("TotFatturato", totaleFatturato);
		maw.addObject("Differenza",totale - totaleFatturato);
		maw.addObject("Sottocategoria",sottocategoriaService.findById((int) session.getAttribute("chiaveSottocategoria")).getSottocategoria());
		maw.addObject("SpesaInvestimento",spesaInvestimentoService.findById(idSpesa).getNomeSpesaInvestimento());
		maw.addObject("Dal",dal);
		maw.addObject("Al",al);
		return maw;
	}
	
}
