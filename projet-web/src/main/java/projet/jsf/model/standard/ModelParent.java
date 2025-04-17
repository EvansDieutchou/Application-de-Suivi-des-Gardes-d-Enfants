package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoParent;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceParent;
import projet.jsf.data.Parent;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelParent implements Serializable{

	// Champs

	private List<Parent>		liste;

	private Parent			courant;

	@EJB
	private IServiceParent	serviceParent;

	@Inject
	private IMapper				mapper;


	@Inject
	private CompteActif compteActif; 

	// Getters 

	//	public List<Parent> getListe() {
	//		if ( liste == null ) {
	//			liste = new ArrayList<>();
	//			for ( DtoParent dto : serviceParent.listerTout() ) {
	//				liste.add( mapper.map( dto ) );
	//			}
	//		}
	//		return liste;
	//	}
	public List<Parent> getListe() {
		if (liste == null) {
			liste = new ArrayList<>();

			// Si l'utilisateur est administrateur, on liste tous les parents
			if (compteActif.isAdmin()) {
				for (DtoParent dto : serviceParent.listerTout()) {
					liste.add(mapper.map(dto));
				}
			} else {

				// Sinon, on récupère les informations du parent connecté via son compte
				DtoParent parent = serviceParent.retrouverPourCompte(compteActif.getId());  // Utilise le compte actif pour retrouver le parent

				if (parent != null ) {
					liste.add(mapper.map(parent));  // Ajoute uniquement les infos du parent connecté
				} else {
					// Si aucun parent n'est trouvé pour le compte, afficher un message d'erreur
					System.out.println("Aucun parent trouvé pour le compte ID: " + compteActif.getId());
				}
			}
		}
		return liste;
	}

	public Parent getCourant() {
		if ( courant == null ) {
			courant = new Parent();
		}
		return courant;
	}


	// Initialisaitons

	public String actualiserCourant() {
		if ( courant != null ) {
			DtoParent dto = serviceParent.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La parent demandée n'existe pas" );
				return "liste";
			} else {
				courant = mapper.map( dto );
				if (courant.getCompte() != null) {
					System.out.println("Compte associé : " + courant.getCompte().getId());
				}
			}
		}
		return null;
	}


	// Actions

	public String validerMiseAJour() {
		try {
			// Vérifie si un compte est déjà associé à un autre parent
			if (courant.getCompte() != null) {
				// Récupérer le compte courant
				int compteId = courant.getCompte().getId();
				// Si on modifie un parent existant
				if (courant.getId() != null) {
					// Vérifier si le compte est associé à un autre parent que celui courant
					boolean estAssocie = serviceParent.estParentAssocieAuCompte(compteId);
					if (estAssocie) {
						// S'il est associé mais pas au parent courant, afficher un message d'erreur
						if (serviceParent.retrouverPourCompte(compteId).getId() != courant.getId()) {
							UtilJsf.messageError("Ce compte est déjà associé à un autre parent.");
							return null;
						}
					}
				} else {
					// Si c'est un nouvel ajout, vérifiez si le compte est associé à un autre parent
					if (serviceParent.estParentAssocieAuCompte(compteId)) {
						UtilJsf.messageError("Ce compte est déjà associé à un autre parent.");
						return null;
					}
				}
			}

			if (courant.getId() == null) {
				serviceParent.inserer(mapper.map(courant));
			} else {
				serviceParent.modifier(mapper.map(courant));
			}
			UtilJsf.messageInfo("Mise à jour effectuée avec succès.");
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}


	public String supprimer( Parent parent ) {
		try {
			serviceParent.supprimer( parent.getId() );
			liste.remove(parent);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}

}
