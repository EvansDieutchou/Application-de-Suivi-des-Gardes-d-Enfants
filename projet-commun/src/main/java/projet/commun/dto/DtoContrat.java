package projet.commun.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class DtoContrat implements Serializable {
	
	private int			id;
	
	private DtoParent	parent;
	
	private String		nomEnfant;
	
	private String		prenomEnfant;
	
	private LocalDate	dateNaissance;
	
	private LocalDate	dateDebut;
	
	private LocalDate	dateFin;
	
	private double tarifHoraire;

	private double indemniteTauxHoraire;

	private double indemniteMontantMinimum;

	private double indemniteRepas;
	
	
	// Constructeurs

	public DtoContrat(int id, DtoParent parent, String nomEnfant, String prenomEnfant, LocalDate dateNaissance,
			LocalDate dateDebut, LocalDate dateFin, double tarifHoraire, double indemniteTauxHoraire,
			double indemniteMontantMinimum, double indemniteRepas) {
		super();
		this.id = id;
		this.parent = parent;
		this.nomEnfant = nomEnfant;
		this.prenomEnfant = prenomEnfant;
		this.dateNaissance = dateNaissance;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.tarifHoraire = tarifHoraire;
		this.indemniteTauxHoraire = indemniteTauxHoraire;
		this.indemniteMontantMinimum = indemniteMontantMinimum;
		this.indemniteRepas = indemniteRepas;
	}

	public DtoContrat() {
	}

	
	// Getters & setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DtoParent getParent() {
		return parent;
	}

	public void setParent(DtoParent parent) {
		this.parent = parent;
	}

	public String getNomEnfant() {
		return nomEnfant;
	}

	public void setNomEnfant(String nomEnfant) {
		this.nomEnfant = nomEnfant;
	}

	public String getPrenomEnfant() {
		return prenomEnfant;
	}

	public void setPrenomEnfant(String prenomEnfant) {
		this.prenomEnfant = prenomEnfant;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public double getTarifHoraire() {
		return tarifHoraire;
	}

	public void setTarifHoraire(double tarifHoraire) {
		this.tarifHoraire = tarifHoraire;
	}

	public double getIndemniteTauxHoraire() {
		return indemniteTauxHoraire;
	}

	public void setIndemniteTauxHoraire(double indemniteTauxHoraire) {
		this.indemniteTauxHoraire = indemniteTauxHoraire;
	}

	public double getIndemniteMontantMinimum() {
		return indemniteMontantMinimum;
	}

	public void setIndemniteMontantMinimum(double indemniteMontantMinimum) {
		this.indemniteMontantMinimum = indemniteMontantMinimum;
	}

	public double getIndemniteRepas() {
		return indemniteRepas;
	}

	public void setIndemniteRepas(double indemniteRepas) {
		this.indemniteRepas = indemniteRepas;
	}

}
