package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Curatore {
	
	
	//definizione variabili
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private LocalDate dataN;
	
	@Column(nullable = false)
	private String luogoN;
	
	//@Column(nullable = false)
	//private String matricola;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String telefono;
	
	@OneToMany
	private List<Collezione> collezioni;

	
	
	//get and set
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDataN() {
		return dataN;
	}

	public void setDataN(LocalDate dataN) {
		this.dataN = dataN;
	}

	public String getLuogoN() {
		return luogoN;
	}

	public void setLuogoN(String luogoN) {
		this.luogoN = luogoN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	
	
	

}
