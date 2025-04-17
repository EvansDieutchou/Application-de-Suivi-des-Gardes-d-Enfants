package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoGarde;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceContrat;
import projet.ejb.dao.IDaoContrat;
import projet.ejb.dao.IDaoGarde;
import projet.ejb.data.Contrat;
import projet.ejb.data.Garde;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceContrat implements IServiceContrat {
	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoContrat daoContrat;
	@Inject
	private IDaoGarde daoGarde;


	@Override
	public int inserer(DtoContrat dtoContrat) throws ExceptionValidation {
		verifierValiditeDonnees(dtoContrat);
		int id = daoContrat.inserer(mapper.map(dtoContrat));
		return id;
	}

	@Override
	public void modifier(DtoContrat dtoContrat) throws ExceptionValidation {
		verifierValiditeDonnees(dtoContrat);
		daoContrat.modifier(mapper.map(dtoContrat));

	}

	@Override
	public void supprimer(int idContrat) throws ExceptionValidation {

		daoContrat.supprimer(idContrat);


	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public DtoContrat retrouver(int idContrat) {
		return mapper.map(daoContrat.retrouver(idContrat));
	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public List<DtoContrat> listerTout() {
		List<DtoContrat> liste = new ArrayList<>();
		for (Contrat contrat : daoContrat.listerTout()) {
			liste.add(mapper.map(contrat));
		}
		return liste;
	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public List<DtoContrat> listerParCompte(int idCompte) {
		List<Contrat> contrats = daoContrat.listerParCompte(idCompte);
		List<DtoContrat> dtoResult = new ArrayList<>();

		for (Contrat contrat : contrats) {
			dtoResult.add(mapper.map(contrat));  // Mapper les contrats vers DTO
		}

		return dtoResult;
	}

	public List<DtoGarde> listerParContrat(int idContrat) {
	    List<Garde> gardes = daoGarde.listerParContrat(idContrat); // Doit être List<Garde>
	    List<DtoGarde> dtoResult = new ArrayList<>();

	    for (Garde garde : gardes) {
	        dtoResult.add(mapper.map(garde)); // Mapper Garde vers DtoGarde
	    }
	    return dtoResult;
	}
	
	// Actions

	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoContrat dtoContrat) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		// Vérification du nom de l'enfant
		if (dtoContrat.getNomEnfant() == null || dtoContrat.getNomEnfant().isEmpty()) {
			message.append("\nLe nom de l'enfant est absent.");
		} else if (dtoContrat.getNomEnfant().length() < 2) {
			message.append("\nLe nom de l'enfant est trop court.");
		} else if (dtoContrat.getNomEnfant().length() > 50) {
			message.append("\nLe nom de l'enfant est trop long.");
		}

		// Vérification du prénom de l'enfant
		if (dtoContrat.getPrenomEnfant() == null || dtoContrat.getPrenomEnfant().isEmpty()) {
			message.append("\nLe prénom de l'enfant est absent.");
		} else if (dtoContrat.getPrenomEnfant().length() < 2) {
			message.append("\nLe prénom de l'enfant est trop court.");
		} else if (dtoContrat.getPrenomEnfant().length() > 50) {
			message.append("\nLe prénom de l'enfant est trop long.");
		}

		// Vérification des dates
		if (dtoContrat.getDateNaissance() == null) {
			message.append("\nLa date de naissance est absente.");
		}
		if (dtoContrat.getDateDebut() == null) {
			message.append("\nLa date de début est absente.");
		}
		if (dtoContrat.getDateFin() == null) {
			message.append("\nLa date de fin est absente.");
		} else if (dtoContrat.getDateDebut() != null && dtoContrat.getDateFin().isBefore(dtoContrat.getDateDebut())) {
			message.append("\nLa date de fin ne peut pas être antérieure à la date de début.");
		}

		// Vérification du tarif horaire
		if (dtoContrat.getTarifHoraire() <= 0) {
			message.append("\nLe tarif horaire doit être supérieur à zéro.");
		}

		// Vérification des indemnités
		if (dtoContrat.getIndemniteTauxHoraire() < 0) {
			message.append("\nL'indemnité taux horaire ne peut pas être négative.");
		}
		if (dtoContrat.getIndemniteMontantMinimum() < 0) {
			message.append("\nL'indemnité montant minimum ne peut pas être négative.");
		}
		if (dtoContrat.getIndemniteRepas() < 0) {
			message.append("\nL'indemnité repas ne peut pas être négative.");
		}

		// Si des erreurs ont été trouvées, lancer une exception avec les messages d'erreurs
		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}



}
