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
@Table(name = "Utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_generator")
	@SequenceGenerator(name = "utente_generator", sequenceName = "SEQCHIAVEUTENTE", initialValue = 1, allocationSize = 1)
	@Column(name="IDUtente", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Admin")
	//@NotBlank(message = "Campo Obbligatorio")
	private String admin;
	
	@Column(name="Password")
	@NotBlank(message = "Campo Obbligatorio")
	private String password;
	
	@Column(name="Username")
	@NotBlank(message = "Campo Obbligatorio")
	private String username;
	
	@ManyToOne
	@JoinColumn(name = "IDTipoVenditore")
	@NotNull(message = "Campo Obbligatorio")
	private Venditore venditore;

	public Integer getId() {
		return id;
	}

	public String getAdmin() {
		return admin;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public Venditore getVenditore() {
		return venditore;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVenditore(Venditore venditore) {
		this.venditore = venditore;
	}
	
	

}
