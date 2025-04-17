package projet.jsf.data;

public class StatistiqueMensuelle {

	private int mois;
	private double remunerationTotale;
	private int nombreJoursTravailles;
	private double remunerationMoyenne;

	// Getters et Setters

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
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
}
