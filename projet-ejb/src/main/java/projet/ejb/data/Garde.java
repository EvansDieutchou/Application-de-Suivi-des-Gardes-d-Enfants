package projet.ejb.data;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "garde")
public class Garde {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idgarde")
	private int id;

	@ManyToOne
	@JoinColumn(name = "idcontrat")
	private Contrat contrat;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "heurearrivee")
	private LocalTime heureArrivee;

	@Column(name = "heuredepart")
	private LocalTime heureDepart;

	@Column(name = "nombrerepaspris")
	private int nombreRepasPris;


	public Garde() {
	}

	public Garde(int id,Contrat contrat, LocalDate date, LocalTime heureArrivee, LocalTime heureDepart,
			int nombreRepasPris) {
		this.id = id;
		this.contrat = contrat;
		this.date = date;
		this.heureArrivee = heureArrivee;
		this.heureDepart = heureDepart;
		this.nombreRepasPris = nombreRepasPris;
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(LocalTime heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public LocalTime getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(LocalTime heureDepart) {
		this.heureDepart = heureDepart;
	}

	public int getNombreRepasPris() {
		return nombreRepasPris;
	}

	public void setNombreRepasPris(int nombreRepasPris) {
		this.nombreRepasPris = nombreRepasPris;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Garde other = (Garde) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Garde [id=" + id + ", date=" + date + ", heureArrivee=" + heureArrivee +
				", heureDepart=" + heureDepart +  "]";
	}

	public double getMontantAPayer() {
	    // Récupération du contrat associé à la garde
	    Contrat contrat = this.contrat; // Assuming 'contrat' is a field in Garde

	    // Vérification si le contrat est null
	    if (contrat == null) {
	        throw new IllegalStateException("Le contrat associé à la garde est absent.");
	    }

	    // Définition des tarifs à partir du contrat
	    double tarifHoraireGarde = contrat.getTarifHoraire(); // Tarif horaire de garde
	    double tauxIndemniteEntretien = contrat.getIndemniteTauxHoraire(); // Taux horaire de l'indemnité d'entretien
	    double minimumJournalier = contrat.getIndemniteMontantMinimum(); // Minimum journalier
	    double tarifRepas = contrat.getIndemniteRepas(); // Tarif par repas

	    // Calculer le nombre d'heures travaillées
	    long heuresTravailles = java.time.Duration.between(heureArrivee, heureDepart).toHours();

	    // Montant total
	    double montantTotal = 0;

	    // Calcul du montant pour la garde
	    montantTotal += heuresTravailles * tarifHoraireGarde;

	    // Calcul de l'indemnité d'entretien
	    double indemniteEntretien = heuresTravailles * tauxIndemniteEntretien;
	    // Appliquer le minimum journalier
	    indemniteEntretien = Math.max(indemniteEntretien, minimumJournalier);
	    montantTotal += indemniteEntretien;

	    // Calcul de l'indemnité de repas
	    montantTotal += nombreRepasPris * tarifRepas;

	    return montantTotal;
	}



}
