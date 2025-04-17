package projet.jsf.data;



import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class Contrat {


    private Integer id;
	
    @NotNull( message = "Le parent doit être renseigné")
    private Parent parent;

    @NotBlank( message = "Le nom doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le pseuo : 25 car. maxi" )
    private String nomEnfant;

    @NotBlank( message = "Le prénom doit être renseigné")
	@Size(max=25, message = "Valeur trop longue pour le pseuo : 25 car. maxi" )
    private String prenomEnfant;

    @NotNull(message = "La date doit être renseignée")
    @Past(message = "La date doit être dans le passé")
    private LocalDate dateNaissance;

    @NotNull(message = "La date doit être renseignée")
    @PastOrPresent(message = "La date doit être dans le passé ou aujourd'hui")
    private LocalDate dateDebut;

    @NotNull(message = "La date doit être renseignée")
    private LocalDate dateFin;

    @NotNull(message = "Le tarif horaire doit être renseigné")
    private double tarifHoraire;

    @NotNull(message = "L'indemnité de taux horaires doit être renseigné")
//    @DecimalMin(value = "0.352", message = "L'indemnité de taux horaire doit être supérieur ou égal à 0.352 par heure")
//    @DecimalMax(value = "1000.00", message = "Le tarif horaire doit être inférieur ou égal à 1000.00")
    @Positive
    private Double indemniteTauxHoraire;

    @NotNull(message = "Le tarif horaire journalier  doit être renseigné")
    @DecimalMin(value = "2.65", message = "Le tarif horaire journalier doit être supérieur ou égal à 2.65 par jour")
    @Positive
    private Double indemniteMontantMinimum;

    @NotNull(message = "L'indemnité de repas doit être renseigné")
    @DecimalMin(value = "1.00", message = "L'indemnité de repas doit être supérieur ou égal à 1.00")
    @DecimalMax(value = "5.00", message = "L'indemnité de repas doit être inférieur ou égal à 5.00")
    private Double indemniteRepas;
    
    
    public Contrat() {
    }

    public Contrat(Integer id, Parent parent, String nomEnfant, String prenomEnfant, LocalDate dateNaissance,
    		LocalDate dateDebut, LocalDate dateFin, double tarifHoraire, Double indemniteTauxHoraire,
                   Double indemniteMontantMinimum, Double indemniteRepas) {
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

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
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

    public Double getIndemniteTauxHoraire() {
        return indemniteTauxHoraire;
    }

    public void setIndemniteTauxHoraire(Double indemniteTauxHoraire) {
        this.indemniteTauxHoraire = indemniteTauxHoraire;
    }

    public Double getIndemniteMontantMinimum() {
        return indemniteMontantMinimum;
    }

    public void setIndemniteMontantMinimum(Double indemniteMontantMinimum) {
        this.indemniteMontantMinimum = indemniteMontantMinimum;
    }

    public Double getIndemniteRepas() {
        return indemniteRepas;
    }

    public void setIndemniteRepas(Double indemniteRepas) {
        this.indemniteRepas = indemniteRepas;
    }
    
    public List<Double> getOptionsIndemnitesRepas() {
        return Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
    }

  
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contrat other = (Contrat) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Contrat [id=" + id + ", nomEnfant=" + nomEnfant + ", prenomEnfant=" + prenomEnfant +
               ", dateNaissance=" + dateNaissance + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin +
               ", tarifHoraire=" + tarifHoraire + "]";
    }
    
}
