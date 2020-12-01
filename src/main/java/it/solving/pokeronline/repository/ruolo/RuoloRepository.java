package it.solving.pokeronline.repository.ruolo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.solving.pokeronline.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>,QueryByExampleExecutor <Ruolo> {

}
