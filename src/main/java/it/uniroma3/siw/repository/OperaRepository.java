package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

<<<<<<< HEAD
import it.uniroma3.siw.model.Opera;


public interface OperaRepository extends CrudRepository<Opera,Long>{
	
	public List<Opera> findByTitolo(String titolo);
	
=======
import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Opera;

public interface OperaRepository extends CrudRepository<Opera,Long>{

	public List<Opera> findByTitolo(String titolo);
	
	public List<Artista> findByTitoloOrAnno(String titolo, int anno);
	
>>>>>>> 8c1e45b365edba4254077d3c3d8121829760f808
	public Optional<Opera> findById(Long id);
}
