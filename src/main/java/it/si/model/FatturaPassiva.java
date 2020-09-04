package it.si.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "FatturaPassiva")
public class FatturaPassiva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatturapassiva_generator")
	@SequenceGenerator(name = "fatturapassiva_generator", sequenceName = "SEQCHIAVEFATTURAPASSIVA", initialValue = 1, allocationSize = 1)
	@Column(name="IDFatturaPassiva", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Numero")
	@NotBlank(message = "Campo Obbligatorio")
	private String numero;
	
	@Column(name="Descrizione")
	//@NotBlank(message = "Campo Obbligatorio")
	private String descrizione;
	
	@Column(name="Data")
	@NotNull(message = "Campo Obbligatorio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "IDFornitore")
	@NotNull(message = "Campo Obbligatorio")
	private Fornitore fornitore;
	
	@Column(name="Pagato")
	private double pagato;

	public Integer getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Date getData() {
		if(data != null)
			return data;
		else
			return new Date();
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	public double getPagato() {
		return pagato;
	}

	public void setPagato(double pagato) {
		this.pagato = pagato;
	}
	
	

}
