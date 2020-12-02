package it.solving.pokeronline.repository.tavolo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solving.pokeronline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>,QueryByExampleExecutor <Tavolo>{

	 List <Tavolo> findAllTavoloByUtenteCreatore_id(Long id);
	 
	// per caricare tavolo
			@Query("from Tavolo t left join fetch t.giocatori g where t.id =?1")
			Tavolo getTavoloEager(Long id);
}
