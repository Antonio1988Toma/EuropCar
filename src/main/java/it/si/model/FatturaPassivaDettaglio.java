package it.si.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Entity
@Table(name = "FatturaPassivaDettaglio")
public class FatturaPassivaDettaglio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatturapassivadettaglio_generator")
	@SequenceGenerator(name = "fatturapassivadettaglio_generator", sequenceName = "SEQCHIAVEFATTURAPASSIVADETTAGLIO", initialValue = 1, allocationSize = 1)
	@Column(name="IDFatturaPassivaDettaglio", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name="DettaglioFattura")
	@NotBlank(message = "Campo Obbligatorio")
	private String dettaglioFattura;
	
	@Column(name="Importo")
	@PositiveOrZero(message = "L\'importo deve essere non puo' essere negativo")
	private double importo;
	
	@Column(name="Imponibile")
	private double imponibile;
	
	@ManyToOne
	@JoinColumn(name = "IDAliquotaIva")
	@NotNull(message = "Campo Obbligatorio")
	private AliquotaIva aliquotaIva;
	
	@ManyToOne
	@JoinColumn(name = "IDPreventivo")
	@NotNull(message = "Campo Obbligatorio")
	private Preventivo preventivo;
	
	@ManyToOne
	@JoinColumn(name = "IDSpesaInvestimento")
	@NotNull(message = "Campo Obbligatorio")
	private SpesaInvestimento spesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name = "IDFatturaPassiva")
	private FatturaPassiva fatturaPassiva;

	public Integer getId() {
		return id;
	}

	public String getDettaglioFattura() {
		return dettaglioFattura;
	}

	public double getImporto() {
		return importo;
	}

	public double getImponibile() {
		BigDecimal db = new BigDecimal(imponibile).setScale(2, RoundingMode.HALF_UP);
		return db.doubleValue();
	}

	public AliquotaIva getAliquotaIva() {
		return aliquotaIva;
	}

	public Preventivo getPreventivo() {
		return preventivo;
	}
	
	public SpesaInvestimento getSpesaInvestimento() {
		return spesaInvestimento;
	}

	public FatturaPassiva getFatturaPassiva() {
		return fatturaPassiva;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDettaglioFattura(String dettaglioFattura) {
		this.dettaglioFattura = dettaglioFattura;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public void setImponibile(double imponibile) {
		this.imponibile = imponibile;
	}

	public void setAliquotaIva(AliquotaIva aliquotaIva) {
		this.aliquotaIva = aliquotaIva;
	}

	public void setPreventivo(Preventivo preventivo) {
		this.preventivo = preventivo;
	}

	public void setFatturaPassiva(FatturaPassiva fatturaPassiva) {
		this.fatturaPassiva = fatturaPassiva;
	}

	public void setSpesaInvestimento(SpesaInvestimento spesaInvestimento) {
		this.spesaInvestimento = spesaInvestimento;
	}
	
	

	
	

}
