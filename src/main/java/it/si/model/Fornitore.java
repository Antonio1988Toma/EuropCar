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
@Table(name = "Fornitore")
public class Fornitore {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornitore_generator")
	@SequenceGenerator(name = "fornitore_generator", sequenceName = "SEQCHIAVEFORNITORE", initialValue = 1, allocationSize = 1)
	@Column(name="IDFornitore", updatable = false, nullable = false)
	private Integer id;

	@Column(name="RagioneSociale")
	@NotBlank(message = "Campo Obbligatorio")
	private String ragioneSociale;
	
	@Column(name="Indirizzo")
	@NotBlank(message = "Campo Obbligatorio")
	private String indirizzo;
	
	@Column(name="Citta")
	@NotBlank(message = "Campo Obbligatorio")
	private String citta;
	
	@Column(name="Cap")
	@NotBlank(message = "Campo Obbligatorio")
	private String cap;
	
	@Column(name="Provicia")
	@NotBlank(message = "Campo Obbligatorio")
	private String provincia;
	
	@Column(name="PartitaIva")
	@NotBlank(message = "Campo Obbligatorio")
	private String partitaIva;

	public Integer getId() {
		return id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public String getCap() {
		return cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	
	
}
