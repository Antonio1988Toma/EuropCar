package it.si.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "OrdineAcquistoDettaglio")
public class OrdineAcquistoDettaglio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordineacquistodettaglio_generator")
	@SequenceGenerator(name = "ordineacquistodettaglio_generator", sequenceName = "SEQCHIAVEORDINEACQUISTODETTAGLIO", initialValue = 1, allocationSize = 1)
	@Column(name="IDFatturaPassivaDettaglio", updatable = false, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "IDOrdineAcquisto")
	private OrdineAcquisto ordineAcquisto;
	
	@ManyToOne
	@JoinColumn(name = "IDSpesaInvestimento")
	private SpesaInvestimento spesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name = "IDProgetto")
	private Progetto progetto;
	
	@Column(name="Importo")
	@PositiveOrZero
	private double importo;
	
	@Column(name="Quantita")
	@PositiveOrZero
	private int quantita;

	public Integer getId() {
		return id;
	}

	public OrdineAcquisto getOrdineAcquisto() {
		return ordineAcquisto;
	}

	public SpesaInvestimento getSpesaInvestimento() {
		return spesaInvestimento;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public double getImporto() {
		return importo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrdineAcquisto(OrdineAcquisto ordineAcquisto) {
		this.ordineAcquisto = ordineAcquisto;
	}

	public void setSpesaInvestimento(SpesaInvestimento spesaInvestimento) {
		this.spesaInvestimento = spesaInvestimento;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	


}
