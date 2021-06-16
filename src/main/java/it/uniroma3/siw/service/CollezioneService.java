package it.uniroma3.siw.service;
import java.util.ArrayList;
import java.util.List;
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
	
	@Transactional
	public void update(Collezione collezione) {
		collezioneRepository.saveOrUpdate(collezione.getNome(), collezione.getDescrizione(),collezione.getId());
	}
		
	@Transactional
	public void rimuovi(Collezione collezione) {
		collezioneRepository.deleteById(collezione.getId());
	}	
	
	@Transactional
	public Collezione findById(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Collezione collezione) {
		List<Collezione> collezioni = this.collezioneRepository.findByNome(collezione.getNome());
		if (collezioni.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void save(Collezione collezione) {
		collezioneRepository.save(collezione);
	}

	@Transactional
	public List<Collezione> findAll() {
		Iterable<Collezione> collezioni = this.collezioneRepository.findAll();
		List<Collezione> daRitornare = new ArrayList<>();
		for(Collezione collezione : collezioni) {
			daRitornare.add(collezione);
		}
		return daRitornare;
	}


}
