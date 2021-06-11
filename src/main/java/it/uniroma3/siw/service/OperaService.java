package it.uniroma3.siw.service;

import java.util.ArrayList;
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

	/*public List<Opera> search(String titolo){
		return operaRepository.findByTitolo(titolo);
	}*/

	/*public Optional<Opera> findById(Long id) {
		return operaRepository.findById(id);
	}*/
	
	@Transactional
	public Opera findById(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}	
	

	@Transactional
	public boolean alreadyExists(Opera opera) {
		List<Opera> opere = this.operaRepository.findByTitolo(opera.getTitolo());
		if (opere.size() > 0)
			return true;
		else 
			return false;
	}

	public void save(Opera opera) {
		operaRepository.save(opera);
	}


	@Transactional
	public List<Opera> findAll() {
		Iterable<Opera> opere = this.operaRepository.findAll();
		List<Opera> daRitornare = new ArrayList<>();
		for(Opera opera : opere) {
			daRitornare.add(opera);
		}
		return daRitornare;
	}
}
