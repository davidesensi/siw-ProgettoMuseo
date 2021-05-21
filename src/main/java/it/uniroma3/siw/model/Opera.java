package it.uniroma3.siw.model;

import javax.persistence.*;

@Entity
public class Opera {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;

	@Column(nullable=false)
	private String titolo;
	
	@Column(nullable=false)
	private int anno;
	
	@Column(nullable=false)
	private String description;

	@ManyToOne
	private Collezione collezione;
	
	@ManyToOne
	private Artista artista;
	
	//get and set
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
