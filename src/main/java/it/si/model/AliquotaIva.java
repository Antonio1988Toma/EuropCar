package it.si.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "AliquotaIva")
public class AliquotaIva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aliquotaIva_generator")
	@SequenceGenerator(name = "aliquotaIva_generator", sequenceName = "SEQCHIAVEALIQUOTAIVA", initialValue = 1, allocationSize = 1)
	@Column(name="IDAliquotaIva", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Aliquota")
	@PositiveOrZero(message = "L\'Aliquota non puo' essere negativa")
	private double aliquota;
	
	@Column(name="Descrizione")
	@NotBlank(message = "Campo Obbligatorio")
	private String descrizione;

	public Integer getId() {
		return id;
	}

	public double getAliquota() {
		return aliquota;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAliquota(double aliquota) {
		this.aliquota = aliquota;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
