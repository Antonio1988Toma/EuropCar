package it.si.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Preventivo")
public class Preventivo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preventivo_generator")
	@SequenceGenerator(name = "preventivo_generator", sequenceName = "SEQCHIAVEPREVENTIVO", initialValue = 1, allocationSize = 1)
	@Column(name="IDPreventivo", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Codice")
	@NotBlank
	private String codice;
	
	@Column(name="Preventivo")
	@NotBlank
	private String nomePreventivo;
	
	@Column(name="Importo")
	@PositiveOrZero
	private double importo;
	
	@Column(name="Data")
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "IDFornitore")
	@NotNull
	private Fornitore fornitore;

	public Integer getId() {
		return id;
	}

	public String getCodice() {
		return codice;
	}

	public String getNomePreventivo() {
		return nomePreventivo;
	}

	public double getImporto() {
		return importo;
	}

	public Date getData() {
		if(data != null)
			return data;
		else
			return new Date();
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setNomePreventivo(String nomePreventivo) {
		this.nomePreventivo = nomePreventivo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}
	
	
	
	
}
