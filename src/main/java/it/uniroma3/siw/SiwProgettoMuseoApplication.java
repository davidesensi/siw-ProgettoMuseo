package it.uniroma3.siw;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.siw.model.Artista;

@SpringBootApplication
public class SiwProgettoMuseoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiwProgettoMuseoApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("museo-unit");
		EntityManager em = emf.createEntityManager();
		
		Artista DaVinci = new Artista();
		DaVinci.setNome("Leonardo");
		DaVinci.setCognome("Da Vinci");
		DaVinci.setNazionalita("Italiana");
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		em.persist(DaVinci);
		
		tx.commit();
		
		em.close();
		emf.close();
		
	}

}
