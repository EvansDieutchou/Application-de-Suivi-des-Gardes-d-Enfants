package projet.ejb.service.standard;

import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import projet.commun.dto.DtoParent;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceParent;
import projet.ejb.dao.IDaoContrat;
import projet.ejb.dao.IDaoParent;
import projet.ejb.data.Parent;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceParent implements IServiceParent {

	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoParent daoParent;
	@Inject
	private IDaoContrat daoContrat;

	@Override
	public int inserer(DtoParent dtoParent) throws ExceptionValidation {
		verifierValiditeDonnees(dtoParent);
		int id = daoParent.inserer(mapper.map(dtoParent));
		return id;
	}

	@Override
	public void modifier(DtoParent dtoParent) throws ExceptionValidation {
		verifierValiditeDonnees(dtoParent);
		daoParent.modifier(mapper.map(dtoParent));

	}

	@Override
	public void supprimer(int idParent) throws ExceptionValidation {
		if (daoContrat.compterPourParent(idParent) != 0) {
			throw new ExceptionValidation("Ce parent est rattaché à un contrat");
		}
		daoParent.supprimer(idParent);

	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public DtoParent retrouver(int idParent) {	
		return mapper.map(daoParent.retrouver(idParent));

	}
	@Override
	public DtoParent retrouverPourCompte(int idCompte) {
		return mapper.map(daoParent.retrouverPourCompte(idCompte));
	}

	@Override @TransactionAttribute(NOT_SUPPORTED)
	public List<DtoParent> listerTout() {
		List<DtoParent> liste = new ArrayList<>();
		for (Parent parent : daoParent.listerTout()) {
			liste.add(mapper.map(parent));

		}
		return liste;
	}

	@Override
	@TransactionAttribute(NOT_SUPPORTED)
	public boolean estParentAssocieAuCompte(int compteId) {
		return daoParent.retrouverPourCompte(compteId) != null;
	}



	// Méthodes auxiliaires

	private void verifierValiditeDonnees(DtoParent dtoParent) throws ExceptionValidation {

		StringBuilder message = new StringBuilder();

		// Vérification du nom
		if (dtoParent.getNom() == null || dtoParent.getNom().isEmpty()) {
			message.append("\nLe nom est absent.");
		} else if (dtoParent.getNom().length() < 3) {
			message.append("\nLe nom est trop court.");
		} else if (dtoParent.getNom().length() > 50) {
			message.append("\nLe nom est trop long.");
		}

		// Vérification du prénom
		if (dtoParent.getPrenom() == null || dtoParent.getPrenom().isEmpty()) {
			message.append("\nLe prénom est absent.");
		} else if (dtoParent.getPrenom().length() < 3) {
			message.append("\nLe prénom est trop court.");
		} else if (dtoParent.getPrenom().length() > 50) {
			message.append("\nLe prénom est trop long.");
		}

		// Vérification de l'adresse
		if (dtoParent.getAdresse() == null || dtoParent.getAdresse().isEmpty()) {
			message.append("\nL'adresse est absente.");
		} else if (dtoParent.getAdresse().length() < 5) {
			message.append("\nL'adresse est trop courte.");
		} else if (dtoParent.getAdresse().length() > 100) {
			message.append("\nL'adresse est trop longue.");
		}

		// Vérification de l'email
		if (dtoParent.getEmail() == null || dtoParent.getEmail().isEmpty()) {
			message.append("\nL'email est absent.");
		} else if (!dtoParent.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			message.append("\nL'email n'est pas valide.");
		}

		// Vérification du téléphone
		if (dtoParent.getTelephone() == null || dtoParent.getTelephone().isEmpty()) {
			message.append("\nLe numéro de téléphone est absent.");
		} else if (!dtoParent.getTelephone().matches("^\\+?[0-9. ()-]{7,15}$")) {
			message.append("\nLe numéro de téléphone n'est pas valide.");
		}

		if (message.length() > 0) {
			throw new ExceptionValidation(message.toString().substring(1));
		}
	}




}
