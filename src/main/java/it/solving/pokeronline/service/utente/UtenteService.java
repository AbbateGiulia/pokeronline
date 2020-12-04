package it.solving.pokeronline.service.utente;

import java.util.List;

import it.solving.pokeronline.model.Utente;



public interface UtenteService {

	public List<Utente> listAllUtenti();
	
	public List<Utente> cercaByUsernameILike( String term);
	
	public List<Utente> listAllUtentiSpecial(String descrizione, String descrizione2, String term);

	public Utente caricaSingoloUtente(Long id);
	
	public Utente caricaSingoloUtenteEager(Long id);
	
	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);
	
	public Utente eseguiAccesso(String username, String password);
	
	public List <Utente> findByExample (Utente example); 

}
