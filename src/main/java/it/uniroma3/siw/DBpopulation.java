package it.uniroma3.siw;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ArtistaRepository;
import it.uniroma3.siw.repository.CollezioneRepository;
import it.uniroma3.siw.repository.OperaRepository;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.CredentialsService;



@Component
public class DBpopulation implements ApplicationRunner{
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Autowired
	private OperaRepository operaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		this.addAll();
	}
	
	private void addAll() {
		
		System.out.print("Popolazione DB con Artisti, Collezioni e Opere");
		
		
		Artista DaVinci = new Artista();
		DaVinci.setNome("Leonardo");
		DaVinci.setCognome("Da Vinci");
		//DaVinci.setDataN("1452/04/15");
		DaVinci.setLuogoN("Anchiano");
		//DaVinci.setDataM(1519-05-02);
		DaVinci.setLuogoM("Amboise");
		DaVinci.setNazionalita("Italiana");
		
		
		Opera o = new Opera();
		o.setTitolo("La Gioconda");
		o.setAnno(1492);
		o.setArtista(DaVinci);
		
				
		Collezione c = new Collezione();
		c.setNome("Leonardo Il Genio");
		c.addOpera(o);
		
		o.setCollezione(c);
		
		User davide = new User();
		davide.setNome("Davide");
		davide.setCognome("Sensi");
		
		Credentials c1 = new Credentials();
		c1.setUsername("davide");
		c1.setPassword("davide");
		c1.setRole("DEFAULT");
		c1.setUser(davide);
		
		User stefano = new User();
		davide.setNome("Stefano");
		davide.setCognome("Zappasodi");
		
		Credentials c2 = new Credentials();
		c2.setUsername("stefano");
		c2.setPassword("stefano");
		c2.setRole("DEFAULT");
		c2.setUser(stefano);
		
		User admin = new User();
		davide.setNome("Admin");
		davide.setCognome("Admin");
		
		Credentials c3 = new Credentials();
		c3.setUsername("admin");
		c3.setPassword("admin");
		c3.setRole("ADMIN");
		c3.setUser(admin);
		
		
		artistaRepository.save(DaVinci);
		operaRepository.save(o);
		collezioneRepository.save(c);
		userRepository.save(davide);
		userRepository.save(stefano);
		userRepository.save(admin);
		credentialsService.saveCredentials(c1);
		credentialsService.saveCredentials(c2);
		credentialsService.saveCredentials(c3);
		
	}
	

}
