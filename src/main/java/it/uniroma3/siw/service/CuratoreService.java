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

	public Optional<Curatore> findById(Long id) {
		return curatoreRepository.findById(id);
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean alreadyExists(Curatore curatore) {
		return curatore.getId().equals(curatoreRepository.findById(curatore.getId()));
	}

	public void save(Curatore curatore) {
		curatoreRepository.save(curatore);
	}

	public Object findAll() {
		return curatoreRepository.findAll();
	}
}
