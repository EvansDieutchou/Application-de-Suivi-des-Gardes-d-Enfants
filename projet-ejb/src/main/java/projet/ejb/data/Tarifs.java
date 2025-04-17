package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tarifs")
public class Tarifs {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idtarif")
    private int id;

    @Column(name = "tauxhoraire")
    private double tauxHoraire;

    @Column(name = "indemnitesentretientauxhoraire")
    private double indemnitesEntretienTauxHoraire;

    @Column(name = "indemnitesentretienminjour")
    private double indemnitesEntretienMinJour;

    @Column(name = "indemnitesrepas")
    private double indemnitesRepas;

    public Tarifs() {
    }

    public Tarifs(double tauxHoraire, double indemnitesEntretienTauxHoraire,
                  double indemnitesEntretienMinJour, double indemnitesRepas) {
        this.tauxHoraire = tauxHoraire;
        this.indemnitesEntretienTauxHoraire = indemnitesEntretienTauxHoraire;
        this.indemnitesEntretienMinJour = indemnitesEntretienMinJour;
        this.indemnitesRepas = indemnitesRepas;
    }

    // Getters & Setters
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
