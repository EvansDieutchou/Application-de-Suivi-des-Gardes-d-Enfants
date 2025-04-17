package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contrat")
public class Contrat {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idcontrat")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idparent")
    private Parent parent;

    @Column(name = "nomenfant")
    private String nomEnfant;

    @Column(name = "prenomenfant")
    private String prenomEnfant;

    @Column(name = "datenaissance")
    private LocalDate dateNaissance;

    @Column(name = "datedebut")
    private LocalDate dateDebut;

    @Column(name = "datefin")
    private LocalDate dateFin;

    @Column(name = "tarifhoraire")
    private double tarifHoraire;

    @Column(name = "indemnitetauxhoraire")
    private double indemniteTauxHoraire;

    @Column(name = "indemnitemontantminimum")
    private double indemniteMontantMinimum;

    @Column(name = "indemniterepas")
    private double indemniteRepas;
    

    public Contrat() {
    }

    public Contrat(int id,Parent parent, String nomEnfant, String prenomEnfant, LocalDate dateNaissance,
    		LocalDate dateDebut, LocalDate dateFin, double tarifHoraire, double indemniteTauxHoraire,
                   double indemniteMontantMinimum, double indemniteRepas) {
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
