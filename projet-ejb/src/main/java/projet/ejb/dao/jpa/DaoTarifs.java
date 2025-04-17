package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoTarifs;
import projet.ejb.data.Tarifs;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoTarifs implements IDaoTarifs {

	// Champs
	@PersistenceContext
	private EntityManager	em;


	// Actions
	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Tarifs> listerTout() {
		em.clear();
		var jpql = "SELECT t FROM Tarifs t";
		var query = em.createQuery( jpql, Tarifs.class );
		return query.getResultList();
	}


	@Override
	public int inserer(Tarifs tarifs) {
		em.persist(tarifs);
		em.flush();
		return tarifs.getId();
	}


	@Override
	public void modifier(Tarifs tarifs) {
	em.merge(tarifs);
	}


	@Override
	public void supprimer(int idTarifs) {
		em.remove(retrouver(idTarifs));
	}


	@Override
	public Tarifs retrouver(int idTarifs) {
		return em.find(Tarifs.class, idTarifs);
	}

}
