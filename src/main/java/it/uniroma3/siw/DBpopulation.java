package it.uniroma3.siw;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Curatore;
import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.repository.ArtistaRepository;
import it.uniroma3.siw.repository.CollezioneRepository;
import it.uniroma3.siw.repository.CuratoreRepository;
import it.uniroma3.siw.repository.OperaRepository;
import it.uniroma3.siw.service.CredentialsService;



@Component
public class DBpopulation implements ApplicationRunner{
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
	private CuratoreRepository curatoreRepository;
	
	@Autowired
	private CollezioneRepository collezioneRepository;
	
	@Autowired
	private OperaRepository operaRepository;
	
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
		
		Artista Pelizza = new Artista();
		Pelizza.setNome("Giuseppe");
		Pelizza.setCognome("Pelizza");
		//Pelizza.setDataN("1868/07/28");
		Pelizza.setLuogoN("Volpedo");
		//Pelizza.setDataM(1907-06-14);
		Pelizza.setLuogoM("Volpedo");
		Pelizza.setNazionalita("Italiana");
		
		
		Opera o = new Opera();
		o.setTitolo("La Gioconda");
		o.setAnno(1492);
		o.setArtista(DaVinci);
		
		Opera o1 = new Opera();
		o1.setTitolo("Il quarto stato");
		o1.setAnno(1901);
		o1.setArtista(Pelizza);
		
		Opera o2 = new Opera();
		o2.setTitolo("Ultima Cena");
		o2.setAnno(1498);
		o2.setArtista(DaVinci);
		
				
		Collezione cc = new Collezione();
		cc.setNome("Leonardo Il Genio");
		cc.addOpera(o);
		cc.addOpera(o2);
		
		Collezione cc1 = new Collezione();
		cc1.setNome("Il Novecento");
		cc1.addOpera(o1);
		
		o.setCollezione(cc);
		o1.setCollezione(cc1);
		o2.setCollezione(cc);
		
		Curatore davide = new Curatore();
		davide.setNome("Davide");
		davide.setCognome("Sensi");
		davide.setEmail("davide.sen@email.com");
		davide.setTelefono("3387456975");
		
		Credentials c1 = new Credentials();
		c1.setUsername("davide");
		c1.setPassword("davide");
		c1.setRole("ADMIN");
		c1.setCuratore(davide);
		
		Curatore stefano = new Curatore();
		stefano.setNome("Stefano");
		stefano.setCognome("Zappasodi");
		stefano.setEmail("stefano.zap@email.com");
		stefano.setTelefono("3387425312");
		
		Credentials c2 = new Credentials();
		c2.setUsername("stefano");
		c2.setPassword("stefano");
		c2.setRole("ADMIN");
		c2.setCuratore(stefano);
		
		Curatore admin = new Curatore();
		admin.setNome("Admin");
		admin.setCognome("Admin");
		admin.setEmail("admin.adm@email.com");
		admin.setTelefono("3387453535");
		
		Credentials c3 = new Credentials();
		c3.setUsername("admin");
		c3.setPassword("admin");
		c3.setRole("ADMIN");
		c3.setCuratore(admin);
		
		
		artistaRepository.save(DaVinci);
		artistaRepository.save(Pelizza);
		operaRepository.save(o);
		operaRepository.save(o1);
		operaRepository.save(o2);
		collezioneRepository.save(cc);
		collezioneRepository.save(cc1);
		curatoreRepository.save(davide);
		curatoreRepository.save(stefano);
		curatoreRepository.save(admin);
		credentialsService.saveCredentials(c1);
		credentialsService.saveCredentials(c2);
		credentialsService.saveCredentials(c3);
		
	}
	

}
