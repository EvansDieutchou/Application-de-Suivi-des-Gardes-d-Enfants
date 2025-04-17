package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoTarifs;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceTarifs;
import projet.jsf.data.Tarifs;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelTarifs implements Serializable{

	// Champs

	private List<Tarifs>		liste;

	private Tarifs			courant;

	@EJB
	private IServiceTarifs	serviceTarifs;

	@Inject
	private IMapper				mapper;


	// Getters 

	public List<Tarifs> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoTarifs dto : serviceTarifs.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	public Tarifs getCourant() {
		if ( courant == null ) {
			courant = new Tarifs();
		}
		return courant;
	}


	// Initialisaitons

	public String actualiserCourant() {
		if ( courant != null ) {
			DtoTarifs dto = serviceTarifs.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "La tarifs demandée n'existe pas" );
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
				serviceTarifs.inserer( mapper.map(courant) );
			} else {
				serviceTarifs.modifier( mapper.map(courant) );
			}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}

	public String supprimer( Tarifs tarifs ) {
		try {
			serviceTarifs.supprimer( tarifs.getId() );
			liste.remove(tarifs);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}

}
