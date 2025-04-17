package projet.jsf.data;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

public class Garde {

	private Integer id;

	@NotNull( message = "Le contrat doit être renseigné" )
	private Contrat contrat;

	@NotNull(message = "La date doit être renseignée")
	@PastOrPresent(message = "La date doit être dans le passé ou aujourd'hui")
	private LocalDate date;

	@NotNull(message = "L'heure d'arrivée doit être renseignée")
	private LocalTime heureArrivee;

	@NotNull(message = "L'heure de départ doit être renseignée")
	private LocalTime heureDepart;

	@NotNull( message = "Le contrat doit être renseigné" )
	@PositiveOrZero
	private Integer nombreRepasPris;

	public Garde() {
	}

	public Garde(Integer id, Contrat contrat, LocalDate date, LocalTime heureArrivee, LocalTime heureDepart,
			Integer nombreRepasPris) {
		this.id = id;
		this.contrat = contrat;
		this.date = date;
		this.heureArrivee = heureArrivee;
		this.heureDepart = heureDepart;
		this.nombreRepasPris = nombreRepasPris;
	}

	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getNombreRepasPris() {
		return nombreRepasPris;
	}

	public void setNombreRepasPris(Integer nombreRepasPris) {
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

	public double calculerMontantAPayer() {
		// Calculer le nombre d'heures de garde
		long heuresDeGarde = java.time.Duration.between(heureArrivee, heureDepart).toHours();

		// Calculer le montant de garde basé sur le tarif horaire du contrat
		double montantGarde = heuresDeGarde * contrat.getTarifHoraire();

		// Calculer l'indemnité d'entretien
		double montantEntretien = Math.max(contrat.getIndemniteMontantMinimum(), heuresDeGarde * contrat.getIndemniteTauxHoraire());

		// Calculer le montant des repas
		double montantRepas = nombreRepasPris * contrat.getIndemniteRepas();

		// Total à payer
		return montantGarde + montantEntretien + montantRepas;
	}


}
