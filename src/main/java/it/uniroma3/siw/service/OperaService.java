package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Collezione;
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
	/** Rimuove l'id della collezione dall'opera con idOpera **/
	public boolean removeCollezioneIdByOperaId(Long idOpera) {
		Opera opera = this.findById(idOpera);
		if(idOpera != null) {
			int opereModificate = this.operaRepository.removeCollezioneIdByOperaId(opera.getId());
			if(opereModificate == 1) return true;
		}
		return false;
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

	@Transactional
	public List<Opera> selectOpere(List<Long> idOpere) {
		List<Opera> daRit = new ArrayList<>();
		for(Long id : idOpere) {
			daRit.add(this.operaRepository.findById(id).get());
		}
		return daRit;
	}

	@Transactional
	public void setCollezione(Collezione collezione, Long id) {
		this.operaRepository.saveOrUpdate(collezione, id);
		
	}

	@Transactional
	public void rimuovi(Long id) {
		operaRepository.rimuoviOperaById(id);
		
	}

	@Transactional
	public boolean removeArtistaIdByOperaId(Long idOpera , Long idArtista) {
		if(idOpera != null) {
			int opereModificate = this.operaRepository.removeArtistaIdByOperaId(idOpera,idArtista);
			if(opereModificate == 1) return true;
		}
		return false;
	}
	
	@Transactional
	public boolean removeOperaIdFromCollezioneId( Long idOpera , Long idCollezione) {
		if(idOpera != null) {
			int opereModificate = this.operaRepository.removeOperaIdFromCollezioneId(idOpera,idCollezione);
			if(opereModificate == 1) return true;
		}
		return false;
	}

	@Transactional
	public void update(Opera opera, Long id) {
		operaRepository.saveOrUpdateOpera(opera.getTitolo(),opera.getAnno(),opera.getDescrizione(),id);
	}
}
