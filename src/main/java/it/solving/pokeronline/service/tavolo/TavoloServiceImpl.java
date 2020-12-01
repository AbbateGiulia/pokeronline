package it.solving.pokeronline.service.tavolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import it.solving.pokeronline.model.Tavolo;
import it.solving.pokeronline.repository.tavolo.TavoloRepository;

@Component
public class TavoloServiceImpl implements TavoloService {
	
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	

	@Transactional(readOnly = true)
	public List<Tavolo> listAllTavolo() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Tavolo caricaSingoloTavolo(Long id) {
		return (Tavolo) tavoloRepository.findById(id).orElse(null);		}

	@Transactional
	public void aggiorna(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Tavolo tavoloInstance) {
		tavoloRepository.save(tavoloInstance);
	}

	@Transactional
	public void rimuovi(Tavolo tavoloInstance) {
		tavoloRepository.delete(tavoloInstance);
	}

	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		String query = "select t from Tavolo t join t.utenteCreatore c where c.id="+ example.getUtenteCreatore().getId();		
		if (StringUtils.isNotEmpty(example.getDenominazione()))
			query += " and t.denominazione like '%" + example.getDenominazione() + "%' ";
		if ((example.getDataCreazione())!= null)
			query += " and t.dataCreazione = '" + example.getDataCreazione()+ "'";
		if (example.getCreditoMinimo() != null && example.getCreditoMinimo() > 0)
			query += " and t.creditoMinimo = " + example.getCreditoMinimo();

		return entityManager.createQuery(query, Tavolo.class).getResultList();
	}

	@Override
	public List<Tavolo> listAllTavoloUtente(Long id) {
		 return tavoloRepository.findAllTavoloByUtenteCreatore_id(id);
	}

	@Override
	public Tavolo caricaSingoloTavoloEager(Long id) {
		return (Tavolo) tavoloRepository.getTavoloEager(id);
	}

}
