package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoGarde;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceGarde;
import projet.jsf.data.Garde;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelGarde implements Serializable{

	// Champs

	private List<Garde>		liste;

	private Garde			courant;

	@EJB
	private IServiceGarde	serviceGarde;

	@Inject
	private IMapper				mapper;

	@Inject
	private CompteActif compteActif;

	private int mois;
	private int annee;
	private double montantTotal;
	
	private boolean montantCalcule;

	// Getter et Setter
	public boolean isMontantCalcule() {
	    return montantCalcule;
	}

	public void setMontantCalcule(boolean montantCalcule) {
	    this.montantCalcule = montantCalcule;
	}

	// Autres champs et méthodes...

	public String afficherMontantTotalAPayerParMois() {
		montantTotal = serviceGarde.calculerMontantTotalAPayerParMois(courant.getContrat().getId(), mois, annee);
		return null; // Reste sur la même page
	}

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
		this.annee = annee;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	// Getters 

	//	public List<Garde> getListe() {
	//		if ( liste == null ) {
	//			liste = new ArrayList<>();
	//			for ( DtoGarde dto : serviceGarde.listerTout() ) {
	//				liste.add( mapper.map( dto ) );
	////				System.out.println(dto.getContrat().getNomEnfant());
	////				System.out.println(mapper.map(dto).getContrat().getNomEnfant());
	//			}
	//		}
	//		return liste;
	//	}

	//	public List<Garde> getListe() {
	//		if (liste == null) {
	//			liste = new ArrayList<>();
	//
	//			// Si l'utilisateur est administrateur, on liste tous les contrats
	//			if (compteActif.isAdmin()) {
	//				for (DtoGarde dto : serviceGarde.listerTout()) {
	//					liste.add(mapper.map(dto)); // Conversion DtoContrat -> Contrat
	//				}
	//			} else {
	//				// Sinon, on liste uniquement les contrats liés au compte de l'utilisateur connecté
	//				List<DtoGarde> dtoListe = serviceGarde.listerParContrat(courant.getContrat().getId());
	//
	//				for (DtoGarde dto : dtoListe) {
	//					liste.add(mapper.map(dto)); // Conversion DtoContrat -> Contrat
	//				}
	//			}
	//		}
	//		return liste;
	//
	//	}

	public List<Garde> getListe() {
		if (liste == null) {
			liste = new ArrayList<>();

			if (compteActif.isAdmin()) {
				for (DtoGarde dto : serviceGarde.listerTout()) {
					liste.add(mapper.map(dto)); 
				}
			} else {
				List<DtoGarde> dtoListe = serviceGarde.retrouverParUtilisateur(compteActif.getId());
				for (DtoGarde dto : dtoListe) {
					liste.add(mapper.map(dto));
				}
			}
		}
		return liste;
	}

	public double getMontantAPayer(Garde garde) {
		return garde.calculerMontantAPayer();
	}

	public Garde getCourant() {
		if ( courant == null ) {
			courant = new Garde();
		}
		return courant;
	}


	// Initialisaitons

	@PostConstruct
	public void init() {
		List<DtoGarde> dtoList = serviceGarde.retrouverParUtilisateur(compteActif.getId());

		if (!dtoList.isEmpty()) {
			courant = mapper.map(dtoList.get(0)); // Charger le premier garde
			if (courant.getId() == 0) {
				courant = new Garde();  // Réinitialiser si l'ID est invalide
			}
		} else {
			courant = new Garde(); // Si aucun garde n'est trouvé, initialiser un nouvel objet
		}
	}

	//	public String actualiserCourant() {
	//		if ( courant != null  ) {
	//			DtoGarde dto = serviceGarde.retrouver( courant.getId() ); 
	//			if ( dto == null ) {
	//				UtilJsf.messageError( "La garde demandée n'existe pas" );
	//				return "liste";
	//			} else {
	//				courant = mapper.map( dto );
	//			}
	//		}
	//		return null;
	//	}

	public String actualiserCourant() {
		if (courant != null) {
			Integer id = courant.getId();
			if (id == null || id == 0) {
				return null; 
			}

			DtoGarde dto = serviceGarde.retrouver(id); 
			if (dto == null) {
				UtilJsf.messageError("La garde demandée n'existe pas.");
				return "liste"; 
			} else {
				courant = mapper.map(dto); 
			}
		}
		return null; 
	}


	// Actions

	public String validerMiseAJour() {
		try {
			if ( courant.getId() == null) {
				serviceGarde.inserer( mapper.map(courant) );
			} else {
				serviceGarde.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

	public String supprimer( Garde garde ) {
		try {
			serviceGarde.supprimer( garde.getId() );
			liste.remove(garde);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}

	public void calculerMontants() {
		for (Garde garde : liste) {
			double montantAPayer = garde.calculerMontantAPayer();
			System.out.println("Montant à payer pour la garde du " + garde.getDate() + ": " + montantAPayer + " €");
		}
	}

	public double calculerTotalMontants() {
		double montantTotal = 0;
		for (Garde garde : liste) {
			double montantAPayer = garde.calculerMontantAPayer();
			montantTotal += montantAPayer;
		}
		return montantTotal;
	}


	public String obtenirMontantTotalAPayerParMois() {
	    // Vérifiez que les valeurs de mois et d'année sont valides
	    if (mois < 1 || mois > 12 || annee <= 0) {
	        UtilJsf.messageError("Veuillez saisir un mois et une année valides.");
	        return null; // Reste sur la même page
	    }

	    // Chargez les gardes selon l'utilisateur actif
	    List<DtoGarde> gardes = serviceGarde.retrouverParUtilisateurEtMois(compteActif.getId(),mois,annee);

	    // Initialisez le montant total
	    montantTotal = 0;
	    
	    liste = new ArrayList<>();

	    if (!gardes.isEmpty()) {
	        // Pour chaque garde, calculez le montant à payer pour le mois donné
	        for (DtoGarde dtoGarde  : gardes) {
	        	Garde garde = mapper.map(dtoGarde);
	        	liste.add(garde);
	        	// Ajoutez le montant de cette garde au total
	            montantTotal += garde.calculerMontantAPayer();
	        }
	    }
	    montantCalcule = true;

	    return null; // Reste sur la même page
	}

}
