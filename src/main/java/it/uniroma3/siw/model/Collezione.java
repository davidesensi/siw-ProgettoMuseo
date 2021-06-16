package it.uniroma3.siw.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Collezione {

	//definizioni attributi

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable=false)
	private String nome;

	//@Column(nullable=false)
	private String descrizione;

	//Associazione ManyToOne tra Curatore e Collezione, viene mappato per identicficare che  due riferiemnti 
	//in Collezione e Curatore si riferiscono alla stessa cosa
	@ManyToOne
	private Curatore curatore;

	//Associazione OneToMany tra Collezione e Opera, viene mappato per identicficare che  due riferiemnti 
	//in Collezione e Opera si riferiscono alla stessa cosa
	@OneToMany(mappedBy = "collezione", cascade = {CascadeType.ALL})
	private List<Opera> opere;

	//Costruttore no args
	public Collezione() {
		this.opere = new ArrayList<>();
	}

	public Collezione(String nome, String descrizione, Curatore curatore) {
		this.nome = nome;
		this.descrizione = descrizione;	
		this.curatore = curatore;
	}
	
	public void addOpera(Opera o) {
		this.opere.add(o);
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Curatore getCuratore() {
		return curatore;
	}

	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}

	public List<Opera> getOpere() {
		return this.opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}


}
