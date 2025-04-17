package projet.commun.service;

import java.util.List;

import projet.commun.dto.DtoTarifs;
import projet.commun.exception.ExceptionValidation;

public interface IServiceTarifs {

	int		inserer( DtoTarifs dtoTarifs ) throws ExceptionValidation;

	void	modifier( DtoTarifs dtoTarifs ) throws ExceptionValidation;

	void	supprimer( int idTarifs) throws ExceptionValidation;

	DtoTarifs	retrouver( int idTarifs);

	List<DtoTarifs>	listerTout();
}
