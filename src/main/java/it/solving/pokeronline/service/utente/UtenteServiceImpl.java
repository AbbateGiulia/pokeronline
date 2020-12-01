package it.solving.pokeronline.service.utente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.solving.pokeronline.model.StatoUtente;
import it.solving.pokeronline.model.Utente;
import it.solving.pokeronline.repository.utente.UtenteRepository;

@Component
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public List<Utente> listAllUtenti() {
		return (List<Utente>) utenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long id) {
		return utenteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		 utenteRepository.save(utenteInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteRepository.save(utenteInstance);
		
	}

	@Override
	public void rimuovi(Utente utenteInstance) {
		utenteRepository.delete(utenteInstance);
		
	}

	@Override
	public Utente eseguiAccesso(String username, String password) {
		return (Utente) utenteRepository.findByUsernameAndPasswordAndStato(username, password, StatoUtente.ATTIVO);
	}

}
