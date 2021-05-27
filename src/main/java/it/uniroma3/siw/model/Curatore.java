package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Curatore {


	//definizione variabili

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;

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

	//Associazione OneToMany tra Curatore e Collezione, viene mappato per identicficare che  due riferiemnti 
	//in Curatore e Collezione si riferiscono alla stessa cosa
	@OneToMany(mappedBy = "curatore", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List <Collezione> collezioni;

	//Costruttore no args
	public Curatore() {
		this.collezioni = new ArrayList<>();
	}

	public Curatore(String nome, String cognome, LocalDate dataN, String luogoN, 
			String email, String telefono) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataN = dataN;
		this.luogoN = luogoN;
		this.email = email;
		this.telefono = telefono;		
	}

	/****************************************************************************************************/
	/******************************************METODI GET E SET******************************************/
	/****************************************************************************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public List<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}	

}
