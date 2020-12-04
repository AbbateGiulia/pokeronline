package it.solving.pokeronline.repository.utente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solving.pokeronline.model.StatoUtente;
import it.solving.pokeronline.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, QueryByExampleExecutor<Utente> {

	Utente findByUsernameAndPasswordAndStato(String username, String password, StatoUtente stato);

	List<Utente> findAllByUsernameContaining(String term);

	// per caricare utente
	@Query("from Utente u left join fetch u.ruoli r where u.id =?1")
	Utente getUtenteEager(Long id);

	// lista di utenti solo special
	@Query("select distinct u from Utente u join fetch u.ruoli r where ( r.descrizione =?1 or r.descrizione=?2 ) and u.username like %?3%")
	List<Utente> listAllSpecialPlayer(String descrizione, String descrizione2, String term);
}
