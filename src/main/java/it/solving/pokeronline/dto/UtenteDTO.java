package it.solving.pokeronline.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.solving.pokeronline.model.StatoUtente;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.util.Util;

public class UtenteDTO {

	private Long id;

	private String username;

	private String password;

	private String nome;

	private String cognome;

	private String dataRegistrazione;

	private String esperienzaAccumulata;

	private String creditoAccumulato;

	private String idTavolo;

	private String ruolo;

	private String stato;

	private String[] ruoli;

	public UtenteDTO(String username, String password, String nome, String cognome) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}

	public UtenteDTO() {
		super();
	}

	public String[] getRuoli() {
		return ruoli;
	}

	public void setRuoli(String[] ruoli) {
		this.ruoli = ruoli;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(String dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public String getEsperienzaAccumulata() {
		return esperienzaAccumulata;
	}

	public void setEsperienzaAccumulata(String esperienzaAccumulata) {
		this.esperienzaAccumulata = esperienzaAccumulata;
	}

	public String getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(String creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}

	public String getIdTavolo() {
		return idTavolo;
	}

	public void setIdTavolo(String idTavolo) {
		this.idTavolo = idTavolo;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome))
			result.add("Il campo NOME non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			result.add("Il campo COGNOME non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			result.add("Il campo USERNAME non può essere vuoto");
		if (StringUtils.isBlank(this.password))
			result.add("Il campo PASSWORD non può essere vuoto");

		return result;
	}

	public List<String> errorsSearch() {
		List<String> result = new ArrayList<String>();

		if (!Util.isEmptyOrNull(this.dataRegistrazione)) {
			try {
				Date dataParse = new SimpleDateFormat("dd-mm-yyyy").parse(getDataRegistrazione());
			} catch (ParseException e) {
				result.add("il campo DATA non è valido");
			}
		}
		return result;
	}

	public List<String> errorsUpdate() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.nome))
			result.add("Il campo NOME non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			result.add("Il campo COGNOME non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			result.add("Il campo USERNAME non può essere vuoto");
		if (this.ruoli == null) {
			result.add("Il campo RUOLO non può essere vuoto");
		}

		return result;
	}

	public static Utente buildModelFromDto(UtenteDTO utenteDTO) {
		Utente result = new Utente();
		if ((utenteDTO.getId()) != null) {
			result.setId(utenteDTO.getId());
		}
		if (!Util.isEmptyOrNull(utenteDTO.getNome())) {
			result.setNome(utenteDTO.getNome());
		}
		if (!Util.isEmptyOrNull(utenteDTO.getCognome())) {
			result.setCognome(utenteDTO.getCognome());
		}
		if (!Util.isEmptyOrNull(utenteDTO.getPassword())) {
			result.setPassword(utenteDTO.getPassword());
		}
		if (!Util.isEmptyOrNull(utenteDTO.getUsername())) {
			result.setUsername(utenteDTO.getUsername());
		}
		if (!Util.isEmptyOrNull(utenteDTO.getStato())) {
			result.setStato(Enum.valueOf(StatoUtente.class, utenteDTO.getStato()));
		}
		return result;
	}

}
