package it.uniroma3.siw.service;

<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 8c1e45b365edba4254077d3c3d8121829760f808
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;

@Service
public class OperaService {
<<<<<<< HEAD
=======

>>>>>>> 8c1e45b365edba4254077d3c3d8121829760f808
	@Autowired
	private OperaRepository operaRepository;
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
<<<<<<< HEAD
	public List<Opera> search(String titolo){
		return operaRepository.findByTitolo(titolo);
	}

=======
>>>>>>> 8c1e45b365edba4254077d3c3d8121829760f808
	public Optional<Opera> findById(Long id) {
		return operaRepository.findById(id);
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean alreadyExists(Opera opera) {
		return opera.getId().equals(operaRepository.findById(opera.getId()));
	}

	public void save(Opera opera) {
		operaRepository.save(opera);
	}

	public Object findAll() {
		return operaRepository.findAll();
	}
}
