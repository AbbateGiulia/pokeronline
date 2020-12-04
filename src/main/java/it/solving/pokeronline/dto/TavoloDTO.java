package it.solving.pokeronline.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.util.Util;

public class TavoloDTO {

	private Long id;

	private String denominazione;

	private String esperienzaMinima;

	private String creditoMinimo;

	private String dataCreazione;

	private String idCreatore;

	public TavoloDTO() {
		super();
	}

	public TavoloDTO(String idCreatore) {
		super();
		this.idCreatore = idCreatore;
	}

	public TavoloDTO(String denominazione, String creditoMinimo, String dataCreazione) {
		super();
		this.denominazione = denominazione;
		this.creditoMinimo = creditoMinimo;
		this.dataCreazione = dataCreazione;
	}

	public TavoloDTO(String denominazione, String esperienzaMinima, String creditoMinimo, String idCreatore) {
		super();
		this.denominazione = denominazione;
		this.esperienzaMinima = esperienzaMinima;
		this.creditoMinimo = creditoMinimo;
		this.idCreatore = idCreatore;
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

	public String getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(String esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public String getCreditoMinimo() {
		return creditoMinimo;
	}

	public void setCreditoMinimo(String creditoMinimo) {
		this.creditoMinimo = creditoMinimo;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getIdCreatore() {
		return idCreatore;
	}

	public void setIdCreatore(String idCreatore) {
		this.idCreatore = idCreatore;
	}

	public List<String> errors() {
		List<String> result = new ArrayList<String>();
		if (StringUtils.isBlank(this.denominazione))
			result.add("Il campo DENOMINAZIONE non può essere vuoto");
		if (!Util.isNumber(this.esperienzaMinima) || StringUtils.isBlank(this.esperienzaMinima))
			result.add("Il campo esperienza vuoto o non valido");
		if (!Util.isNumber(this.creditoMinimo)|| StringUtils.isBlank(this.creditoMinimo) )
			result.add("Il campo credito vuoto o non valido");

		return result;
	}

	public List<String> errorsSearch() {
		List<String> result = new ArrayList<String>();
		if (!StringUtils.isBlank(this.esperienzaMinima) && !Util.isNumber(this.esperienzaMinima))
			result.add("campo esperienza deve essere un numero");
		if (!StringUtils.isBlank(this.creditoMinimo) && !Util.isNumber(this.creditoMinimo))
			result.add("campo credito deve essere un numero");
		if (!StringUtils.isBlank(this.creditoMinimo) && Integer.parseInt(this.creditoMinimo) < 0)
			result.add("campo credito inferiore a zero");
		if (!Util.isNumber(this.idCreatore))
			result.add("user creatore non valido");
		if (!Util.isEmptyOrNull(this.dataCreazione)) {
			try {
				new SimpleDateFormat("dd-mm-yyyy").parse(getDataCreazione());
			} catch (ParseException e) {
				result.add("data non valida");
			}
		}

		return result;
	}
	
	public List<String> errorsSearchPartita() {
		List<String> result = new ArrayList<String>();
		if (!StringUtils.isBlank(this.creditoMinimo) && !Util.isNumber(this.creditoMinimo))
			result.add("campo credito deve essere un numero");
		if (!StringUtils.isBlank(this.creditoMinimo) && Integer.parseInt(this.creditoMinimo) < 0)
			result.add("campo credito inferiore a zero");
		if (!Util.isEmptyOrNull(this.dataCreazione)) {
			try {
				Date dataParse = new SimpleDateFormat("dd-mm-yyyy").parse(getDataCreazione());
			} catch (ParseException e) {
				result.add("data non valida");
			}
		}

		return result;
	}

	public static Tavolo buildModelFromDto(TavoloDTO tavoloDTO) {
		Tavolo result = new Tavolo();
		if (!Util.isEmptyOrNull(tavoloDTO.getDenominazione())) {
			result.setDenominazione(tavoloDTO.getDenominazione());
		}
		if (!Util.isEmptyOrNull(tavoloDTO.getEsperienzaMinima())) {
			result.setEsperienzaMinima(Integer.parseInt(tavoloDTO.getEsperienzaMinima()));
		}
		if (!Util.isEmptyOrNull(tavoloDTO.getCreditoMinimo())) {
			result.setCreditoMinimo(Integer.parseInt(tavoloDTO.getCreditoMinimo()));
		}
		if (!Util.isEmptyOrNull(tavoloDTO.getDataCreazione())) {
			result.setDataCreazione(LocalDate.parse(tavoloDTO.getDataCreazione()));
		}
		return result;
	}

}
