package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import projet.commun.dto.DtoGarde;
import projet.commun.dto.DtoStatistiqueMensuelle;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceGarde;
import projet.ejb.dao.IDaoGarde;
import projet.ejb.data.Garde;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceGarde implements IServiceGarde {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoGarde daoGarde;

	@PersistenceContext
	private EntityManager em;

	@Override
	public int inserer(DtoGarde dtogarde) throws ExceptionValidation {
		verifierValiditeDonnees(dtogarde);
		int id = daoGarde.inserer(mapper.map(dtogarde));
		return id;
	}

	@Override
	public void modifier(DtoGarde dtoGarde) throws ExceptionValidation {
		verifierValiditeDonnees(dtoGarde);
		daoGarde.modifier(mapper.map(dtoGarde));
	}

	@Override
	public void supprimer(int idGarde) throws ExceptionValidation {
		daoGarde.supprimer(idGarde);
	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public DtoGarde retrouver(int idGarde) {
		return mapper.map(daoGarde.retrouver(idGarde));
	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public List<DtoGarde> listerTout() {
		List<DtoGarde> liste = new ArrayList<>();
		for (Garde garde : daoGarde.listerTout()) {
			liste.add( mapper.map(garde) );
		}
		return liste;
	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public List<DtoGarde> listerParContrat(int idContrat) {
		List<Garde> gardes = daoGarde.listerParContrat(idContrat);
		List<DtoGarde> dtoResult = new ArrayList<>();
		for (Garde garde : gardes) {
			dtoResult.add(mapper.map(garde)); // Mapper Garde vers DtoGarde
		}
		return dtoResult;
	}

	@Override 
	public List<DtoGarde> retrouverParUtilisateur(int idUtilisateur) {
		List<Garde> gardes = daoGarde.listerParUtilisateur(idUtilisateur);
		List<DtoGarde> dtoGardes = new ArrayList<>();
		for (Garde garde : gardes) {
			dtoGardes.add(mapper.map(garde));
		}
		return dtoGardes;
	}

	@Override
	public List<DtoGarde> retrouverParUtilisateurEtMois(int idUtilisateur, int mois, int annee) {
		List<Garde> gardes = daoGarde.listerGardesParUtilisateurEtMois(idUtilisateur, mois, annee);
		List<DtoGarde> dtoGardes = new ArrayList<>();
		for (Garde garde : gardes) {
			dtoGardes.add(mapper.map(garde));
		}
		return dtoGardes;
	}


	@Override
	public double calculerMontantTotalAPayerParMois(int idUtilisateur, int mois, int annee) {
		List<Garde> gardes = daoGarde.listerGardesParUtilisateurEtMois(idUtilisateur, mois, annee);
		double montantTotal = 0;

		for (Garde garde : gardes) {
			montantTotal += garde.getMontantAPayer();
		}

		return montantTotal;
	}

	//	@Override
	//	public List<DtoStatistiqueMensuelle> recupererStatistiquesMensuelles() {
	//		List<DtoStatistiqueMensuelle> statistiques = new ArrayList<>();
	//        LocalDate dateFin = LocalDate.now();
	//
	//        for (int i = 0; i < 12; i++) {
	//            LocalDate dateDebut = dateFin.minusMonths(i).withDayOfMonth(1);
	//            LocalDate dateFinMois = dateFin.minusMonths(i).withDayOfMonth(dateFin.minusMonths(i).lengthOfMonth());
	//
	//            // Requête pour récupérer les statistiques de tous les contrats
	//            TypedQuery<Object[]> query = em.createQuery(
	//                "SELECT g.contrat.id, SUM(g.montant), COUNT(g.id) " +
	//                "FROM Garde g WHERE g.date BETWEEN :dateDebut AND :dateFinMois " +
	//                "GROUP BY g.contrat.id", Object[].class);
	//            query.setParameter("dateDebut", dateDebut);
	//            query.setParameter("dateFinMois", dateFinMois);
	//
	//            List<Object[]> results = query.getResultList();
	//
	//            for (Object[] result : results) {
	//                int contratId = (int) result[0];
	//                double remunerationTotale = result[1] != null ? (double) result[1] : 0;
	//                int nombreJoursTravailles = result[2] != null ? (int) result[2] : 0;
	//
	//                DtoStatistiqueMensuelle statistique = new DtoStatistiqueMensuelle();
	//                statistique.setMois(dateDebut.getMonthValue()); // 1-12
	//                statistique.setRemunerationTotale(remunerationTotale);
	//                statistique.setNombreJoursTravailles(nombreJoursTravailles);
	//                statistique.setContratId(contratId); // Vous pouvez ajouter cette méthode dans le DTO si besoin
	//
	//                statistiques.add(statistique);
	//            }
	//        }
	//        return statistiques;
	//	}

	@Override
	public List<DtoStatistiqueMensuelle> recupererStatistiquesMensuelles() {
	    List<DtoStatistiqueMensuelle> statistiques = new ArrayList<>();
	    LocalDate dateActuelle = LocalDate.now(); // Date actuelle (mois et année courants)

	    // Boucle pour les 12 derniers mois
	    for (int i = 0; i < 12; i++) {
	        LocalDate dateMois = dateActuelle.minusMonths(i);
	        int mois = dateMois.getMonthValue(); // Mois (1 à 12)
	        int annee = dateMois.getYear(); // Année correspondante

	        // Requête pour récupérer les gardes du mois précis et de l'année correspondante
	        TypedQuery<Garde> query = em.createQuery(
	            "SELECT g FROM Garde g WHERE MONTH(g.date) = :mois AND YEAR(g.date) = :annee", Garde.class);
	        query.setParameter("mois", mois);
	        query.setParameter("annee", annee);

	        List<Garde> gardes = query.getResultList();

	        // Créer une statistique pour le mois courant
	        DtoStatistiqueMensuelle statistique = new DtoStatistiqueMensuelle();
	        statistique.setMois(mois); // Stocke le mois numérique
	        statistique.setAnnee(annee); // Stocke l'année
	        statistique.setContratId(-1); // Indication que c'est pour plusieurs contrats

	        // Calculer les totaux pour ce mois
	        double remunerationTotale = 0;
	        int nombreJoursTravailles = 0;

	        for (Garde garde : gardes) {
	            remunerationTotale += garde.getMontantAPayer();
	            nombreJoursTravailles++;
	        }

	        statistique.setRemunerationTotale(remunerationTotale);
	        statistique.setNombreJoursTravailles(nombreJoursTravailles);

	        // N'ajouter la statistique que si des gardes ont été trouvées pour ce mois
	        if (nombreJoursTravailles > 0) {
	            statistiques.add(statistique);
	        }
	    }

	    return statistiques;
	}



	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoGarde dtoGarde) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		if (dtoGarde.getContrat() == null) {
			message.append("\nLe Contrat est absent.");
		}

		//		else if (dtoGarde.getLibelle().length() < 3) {
		//			message.append("\nLe libellé est trop court.");
		//		} else if (dtoGarde.getLibelle().length() > 25) {
		//			message.append("\nLe libellé est trop long.");
		//		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}


}
