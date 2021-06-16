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
		//DaVinci.setDataN('1452/04/15');
		DaVinci.setLuogoN("Anchiano");
		//DaVinci.setDataM("1519/05/02");
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
		
		Artista Andy = new Artista();
		Andy.setNome("Andy");
		Andy.setCognome("Warhol");
		//Andy.setDataN("1928/08/06");
		Andy.setLuogoN("Pittsburgh");
		//Andy.setDataM(1987-02-22);
		Andy.setLuogoM("New York");
		Andy.setNazionalita("Statunitense");
		
		Artista Roy = new Artista();
		Roy.setNome("Roy");
		Roy.setCognome("Lichtenstein");
		//Roy.setDataN("1923/10/27");
		Roy.setLuogoN("New York");
		//Roy.setDataM(1997-09-29);
		Roy.setLuogoM("New York");
		Roy.setNazionalita("Statunitense");
		
		Opera o = new Opera();
		o.setTitolo("La Gioconda");
		o.setAnno(1492);
		o.setDescrizione("Dipinto a olio su tavola di legno di pioppo");
		o.setArtista(DaVinci);
		
		Opera o1 = new Opera();
		o1.setTitolo("Il quarto stato");
		o1.setAnno(1901);
		o1.setDescrizione("Dipinto ad olio");
		o1.setArtista(Pelizza);
		
		Opera o2 = new Opera();
		o2.setTitolo("Ultima Cena");
		o2.setAnno(1498);
		o2.setDescrizione("Dipinto parietale ottenuto con una tecnica mista a secco su intonaco");
		o2.setArtista(DaVinci);
		
		Opera o3 = new Opera();
		o3.setTitolo("Campbell's Soup Cans");
		o3.setAnno(1962);
		o3.setDescrizione("Consiste in trentadue tele in polimero sintetico su tela");
		o3.setArtista(Andy);
		
		Opera o4 = new Opera();
		o4.setTitolo("Dittico di Marilyn");
		o4.setAnno(1962);
		o4.setDescrizione("Dipinto serigrafato composto da 50 immagini di Marilyn Monroe");
		o4.setArtista(Andy);
		
		Opera o5 = new Opera();
		o5.setTitolo("Ragazza che annega");
		o5.setAnno(1963);
		o5.setDescrizione("Dipinto in pittura a olio e polimero sintetico");
		o5.setArtista(Roy);
		
				
		Collezione cc = new Collezione();
		cc.setNome("Leonardo Il Genio");
		cc.setDescrizione("Le più grandi opere di Leonardo Da Vinci");
		cc.addOpera(o);
		cc.addOpera(o2);
		
		Collezione cc1 = new Collezione();
		cc1.setNome("Il Novecento");
		cc1.setDescrizione("Le opere che vanno dal 1900 al 1999");
		cc1.addOpera(o1);
		
		Collezione cc2 = new Collezione();
		cc2.setNome("Contemporanea");
		cc2.setDescrizione("Le opere che vanno dal 2000 ai giorni nostri");
		//cc2.addOpera();
		
		Collezione cc3 = new Collezione();
		cc3.setNome("Il Seicento");
		cc3.setDescrizione("Le opere che vanno dal 1600 al 1699");
		//cc3.addOpera();
		
		Collezione cc4 = new Collezione();
		cc4.setNome("Popular Art");
		cc4.setDescrizione("Le opere fine anni '50 inizi anni '60, con lo stile Pop Art");
		cc4.addOpera(o3);
		cc4.addOpera(o4);
		cc4.addOpera(o5);
		
		Curatore davide = new Curatore();
		davide.setNome("Davide");
		davide.setCognome("Sensi");
		davide.setEmail("davide.sen@email.com");
		davide.setTelefono("3387456975");
		//davide.addCollezione(cc);
		
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
		//stefano.addCollezione(cc1);
		
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
		
		o.setCollezione(cc);
		o1.setCollezione(cc1);
		o2.setCollezione(cc);
		o3.setCollezione(cc4);
		o4.setCollezione(cc4);
		o5.setCollezione(cc4);
		//cc.setCuratore(davide);
		//cc1.setCuratore(stefano);
		
		
		
		
		artistaRepository.save(DaVinci);
		artistaRepository.save(Pelizza);
		artistaRepository.save(Andy);
		artistaRepository.save(Roy);
		operaRepository.save(o);
		operaRepository.save(o1);
		operaRepository.save(o2);
		operaRepository.save(o3);
		operaRepository.save(o4);
		operaRepository.save(o5);		
		collezioneRepository.save(cc);
		collezioneRepository.save(cc1);
		collezioneRepository.save(cc2);
		collezioneRepository.save(cc3);
		collezioneRepository.save(cc4);
		curatoreRepository.save(davide);
		curatoreRepository.save(stefano);
		curatoreRepository.save(admin);
		credentialsService.saveCredentials(c1);
		credentialsService.saveCredentials(c2);
		credentialsService.saveCredentials(c3);
		
	}
	

}
