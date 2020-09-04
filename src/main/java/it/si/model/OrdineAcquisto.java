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
@Table(name = "OrdineAcquisto")
public class OrdineAcquisto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prdineacquisto_generator")
	@SequenceGenerator(name = "ordineacquisto_generator", sequenceName = "SEQCHIAVEORDINEACQUISTO", initialValue = 1, allocationSize = 1)
	@Column(name="IDOrdineAcquisto", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Numero")
	@NotBlank(message = "Campo Obbligatorio")
	private String numero;
	
	@Column(name="OrdineAcquisto")
	@NotBlank(message = "Campo Obbligatorio")
	private String nomeOrdineAcquisto;
	
	@Column(name="Data")
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@Column(name="Importo")
	@PositiveOrZero
	private double importo;
	
	@ManyToOne
	@JoinColumn(name = "ID_Fornitore")
	@NotNull
	private Fornitore fornitore;

	public Integer getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getNomeOrdineAcquisto() {
		return nomeOrdineAcquisto;
	}

	public Date getData() {
		if(data != null)
			return data;
		else
			return new Date();
	}

	public double getImporto() {
		return importo;
	}

	public Fornitore getFornitore() {
		return fornitore;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setNomeOrdineAcquisto(String nomeOrdineAcquisto) {
		this.nomeOrdineAcquisto = nomeOrdineAcquisto;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}
	
	

}
