package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoContrat;
import projet.ejb.data.Contrat;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoContrat implements IDaoContrat {

	// Champs

	@PersistenceContext
	private EntityManager	em;


	// Actions
	@Override
	public int inserer(Contrat contrat) {
		em.persist(contrat);
		em.flush();
		return contrat.getId();
	}

	@Override
	public void modifier(Contrat contrat) {
		em.merge(contrat);
	}

	@Override
	public void supprimer(int idContrat) {
		em.remove(retrouver(idContrat));
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Contrat retrouver(int idContrat) {
		return em.find(Contrat.class, idContrat);
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Contrat> listerTout() {
		em.clear();
		var jpql = "SELECT c FROM Contrat c ORDER BY c.parent.nom,c.nomEnfant";
		var query = em.createQuery( jpql, Contrat.class );
		return query.getResultList();
	}
	
	@TransactionAttribute(NOT_SUPPORTED)
	public List<Contrat> listerParCompte(int idCompte) {
	    var jpql = "SELECT c FROM Contrat c WHERE c.parent.compte.id = :idCompte  ORDER BY c.nomEnfant,c.prenomEnfant";
	    var query = em.createQuery(jpql, Contrat.class);
	    query.setParameter("idCompte", idCompte);
	    
	    return query.getResultList();  // Retourne les contrats liés au parent de ce compte
	}

	@Override
	public int compterPourParent(int idParent) {
		var jpql = "SELECT COUNT(c) FROM Contrat c WHERE c.parent.id = :idParent";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idParent", idParent );
		return query.getSingleResult().intValue();
	}

}
