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
	
	
	@ManyToOne
	private Curatore curatore;
	
	@OneToMany
	private List<Opera> opere;
	
	
	//get and set
	
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
