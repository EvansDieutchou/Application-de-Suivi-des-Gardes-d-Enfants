package projet.jsf.data;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "tarifs")
public class Tarifs {

    private Integer id;

    @Positive
    @NotNull(message = "Le tarif horaire doit être renseigné")
    private double tauxHoraire;

    @Positive
    @NotNull(message = "L'indemnité de taux horaires doit être renseigné")
    private double indemnitesEntretienTauxHoraire;

    @NotNull(message = "Le tarif horaire journalier  doit être renseigné")
    @DecimalMin(value = "2.65", message = "Le tarif horaire journalier doit être supérieur ou égal à 2.65 par jour")
    @Positive
    private double indemnitesEntretienMinJour;

    @NotNull(message = "L'indemnité de repas doit être renseigné")
    @DecimalMin(value = "1.00", message = "L'indemnité de repas doit être supérieur ou égal à 1.00")
    @DecimalMax(value = "5.00", message = "L'indemnité de repas doit être inférieur ou égal à 5.00")
    private double indemnitesRepas;

    public Tarifs() {
    }

    public Tarifs(Integer id,double tauxHoraire, double indemnitesEntretienTauxHoraire,
                  double indemnitesEntretienMinJour, double indemnitesRepas) {
    	this.id = id;
        this.tauxHoraire = tauxHoraire;
        this.indemnitesEntretienTauxHoraire = indemnitesEntretienTauxHoraire;
        this.indemnitesEntretienMinJour = indemnitesEntretienMinJour;
        this.indemnitesRepas = indemnitesRepas;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    // Liste des indemnités possibles entre 1 et 5
    public List<Double> getOptionsIndemnites() {
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
        Tarifs other = (Tarifs) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Tarifs [id=" + id + ", tauxHoraire=" + tauxHoraire +
               ", indemnitesEntretienTauxHoraire=" + indemnitesEntretienTauxHoraire +
               ", indemnitesEntretienMinJour=" + indemnitesEntretienMinJour +
               ", indemnitesRepas=" + indemnitesRepas + "]";
    }
}
