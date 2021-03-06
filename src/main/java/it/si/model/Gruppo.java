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
@Table(name = "Gruppo")
public class Gruppo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gruppo_generator")
	@SequenceGenerator(name = "gruppo_generator", sequenceName = "SEQCHIAVEGRUPPO", initialValue = 1, allocationSize = 1)
	@Column(name="IDGruppo", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Codice")
	@NotBlank(message = "Campo Obbligatorio")
	private String codice;
	
	@Column(name="Gruppo")
	@NotBlank(message = "Campo Obbligatorio")
	private String nomeGruppo;

	public Integer getId() {
		return id;
	}

	public String getCodice() {
		return codice;
	}

	public String getNomeGruppo() {
		return nomeGruppo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setNomeGruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}
	
	

}
