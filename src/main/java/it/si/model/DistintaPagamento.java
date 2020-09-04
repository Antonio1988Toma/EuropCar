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

@Entity
@Table(name = "DistintaPagamento")
public class DistintaPagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "distintapagamento_generator")
	@SequenceGenerator(name = "distintapagamento_generator", sequenceName = "SEQCHIAVEDISTINTAPAGAMENTO", initialValue = 1, allocationSize = 1)
	@Column(name="IDDistinta", updatable = false, nullable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "IDFatturaPassivaDettaglio")
	private FatturaPassivaDettaglio fatturaPassivaDettaglio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FatturaPassivaDettaglio getFatturaPassivaDettaglio() {
		return fatturaPassivaDettaglio;
	}

	public void setFatturaPassivaDettaglio(FatturaPassivaDettaglio fatturaPassivaDettaglio) {
		this.fatturaPassivaDettaglio = fatturaPassivaDettaglio;
	}

	
	

}
