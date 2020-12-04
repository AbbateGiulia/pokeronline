package it.solving.pokeronline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	// se non uso questa annotation viene gestito come un intero
	@Enumerated(EnumType.STRING)
	private CodiceRuolo codice;

	public Ruolo() {
	}

	public Ruolo(String descrizione, CodiceRuolo codice) {
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public CodiceRuolo getCodice() {
		return codice;
	}

	public void setCodice(CodiceRuolo codice) {
		this.codice = codice;
	}

}
