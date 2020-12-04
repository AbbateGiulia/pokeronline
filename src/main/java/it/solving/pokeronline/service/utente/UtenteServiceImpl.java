package it.solving.pokeronline.service.utente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
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
	
	@PersistenceContext
	private EntityManager entityManager;

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

	@Override
	public List<Utente> findByExample(Utente example) {
		String query = "select u from Utente u left join fetch u.ruoli r where 1=1";		
		if (StringUtils.isNotEmpty(example.getNome()))
			query += " and u.nome like '%" + example.getNome() + "%' ";
		if (StringUtils.isNotEmpty(example.getCognome()))
			query += " and u.cognome like '%" + example.getCognome() + "%' ";
		if (StringUtils.isNotEmpty(example.getUsername()))
			query += " and u.username like '%" + example.getUsername() + "%' ";
		if ((example.getDataRegistrazione())!= null)
			query += " and u.dataRegistrazione = '" + example.getDataRegistrazione()+ "'";
		if (example.getStato() != null)
			query += " and u.stato = '" + example.getStato() + "' ";
		if (example.getRuoli().size() > 0)
			query += " and r.id = " + example.getRuoli().iterator().next().getId() + " ";

		return entityManager.createQuery(query, Utente.class).getResultList();
	}

	@Override
	public Utente caricaSingoloUtenteEager(Long id) {
		return (Utente) utenteRepository.getUtenteEager(id);
	}

	@Override
	public List<Utente> listAllUtentiSpecial(String descrizione, String descrizione2, String term) {
		return utenteRepository.listAllSpecialPlayer(descrizione, descrizione2, term);
	}

	@Override
	public List<Utente> cercaByUsernameILike( String term) {
		return utenteRepository.findAllByUsernameContaining(term);
	}
		
	}


