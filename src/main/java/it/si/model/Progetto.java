package it.si.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Progetto")
public class Progetto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "progetto_generator")
	@SequenceGenerator(name = "progetto_generator", sequenceName = "SEQCHIAVEPROGETTO", initialValue = 1, allocationSize = 1)
	@Column(name="IDProgetto", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Codice")
	@NotBlank(message = "Campo Obbligatorio")
	private String codice;
	
	@Column(name="Progetto")
	@NotBlank(message = "Campo Obbligatorio")
	private String nomeProgetto;

	public Integer getId() {
		return id;
	}

	public String getCodice() {
		return codice;
	}

	public String getNomeProgetto() {
		return nomeProgetto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}
	
	
}
