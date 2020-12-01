package it.solving.pokeronline.repository.utente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solving.pokeronline.model.StatoUtente;
import it.solving.pokeronline.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, QueryByExampleExecutor <Utente>{
	
	Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);

}
