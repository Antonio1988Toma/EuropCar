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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Previsione")
public class Previsione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "previsione_generator")
	@SequenceGenerator(name = "previsione_generator", sequenceName = "SEQCHIAVEPREVISIONE", initialValue = 1, allocationSize = 1)
	@Column(name="IDPrevsione", updatable = false, nullable = false)
	private Integer id;

	@Column(name="AnnoRiferimento")
	@NotBlank(message = "Campo Obbligatorio")
	private String annoRiferimento;
	
	@Column(name="Confidenza")
	@NotBlank(message = "Campo Obbligatorio")
	private String confidenza;
	
	@Column(name="DataRegistrazione")
	@NotNull(message = "Campo Obbligatorio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRegistrazione;
	
	@Column(name="DataVisita")
	@NotNull(message = "Campo Obbligatorio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataVisita;
	
	@Column(name="PotenzialeAzienda")
	@NotBlank(message = "Campo Obbligatorio")
	private String potenzialeAzienda;
	
	@Column(name="PotenzialeEuropCar")
	@NotBlank(message = "Campo Obbligatorio")
	private String potenzialeEuropCar;
	
	@Column(name="ShareAnte")
	@NotBlank(message = "Campo Obbligatorio")
	private String shareAnte;
	
	@Column(name="SharePost")
	@NotBlank(message = "Campo Obbligatorio")
	private String sharePost;
	
	@Column(name="ShareAvis")
	@NotBlank(message = "Campo Obbligatorio")
	private String shareAvis;
	
	@Column(name="ShareHertz")
	@NotBlank(message = "Campo Obbligatorio")
	private String shareHertz;
	
	@Column(name="ShareMaggiore")
	@NotBlank(message = "Campo Obbligatorio")
	private String shareMaggiore;
	
	@Column(name="ShareSixt")
	@NotBlank(message = "Campo Obbligatorio")
	private String shareSixt;
	
	@ManyToOne
	@JoinColumn(name = "IDVenditore")
	private Venditore venditore;
	
	@ManyToOne
	@JoinColumn(name = "IDAreaGeo")
	private AreaGeo areaGeo;
	
	@ManyToOne
	@JoinColumn(name = "IDAzienda")
	private Azienda azienda;
	
	@ManyToOne
	@JoinColumn(name = "IDSottocategoria")
	private Sottocategoria sottocategoria;

	public Integer getId() {
		return id;
	}

	public String getAnnoRiferimento() {
		return annoRiferimento;
	}

	public String getConfidenza() {
		return confidenza;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public Date getDataVisita() {
		return dataVisita;
	}

	public String getPotenzialeAzienda() {
		return potenzialeAzienda;
	}

	public String getPotenzialeEuropCar() {
		return potenzialeEuropCar;
	}

	public String getShareAnte() {
		return shareAnte;
	}

	public String getSharePost() {
		return sharePost;
	}

	public String getShareAvis() {
		return shareAvis;
	}

	public String getShareHertz() {
		return shareHertz;
	}

	public String getShareMaggiore() {
		return shareMaggiore;
	}

	public String getShareSixt() {
		return shareSixt;
	}

	public Venditore getVenditore() {
		return venditore;
	}

	public AreaGeo getAreaGeo() {
		return areaGeo;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public Sottocategoria getSottocategoria() {
		return sottocategoria;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAnnoRiferimento(String annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public void setConfidenza(String confidenza) {
		this.confidenza = confidenza;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public void setPotenzialeAzienda(String potenzialeAzienda) {
		this.potenzialeAzienda = potenzialeAzienda;
	}

	public void setPotenzialeEuropCar(String potenzialeEuropCar) {
		this.potenzialeEuropCar = potenzialeEuropCar;
	}

	public void setShareAnte(String shareAnte) {
		this.shareAnte = shareAnte;
	}

	public void setSharePost(String sharePost) {
		this.sharePost = sharePost;
	}

	public void setShareAvis(String shareAvis) {
		this.shareAvis = shareAvis;
	}

	public void setShareHertz(String shareHertz) {
		this.shareHertz = shareHertz;
	}

	public void setShareMaggiore(String shareMaggiore) {
		this.shareMaggiore = shareMaggiore;
	}

	public void setShareSixt(String shareSixt) {
		this.shareSixt = shareSixt;
	}

	public void setVenditore(Venditore venditore) {
		this.venditore = venditore;
	}

	public void setAreaGeo(AreaGeo areaGeo) {
		this.areaGeo = areaGeo;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public void setSottocategoria(Sottocategoria sottocategoria) {
		this.sottocategoria = sottocategoria;
	}
	
	

}
