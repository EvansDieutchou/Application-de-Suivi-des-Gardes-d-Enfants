package projet.ejb.dao;

import java.util.List;

import projet.ejb.data.Tarifs;


public interface IDaoTarifs {

	int			inserer( Tarifs tarifs );

	void 		modifier( Tarifs tarifs );

	void 		supprimer( int idTarifs );

	Tarifs 		retrouver( int idTarifs );

	List<Tarifs> listerTout();
}
