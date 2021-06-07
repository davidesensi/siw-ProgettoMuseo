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

	//@Column(nullable=false)
	private String descrizione;
	//private String img;

	//Associazione ManyToOne tra Opera e Collezione, viene mappato per identicficare che  due riferiemnti 
	//in Artista e Opera si riferiscono alla stessa cosa
	@ManyToOne(cascade= {CascadeType.ALL})
	private Collezione collezione;

	//Associazione ManyToOne tra Opera e Artista, viene mappato per identicficare che  due riferiemnti 
	//in Artista e Opera si riferiscono alla stessa cosa
	@ManyToOne
	private Artista artista;

	//Costruttore no args
	public Opera() {

	}

	public Opera(String titolo, int anno, String descrizione, Collezione collezione, Artista artista) {
		this.titolo = titolo;
		this.anno = anno;
		this.descrizione = descrizione;	
		this.collezione = collezione;
		this.artista = artista;
	}

	/****************************************************************************************************/
	/******************************************METODI GET E SET******************************************/
	/****************************************************************************************************/

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Collezione getCollezione() {
		return collezione;
	}

	public void setCollezione(Collezione collezione) {
		this.collezione = collezione;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	/*public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	*/

}
