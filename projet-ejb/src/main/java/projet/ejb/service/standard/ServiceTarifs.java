package projet.ejb.service.standard;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import projet.commun.dto.DtoTarifs;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceTarifs;
import projet.ejb.dao.IDaoTarifs;
import projet.ejb.data.Tarifs;
import projet.ejb.data.mapper.IMapperEjb;

@Stateless
@Remote
public class ServiceTarifs implements IServiceTarifs {

	// Champs
	@Inject
	private IMapperEjb mapper;
	@Inject
	private IDaoTarifs daoTarifs;

	@Override
	public int inserer(DtoTarifs dtotarifs) throws ExceptionValidation {
//		verifierValiditeDonnees(dtotarifs);
		int id = daoTarifs.inserer(mapper.map(dtotarifs));
		return id;
	}

	@Override
	public void modifier(DtoTarifs dtoTarifs) throws ExceptionValidation {
//		verifierValiditeDonnees(dtotarifs);
		daoTarifs.modifier(mapper.map(dtoTarifs));
	}

	@Override
	public void supprimer(int idTarifs) throws ExceptionValidation {
		daoTarifs.supprimer(idTarifs);
	}

	@Override
	public DtoTarifs retrouver(int idTarifs) {
		return mapper.map(daoTarifs.retrouver(idTarifs));
	}

	@Override
	public List<DtoTarifs> listerTout() {
		List<DtoTarifs> liste = new ArrayList<>();
		for (Tarifs garde : daoTarifs.listerTout()) {
			liste.add(mapper.map(garde));
		}
		return liste;
	}

}
