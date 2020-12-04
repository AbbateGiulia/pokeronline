package it.solving.pokeronline.service.ruolo;

import java.util.List;

import it.solving.pokeronline.model.Ruolo;

public interface RuoloService {

	public List<Ruolo> listAllRuolo();

	public Ruolo caricaSingoloRuolo(Long id);

	public void aggiorna(Ruolo ruoloInstance);

	public void inserisciNuovo(Ruolo ruoloInstance);

	public void rimuovi(Ruolo ruoloInstance);

}
