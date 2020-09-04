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
@Table(name = "TipoVenditore")
public class TipoVenditore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipovenditore_generator")
	@SequenceGenerator(name = "tipovenditore_generator", sequenceName = "SEQCHIAVETIPOVENDITORE", initialValue = 1, allocationSize = 1)
	@Column(name="IDTipoVenditore", updatable = false, nullable = false)
	private Integer id;

	@Column(name="TipoVenditore")
	@NotBlank(message = "Campo Obbligatorio")
	private String tipoVenditore;

	public Integer getId() {
		return id;
	}

	public String getTipoVenditore() {
		return tipoVenditore;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipoVenditore(String tipoVenditore) {
		this.tipoVenditore = tipoVenditore;
	}
	
	

}
