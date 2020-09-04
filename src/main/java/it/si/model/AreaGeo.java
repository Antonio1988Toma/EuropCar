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
@Table(name = "AreaGeo")
public class AreaGeo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "areageo_generator")
	@SequenceGenerator(name = "areageo_generator", sequenceName = "SEQCHIAVEAREAGEO", initialValue = 1, allocationSize = 1)
	@Column(name="IDAreaGeo", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Codice")
	@NotBlank(message = "Campo Obbligatorio")
	private String codice;
	
	@Column(name="AreaGeo")
	@NotBlank(message = "Campo Obbligatorio")
	private String nomeAreaGeo;

	public Integer getId() {
		return id;
	}

	public String getCodice() {
		return codice;
	}

	public String getNomeAreaGeo() {
		return nomeAreaGeo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setNomeAreaGeo(String nomeAreaGeo) {
		this.nomeAreaGeo = nomeAreaGeo;
	}
	
	

}
