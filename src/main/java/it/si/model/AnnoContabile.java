package it.si.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "AnnoContabile")
public class AnnoContabile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "annocontabile_generator")
	@SequenceGenerator(name = "annocontabile", sequenceName = "SEQCHIAVEANNOCONTABILE", initialValue = 1, allocationSize = 1)
	@Column(name="IDAnno", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Descrizione")
	@NotBlank(message = "Campo Obbligatorio")
	private String descrizione;
	
	@Column(name="DataInizio")
	@NotNull(message = "Campo Obbligatorio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInizio;
	
	@Column(name="DataFine")
	@NotNull(message = "Campo Obbligatorio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFine;

	public Integer getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	
	
	
}
