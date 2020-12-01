package it.solving.pokeronline.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tavolo")
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "denominazione")
	private String denominazione;
	
	@Column(name = "esperienza_minima")
	private Integer esperienzaMinima;
	
	@Column(name = "credito_minimo")
	private Integer creditoMinimo;
	
	@Column(name = "data_creazione")
	private LocalDate dataCreazione;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_creatore_id")
	private Utente utenteCreatore;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo")
	private Set <Utente> giocatori= new HashSet<>();
	
	

	public Tavolo() {
		super();
	}
	
	

	public Tavolo(String denominazione, Integer esperienzaMinima, Integer creditoMinimo, LocalDate dataCreazione) {
		super();
		this.denominazione = denominazione;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.dataCreazione = dataCreazione;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Integer getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Integer esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Integer getCreditoMinimo() {
		return creditoMinimo;
	}

	public void setCreditoMinimo(Integer creditoMinimo) {
		this.creditoMinimo = creditoMinimo;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Utente getUtenteCreatore() {
		return utenteCreatore;
	}

	public void setUtenteCreatore(Utente utenteCreatore) {
		this.utenteCreatore = utenteCreatore;
	}

	public Set<Utente> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Set<Utente> giocatori) {
		this.giocatori = giocatori;
	}
	
	
	

}
