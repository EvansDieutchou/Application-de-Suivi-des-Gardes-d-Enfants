package projet.ejb.dao.jpa;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import projet.ejb.dao.IDaoGarde;
import projet.ejb.data.Garde;

@Stateless
@Local
@TransactionAttribute( MANDATORY )
public class DaoGarde implements IDaoGarde {

	// Champs

	@PersistenceContext
	private EntityManager	em;


	// Actions
	@Override
	public int inserer(Garde garde) {
		em.persist(garde);
		em.flush();
		return garde.getId();
	}

	@Override
	public void modifier(Garde garde) {
		em.merge(garde);
	}

	@Override
	public void supprimer(int idGarde) {
		em.remove(retrouver(idGarde));
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public Garde retrouver(int idGarde) {
		return em.find(Garde.class, idGarde);
	}

	@Override
	@TransactionAttribute( NOT_SUPPORTED )
	public List<Garde> listerTout() {
		em.clear();
		var jpql = "SELECT g FROM Garde g ORDER BY g.date";
		var query = em.createQuery( jpql, Garde.class );
		return query.getResultList();
	}

	@Override @TransactionAttribute( NOT_SUPPORTED )
	public List<Garde> listerParContrat(int idContrat) {
		var jpql = "SELECT g FROM Garde g WHERE  g.contrat.parent.compte.id = :idContrat";
		var query = em.createQuery(jpql, Garde.class);
		query.setParameter("idContrat", idContrat);

		return query.getResultList();
	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public List<Garde> listerParUtilisateur(int idUtilisateur) {
		String jpql = "SELECT g FROM Garde g WHERE g.contrat.parent.compte.id = :idUtilisateur";
		var query = em.createQuery(jpql, Garde.class);
		query.setParameter("idUtilisateur", idUtilisateur);
		return query.getResultList();
	}

	@Override @TransactionAttribute( NOT_SUPPORTED )
	public int compterPourContrat(int idContrat) {
		var jpql = "SELECT COUNT(g) FROM Garde g WHERE g.contrat.id = :idContrat";
		var query = em.createQuery( jpql, Long.class );
		query.setParameter( "idContrat", idContrat );
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Garde> listerGardesParContrat(int idContrat) {
		var query = em.createQuery("SELECT g FROM Garde g WHERE g.contrat.id = :idContrat", Garde.class);
		query.setParameter("idContrat", idContrat);
		return query.getResultList();
	}
	
	@Override
	public List<Garde> listerGardesParContratEtMois(int contratId, int mois, int annee) {
	    // Implémentez ici la requête SQL ou JPQL pour filtrer par contrat, mois et année directement dans la base de données
	    return em.createQuery(
	            "SELECT g FROM Garde g WHERE g.contrat.id = :contratId AND MONTH(g.date) = :mois AND YEAR(g.date) = :annee", Garde.class)
	            .setParameter("contratId", contratId)
	            .setParameter("mois", mois)
	            .setParameter("annee", annee)
	            .getResultList();
	}
	
	@Override
	public List<Garde> listerGardesParUtilisateurEtMois(int idUtilisateur, int mois, int annee) {
	    // Implémentez ici la requête SQL ou JPQL pour filtrer par contrat, mois et année directement dans la base de données
	    return em.createQuery(
	            "SELECT g FROM Garde g WHERE g.contrat.parent.compte.id = :idUtilisateur AND MONTH(g.date) = :mois AND YEAR(g.date) = :annee", Garde.class)
	            .setParameter("idUtilisateur", idUtilisateur)
	            .setParameter("mois", mois)
	            .setParameter("annee", annee)
	            .getResultList();
	}

}
