package it.solving.pokeronline.service.tavolo;

import java.util.List;

import it.solving.pokeronline.model.Tavolo;

public interface TavoloService {
	
	public List<Tavolo> listAllTavolo();
	
	public List<Tavolo> listAllTavoloUtente(Long id);

	public Tavolo caricaSingoloTavolo(Long id);
	
	public Tavolo caricaSingoloTavoloEager(Long id);
	
	public void aggiorna(Tavolo tavoloInstance);

	public void inserisciNuovo(Tavolo tavoloInstance);

	public void rimuovi(Tavolo tavoloInstance);
	
	public List<Tavolo> findByExample(Tavolo example);

}
