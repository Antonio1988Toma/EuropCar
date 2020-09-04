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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Venditore")
public class Venditore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venditore_generator")
	@SequenceGenerator(name = "venditore_generator", sequenceName = "SEQCHIAVEVENDITORE", initialValue = 1, allocationSize = 1)
	@Column(name="IDVenditore", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Cognome")
	@NotBlank(message = "Campo Obbligatorio")
	private String cognome;
	
	@Column(name="Nome")
	@NotBlank(message = "Campo Obbligatorio")
	private String nome;
	
	@Column(name="Indirizzo")
	@NotBlank(message = "Campo Obbligatorio")
	private String indirizzo;
	
	@Column(name="NumeroTelefono")
	@NotBlank(message = "Campo Obbligatorio")
	private String numeroTelefono;
	
	@ManyToOne
	@JoinColumn(name = "IDTipoVenditore")
	@NotNull(message = "Campo Obbligatorio")
	private TipoVenditore tipoVenditore;

	public Integer getId() {
		return id;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public TipoVenditore getTipoVenditore() {
		return tipoVenditore;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public void setTipoVenditore(TipoVenditore tipoVenditore) {
		this.tipoVenditore = tipoVenditore;
	}
	
	


}
