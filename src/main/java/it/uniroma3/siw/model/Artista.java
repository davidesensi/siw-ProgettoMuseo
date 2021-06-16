package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Artista {
	//definizioni attributi
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String nome;

	@Column(nullable=false)
	private String cognome;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataN;
	
	@Column(nullable=false)
	private String luogoN;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataM;
	private String luogoM;

	@Column(nullable=false)
	private String nazionalita;

	//Associazione OneToMany tra Artista e Opera, viene mappato per identicficare che  due riferiemnti 
	//in Artista e Opera si riferiscono alla stessa cosa
	@OneToMany(mappedBy = "artista", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List <Opera> opere;

	//Costruttore no args
	public Artista() {
		this.opere = new ArrayList<>();		
	}

	public Artista(String nome, String cognome, LocalDate dataN, String luogoN, 
			LocalDate dataM, String luogoM, String nazionalita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataN = dataN;
		this.luogoN = luogoN;
		this.dataM = dataM;
		this.luogoM = luogoM;
		this.nazionalita = nazionalita;		
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

	public LocalDate getDataM() {
		return dataM;
	}

	public void setDataM(LocalDate dataM) {
		this.dataM = dataM;
	}

	public String getLuogoM() {
		return luogoM;
	}

	public void setLuogoM(String luogoM) {
		this.luogoM = luogoM;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}


}
