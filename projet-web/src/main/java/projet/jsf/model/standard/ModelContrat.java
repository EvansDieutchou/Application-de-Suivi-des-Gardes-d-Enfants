package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoContrat;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceContrat;
import projet.jsf.data.Contrat;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelContrat implements Serializable{

	// Champs

	private List<Contrat>		liste;

	private Contrat			courant;

	@EJB
	private IServiceContrat	serviceContrat;

	@Inject
	private IMapper				mapper;
	
	@Inject
	 private CompteActif compteActif;


	// Getters 

//	public List<Contrat> getListe() {
//		if ( liste == null ) {
//			liste = new ArrayList<>();
//			for ( DtoContrat dto : serviceContrat.listerTout() ) {
//				liste.add( mapper.map( dto ) );
//			}
//		}
//		return liste;
//	}
	
	  public List<Contrat> getListe() {
		  
		   if (liste == null) {
		        liste = new ArrayList<>();
		        
		        // Si l'utilisateur est administrateur, on liste tous les contrats
		        if (compteActif.isAdmin()) {
		            for (DtoContrat dto : serviceContrat.listerTout()) {
		                liste.add(mapper.map(dto)); // Conversion DtoContrat -> Contrat
		            }
		        } else {
		            // Sinon, on liste uniquement les contrats liés au compte de l'utilisateur connecté
		            List<DtoContrat> dtoListe = serviceContrat.listerParCompte(compteActif.getId());
		            for (DtoContrat dto : dtoListe) {
		                liste.add(mapper.map(dto)); // Conversion DtoContrat -> Contrat
		            }
		        }
		    }
		    return liste;
		    
	    }

	public Contrat getCourant() {
		if ( courant == null ) {
			courant = new Contrat();
		}
		return courant;
	}


	// Initialisaitons

	public String actualiserCourant() {
		if ( courant != null ) {
			DtoContrat dto = serviceContrat.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La contrat demandée n'existe pas" );
				return "liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}


	// Actions

	public String validerMiseAJour() {
		try {
			if ( courant.getId() == null) {
				serviceContrat.inserer( mapper.map(courant) );
			} else {
				serviceContrat.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

	public String supprimer( Contrat contrat ) {
		try {
			serviceContrat.supprimer( contrat.getId() );
			liste.remove(contrat);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}

}
