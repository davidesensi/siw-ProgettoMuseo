package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Artista;
import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends CrudRepository<Artista,Long>{

	public List<Artista> findByNome(String nome);
	
	public List<Artista> findByNomeOrCognome(String nome, String cognome);
	
	public Optional<Artista> findById(Long id);
}
