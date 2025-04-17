package projet.commun.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DtoParent implements Serializable {

	private int id;
	
	private String nom;

	private String prenom;

	private String adresse;

	private String email;

	private String telephone;
	
	private DtoCompte compte;

	//Constructeurs

	public DtoParent() {
		
	}
	

	
	public DtoParent(int id, String nom, String prenom, String adresse, String email, String telephone,
			DtoCompte compte) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.compte = compte;
	}



	// Getters and  Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public DtoCompte getCompte() {
		return compte;
	}

	public void setCompte(DtoCompte compte) {
		this.compte = compte;
	}


}
