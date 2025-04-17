package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Garde;


public interface IDaoGarde {

	int			inserer( Garde garde );

	void 		modifier( Garde garde );

	void 		supprimer( int idGarde );

	Garde 		retrouver( int idGarde );

	List<Garde> listerTout();
	
	int 		compterPourContrat( int idContrat );

	List<Garde> listerParContrat(int idContrat);

	List<Garde> listerParUtilisateur(int idUtilisateur);

	List<Garde> listerGardesParContrat(int idContrat);

	List<Garde> listerGardesParUtilisateurEtMois(int idUtilisateur, int mois, int annee);

	List<Garde> listerGardesParContratEtMois(int contratId, int mois, int annee);

}
