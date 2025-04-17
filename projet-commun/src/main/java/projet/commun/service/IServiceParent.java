package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoParent;
import projet.commun.exception.ExceptionValidation;

public interface IServiceParent {

	int		inserer( DtoParent dtoParent ) throws ExceptionValidation;

	void	modifier( DtoParent dtoParent ) throws ExceptionValidation;

	void	supprimer( int idParent) throws ExceptionValidation;

	DtoParent	retrouver( int idParent);

	List<DtoParent>	listerTout();
	
	DtoParent retrouverPourCompte(int idCompte);
	
	boolean estParentAssocieAuCompte(int compteId);
}
