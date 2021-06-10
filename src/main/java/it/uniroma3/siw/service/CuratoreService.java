package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.repository.CuratoreRepository;

@Service
public class CuratoreService {
	@Autowired
	private CuratoreRepository curatoreRepository;
	
	@Transactional
	public Curatore inserisci(Curatore curatore) {
		return curatoreRepository.save(curatore);
	}
	
	public List<Curatore> search(String nome, String cognome){
		return curatoreRepository.findByNomeOrCognome(nome,cognome);
	}

	/*public Optional<Curatore> findById(Long id) {
		return curatoreRepository.findById(id);
	}*/
	
	@Transactional
	public Curatore findById(Long id) {
		Optional<Curatore> optional = curatoreRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Curatore curatore) {
		List<Curatore> curatori = this.curatoreRepository.findByNomeOrCognome(curatore.getNome(), curatore.getCognome());
		if (curatori.size() > 0)
			return true;
		else 
			return false;
	}

	public void save(Curatore curatore) {
		curatoreRepository.save(curatore);
	}

	public Object findAll() {
		return curatoreRepository.findAll();
	}
}
