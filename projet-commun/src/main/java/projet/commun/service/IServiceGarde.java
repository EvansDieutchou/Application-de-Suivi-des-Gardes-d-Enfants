package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoGarde;
import projet.commun.dto.DtoStatistiqueMensuelle;
import projet.commun.exception.ExceptionValidation;


public interface IServiceGarde {
	
	int		inserer( DtoGarde dtoGarde ) throws ExceptionValidation;

	void	modifier( DtoGarde dtoGarde ) throws ExceptionValidation;

	void	supprimer( int idGarde) throws ExceptionValidation;

	DtoGarde	retrouver( int idGarde);

	List<DtoGarde>	listerTout();

	List<DtoGarde> listerParContrat(int idContrat);

	List<DtoGarde> retrouverParUtilisateur(int id);
	
	 double calculerMontantTotalAPayerParMois(int contratId, int mois, int annee);

	List<DtoGarde> retrouverParUtilisateurEtMois(int idUtilisateur, int mois, int annee);
	
	List<DtoStatistiqueMensuelle> recupererStatistiquesMensuelles();

}
