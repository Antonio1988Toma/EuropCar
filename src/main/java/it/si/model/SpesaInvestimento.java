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
@Table(name = "SpesaInvestimento")
public class SpesaInvestimento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spesaInvestimento_generator")
	@SequenceGenerator(name = "spesaInvestimento_generator", sequenceName = "SEQCHIAVESPESAINVESTIMENTO", initialValue = 1, allocationSize = 1)
	@Column(name="IDSpesaInvestimento", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name="SpesaInvestimento")
	@NotBlank(message = "Campo Obbligatorio")
	private String nomeSpesaInvestimento;
	
	@ManyToOne
	@JoinColumn(name = "IDSottocategoria")
	@NotNull(message = "Campo Obbligatorio")
	private Sottocategoria sottocategoria;
	
	@ManyToOne
	@JoinColumn(name = "IDProgetto")
	@NotNull(message = "Campo Obbligatorio")
	private Progetto progetto;

	public Integer getId() {
		return id;
	}

	public String getNomeSpesaInvestimento() {
		return nomeSpesaInvestimento;
	}

	public Sottocategoria getSottocategoria() {
		return sottocategoria;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNomeSpesaInvestimento(String nomeSpesaInvestimento) {
		this.nomeSpesaInvestimento = nomeSpesaInvestimento;
	}

	public void setSottocategoria(Sottocategoria sottocategoria) {
		this.sottocategoria = sottocategoria;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}


	
	
	
}
