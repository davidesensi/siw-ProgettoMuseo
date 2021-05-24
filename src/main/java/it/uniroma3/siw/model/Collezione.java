package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Collezione {
	
	//definizioni attributi
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	private String description;
	
	//Associazione ManyToOne tra Curatore e Collezione, viene mappato per identicficare che  due riferiemnti 
	//in Collezione e Curatore si riferiscono alla stessa cosa
	@ManyToOne
	private Curatore curatore;
	
	//Associazione OneToMany tra Collezione e Opera, viene mappato per identicficare che  due riferiemnti 
	//in Collezione e Opera si riferiscono alla stessa cosa
	@OneToMany(mappedBy = "opera", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List <Opera>opere;
	
	
	/****************************************************************************************************/
	/******************************************METODI GET E SET******************************************/
	/****************************************************************************************************/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
