package it.uniroma3.siw.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operaRepository;
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}

	public List<Opera> search(String titolo){
		return operaRepository.findByTitolo(titolo);
	}

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
