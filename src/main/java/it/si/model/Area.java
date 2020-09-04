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
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Area")
public class Area {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_generator")
	@SequenceGenerator(name = "area_generator", sequenceName = "SEQCHIAVEAREA", initialValue = 1, allocationSize = 1)
	@Column(name="IDArea", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Codice")
	@NotBlank(message = "Campo Obbligatorio")
	private String codice;
	
	@Column(name="Area")
	@NotBlank(message = "Campo Obbligatorio")
	private String nomeArea;
	
	@ManyToOne
	@JoinColumn(name = "IDAnno")
	private AnnoContabile annoContabile;
	
	
	public Integer getId() {
		return id;
	}

	public String getCodice() {
		return codice;
	}

	public String getNomeArea() {
		return nomeArea;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setNomeArea(String nomeArea) {
		this.nomeArea = nomeArea;
	}

	public AnnoContabile getAnnoContabile() {
		return annoContabile;
	}

	public void setAnnoContabile(AnnoContabile annoContabile) {
		this.annoContabile = annoContabile;
	}
	
	
}
