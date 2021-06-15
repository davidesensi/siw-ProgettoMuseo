package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,Long>{
	
	public List<Opera> findByTitolo(String titolo);
	

	public Optional<Opera> findById(Long id);

	@Query("UPDATE Opera o SET o.collezione = ?1 WHERE o.id = ?2")
	@Modifying
	public void saveOrUpdate(Collezione collezione, Long id);
	
	@Query("UPDATE Opera o SET o.collezione = NULL WHERE o.id = ?1")
	@Modifying
	public int removeCollezioneIdByOperaId(Long idOpera);

	@Query("UPDATE Opera o SET o.artista = NULL WHERE o.id = ?1")
	@Modifying
	public int removeArtistaIdByOperaId(Long idOpera);
	
}