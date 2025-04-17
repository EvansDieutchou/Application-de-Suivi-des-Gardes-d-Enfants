package projet.commun.dto;

import java.io.Serializable;

public class DtoStatistiqueMensuelle implements Serializable {
    private static final long serialVersionUID = 1L;

    private int mois;
    private int annee;
    private double remunerationTotale;
    private int nombreJoursTravailles;
    private int contratId;
    private double remunerationMoyenne;

    // Getters et Setters

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }
    
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee= annee;
    }

    public double getRemunerationTotale() {
        return remunerationTotale;
    }

    public void setRemunerationTotale(double remunerationTotale) {
        this.remunerationTotale = remunerationTotale;
    }

    public int getNombreJoursTravailles() {
        return nombreJoursTravailles;
    }

    public void setNombreJoursTravailles(int nombreJoursTravailles) {
        this.nombreJoursTravailles = nombreJoursTravailles;
    }

    public double getRemunerationMoyenne() {
        return remunerationMoyenne;
    }

    public void setRemunerationMoyenne(double remunerationMoyenne) {
        this.remunerationMoyenne = remunerationMoyenne;
    }
    
    public double getRemunerationMoyenneParJour() {
        return nombreJoursTravailles > 0 ? remunerationTotale / nombreJoursTravailles : 0;
    }
    
    public int getContratId() {
		return contratId;
	}

	public void setContratId(int contratId) {
		this.contratId = contratId;
	}

	public String getMoisNom() {
        String[] moisNoms = {
            "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        };
        return moisNoms[mois - 1]; // -1 pour accéder au tableau
    }
    
}
