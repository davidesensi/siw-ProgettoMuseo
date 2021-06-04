package it.uniroma3.siw;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.siw.model.Artista;
import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.model.Opera;

@SpringBootApplication
public class SiwProgettoMuseoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwProgettoMuseoApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("museo-unit");
		EntityManager em = emf.createEntityManager();
					
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
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		em.persist(DaVinci);
		em.persist(o);
		em.persist(c);
		
		tx.commit();
		
		em.close();
		emf.close();
		
	}

}
