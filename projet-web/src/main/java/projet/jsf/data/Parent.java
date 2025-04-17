package projet.jsf.data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class Parent {

	
	private int id;

	@NotNull( message = "Le compte doit être renseigné")
	private Compte compte;

	@NotBlank( message = "Le nom doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le pseuo : 25 car. maxi" )
	private String nom;

	@NotBlank( message = "Le prénom doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le pseuo : 25 car. maxi" )
	private String prenom;

	@NotBlank( message = "L'adresse doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le pseuo : 25 car. maxi" )
	private String adresse;

	@NotBlank( message = "Le pseudo doit être renseigné")
	@Size(max=100, message = "Valeur trop longue pour le pseuo : 100 car. maxi" )
	@Email( message = "Adresse e-mail invalide" )
	private String email;

	@NotBlank( message = "Le pseudo doit être renseigné")
	@Size(max=13, message = "Valeur trop longue pour le pseuo : 13 car. maxi" )
	private String telephone;
	
	
	public Parent() {
	}

	public Parent(Integer id,Compte compte, String nom, String prenom, String adresse, String email, String telephone) {
		this.id = id;
		this.compte = compte;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
	}

	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
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
	
	
	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Parent other = (Parent) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Parent [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
}
