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
@Table(name = "Azienda")
public class Azienda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "azienda_generator")
	@SequenceGenerator(name = "azienda_generator", sequenceName = "SEQCHIAVEAZIENDA", initialValue = 1, allocationSize = 1)
	@Column(name="IDAzienda", updatable = false, nullable = false)
	private Integer id;

	@Column(name="ContractID")
	@NotBlank(message = "Campo Obbligatorio")
	private String contractId;
	
	@Column(name="Indirizzo")
	@NotBlank(message = "Campo Obbligatorio")
	private String indirizzo;
	
	@Column(name="RagioneSociale")
	@NotBlank(message = "Campo Obbligatorio")
	private String ragioneSociale;
	
	@ManyToOne
	@JoinColumn(name = "IDGruppo")
	private Gruppo gruppo;

	public Integer getId() {
		return id;
	}

	public String getContractId() {
		return contractId;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public Gruppo getGruppo() {
		return gruppo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}
	
	

}
