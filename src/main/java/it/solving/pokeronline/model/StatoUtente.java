package it.solving.pokeronline.model;

import java.util.EnumSet;

public enum StatoUtente {
	CREATO ("creato"),
	ATTIVO ("attivo"),
	DISABILITATO ("disabilitato");
	
    private String stringaStato;
	
	public String toString() {
		return stringaStato;
	}
	
	StatoUtente(String stringaStato) {
		this.stringaStato=stringaStato;
	}
	
	public static final EnumSet<StatoUtente> allStato = EnumSet.of(CREATO,ATTIVO, DISABILITATO);

}

