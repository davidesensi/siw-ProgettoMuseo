package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.repository.ArtistaRepository;

@Service
public class ArtistaService {

	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Transactional
	public Artista inserisci(Artista artista) {
		return artistaRepository.save(artista);
	}
	
	public List<Artista> search(String nome, String cognome){
		return artistaRepository.findByNomeOrCognome(nome,cognome);
	}

	public Optional<Artista> findById(Long id) {
		return artistaRepository.findById(id);
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean alreadyExists(Artista artista) {
		return artista.getId().equals(artistaRepository.findById(artista.getId()));
	}

	public void save(Artista artista) {
		artistaRepository.save(artista);
	}

	public Object findAll() {
		return artistaRepository.findAll();
	}

	 

	 
}
