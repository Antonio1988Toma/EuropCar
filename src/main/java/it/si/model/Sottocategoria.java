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
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "Sottocategoria")
public class Sottocategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sottocategoria_generator")
	@SequenceGenerator(name = "sottocategoria_generator", sequenceName = "SEQCHIAVESOTTOCATEGORIA", initialValue = 1, allocationSize = 1)
	@Column(name="IDSottocategoria", updatable = false, nullable = false)
	private Integer id;

	@Column(name="Codice")
	@NotBlank(message = "Campo Obbligatorio")
	private String codice;
	
	@Column(name="Sottocategoria")
	@NotBlank(message = "Campo Obbligatorio")
	private String sottocategoria;
	
	@Column(name="Budget")
	@PositiveOrZero(message = "Il Budget non puo' essere negativo")
	private double budget;
	
	@Column(name="BudgetSpeso")
	private double budgetSpeso;
	
	@ManyToOne
	@JoinColumn(name = "IDArea")
	@NotNull(message = "Campo Obbligatorio")
	private Area area;

	public Integer getId() {
		return id;
	}

	public String getCodice() {
		return codice;
	}

	public String getSottocategoria() {
		return sottocategoria;
	}

	public double getBudget() {
		return budget;
	}

	public double getBudgetSpeso() {
		return budgetSpeso;
	}

	public Area getArea() {
		return area;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setSottocategoria(String sottocategoria) {
		this.sottocategoria = sottocategoria;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public void setBudgetSpeso(double budgetSpeso) {
		this.budgetSpeso = budgetSpeso;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	
}
