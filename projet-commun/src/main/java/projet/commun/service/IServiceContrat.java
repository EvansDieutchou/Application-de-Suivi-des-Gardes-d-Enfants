package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoContrat;
import projet.commun.exception.ExceptionValidation;

public interface IServiceContrat {
	
	int		inserer( DtoContrat dtoContrat ) throws ExceptionValidation;

	void	modifier( DtoContrat dtoContrat ) throws ExceptionValidation;

	void	supprimer( int idContrat) throws ExceptionValidation;

	DtoContrat	retrouver( int idContrat);

	List<DtoContrat>	listerTout();

	List<DtoContrat> listerParCompte(int idCompte);


}
