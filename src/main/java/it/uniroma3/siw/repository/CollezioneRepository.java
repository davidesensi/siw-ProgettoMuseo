package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Collezione;

public interface CollezioneRepository extends CrudRepository<Collezione,Long>{

	public List<Collezione> findByNome(String name);
	
	public Optional<Collezione> findById(Long id);

	
	@Query("UPDATE Collezione SET nome = ?1, descrizione = ?2 WHERE id = ?3")
	@Modifying
	public void saveOrUpdate(String nome , String descrizione ,Long id);


	

}