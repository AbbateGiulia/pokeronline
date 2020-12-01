package it.solving.pokeronline.model;

import java.util.EnumSet;

public enum StatoUtente {
	CREATO,ATTIVO,DISABILITATO;
	
	public static final EnumSet<StatoUtente> allStato = EnumSet.of(CREATO,ATTIVO, DISABILITATO);

}

