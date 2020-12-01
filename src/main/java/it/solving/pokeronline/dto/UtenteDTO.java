package it.solving.pokeronline.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.solving.pokeronline.model.Utente;

public class UtenteDTO {
	
		private Long id;
				
		private String username;
				
		private String password;
				
		private String nome;
				
		private String cognome;		
		
		private String dataRegistrazione;
				
		private String esperienzaAccumulata ;	
		
		private String creditoAccumulato ;
		
		private String idTavolo;

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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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
		
			
		public List<String> errors(){
			List<String> result = new ArrayList<String>();
			if(StringUtils.isBlank(this.nome))
				result.add("Il campo nome non può essere vuoto");
			if(StringUtils.isBlank(this.cognome))
				result.add("Il campo codice non può essere vuoto");
			if(StringUtils.isBlank(this.username))
				result.add("Il campo username non può essere vuoto");
			if(StringUtils.isBlank(this.password))
				result.add("Il campo password non può essere vuoto");
			
			return result;
		}
		
		public static Utente buildModelFromDto(UtenteDTO utenteDTO) {
			Utente result = new Utente();
			result.setNome(utenteDTO.getNome());
			result.setCognome(utenteDTO.getCognome());
			result.setUsername(utenteDTO.getUsername());
			result.setPassword(utenteDTO.getPassword());
			return result;
		}


}
