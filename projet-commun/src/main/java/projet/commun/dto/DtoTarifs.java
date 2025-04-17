package projet.commun.dto;

import java.io.Serializable;



@SuppressWarnings("serial")
public class DtoTarifs implements Serializable{

	private int id;

	private double tauxHoraire;

	private double indemnitesEntretienTauxHoraire;

	private double indemnitesEntretienMinJour;

	private double indemnitesRepas;
	
	//Constructeurs

	public DtoTarifs() {

	}

	public DtoTarifs(int id, double tauxHoraire, double indemnitesEntretienTauxHoraire,
			double indemnitesEntretienMinJour, double indemnitesRepas) {
		super();
		this.id = id;
		this.tauxHoraire = tauxHoraire;
		this.indemnitesEntretienTauxHoraire = indemnitesEntretienTauxHoraire;
		this.indemnitesEntretienMinJour = indemnitesEntretienMinJour;
		this.indemnitesRepas = indemnitesRepas;
	}

	//Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTauxHoraire() {
		return tauxHoraire;
	}

	public void setTauxHoraire(double tauxHoraire) {
		this.tauxHoraire = tauxHoraire;
	}

	public double getIndemnitesEntretienTauxHoraire() {
		return indemnitesEntretienTauxHoraire;
	}

	public void setIndemnitesEntretienTauxHoraire(double indemnitesEntretienTauxHoraire) {
		this.indemnitesEntretienTauxHoraire = indemnitesEntretienTauxHoraire;
	}

	public double getIndemnitesEntretienMinJour() {
		return indemnitesEntretienMinJour;
	}

	public void setIndemnitesEntretienMinJour(double indemnitesEntretienMinJour) {
		this.indemnitesEntretienMinJour = indemnitesEntretienMinJour;
	}

	public double getIndemnitesRepas() {
		return indemnitesRepas;
	}

	public void setIndemnitesRepas(double indemnitesRepas) {
		this.indemnitesRepas = indemnitesRepas;
	}
	
	
	
}
