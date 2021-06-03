package it.uniroma3.siw.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.repository.CollezioneRepository;

@Service
public class CollezioneService {

	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Transactional
	public Collezione inserisci(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}
	
	/*public Optional<Collezione> findById(Long id) {
		return collezioneRepository.findById(id);
	}*/
	
	@Transactional
	public Collezione findById(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean alreadyExists(Collezione collezione) {
		return collezione.getId().equals(collezioneRepository.findById(collezione.getId()));
	}

	public void save(Collezione collezione) {
		collezioneRepository.save(collezione);
	}

	public Object findAll() {
		return collezioneRepository.findAll();
	}
}
