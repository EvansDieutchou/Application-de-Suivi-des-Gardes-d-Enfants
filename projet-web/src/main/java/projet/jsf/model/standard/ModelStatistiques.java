package projet.jsf.model.standard;

import projet.commun.service.IServiceGarde;
import projet.commun.dto.DtoStatistiqueMensuelle;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelStatistiques implements Serializable {

	@EJB
	private IServiceGarde serviceGarde;

	private List<DtoStatistiqueMensuelle> statistiques;
	private int contratId; // ID du contrat pour lequel vous voulez les statistiques

	// Méthode pour récupérer les statistiques
	public void init() {
//		if (contratId > 0) {
//			statistiques = serviceGarde.recupererStatistiquesMensuelles();
//		} else {
//			statistiques = null; // Ou une nouvelle liste vide si vous préférez
//		}
		statistiques = serviceGarde.recupererStatistiquesMensuelles();
	}

	// Getters et Setters
	public List<DtoStatistiqueMensuelle> getStatistiques() {
		return statistiques;
	}

	public void setContratId(int contratId) {
		this.contratId = contratId;
	}

	public int getContratId() { // Ajout du getter pour contratId
		return contratId;
	}
}
