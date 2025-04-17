package projet.commun.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@SuppressWarnings("serial")
public class DtoGarde implements Serializable {

    private int id;
    
    private int mois;

    private DtoContrat contrat;

    private LocalDate date;

    private LocalTime heureArrivee;
    
    private LocalTime heureDepart;

    private Integer nombreRepasPris;
    
    
  //Constructeurs
    

	public DtoGarde() {
		
	}


	public DtoGarde(int id, DtoContrat contrat, LocalDate date, LocalTime heureArrivee, LocalTime heureDepart,
			Integer nombreRepasPris) {
		super();
		this.id = id;
		this.contrat = contrat;
		this.date = date;
		this.heureArrivee = heureArrivee;
		this.heureDepart = heureDepart;
		this.nombreRepasPris = nombreRepasPris;
	}

	//Getters and Setters

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	 public int getMois() {
	        return mois;
	    }

	    public void setMois(int mois) {
	        this.mois = mois;
	    }

	public DtoContrat getContrat() {
		return contrat;
	}


	public void setContrat(DtoContrat contrat) {
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
