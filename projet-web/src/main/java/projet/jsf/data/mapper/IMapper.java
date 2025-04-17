package projet.jsf.data.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoGarde;
import projet.commun.dto.DtoParent;
import projet.commun.dto.DtoTarifs;
import projet.jsf.data.Compte;
import projet.jsf.data.Contrat;
import projet.jsf.data.Garde;
import projet.jsf.data.Parent;
import projet.jsf.data.Tarifs;


@Mapper( componentModel = "cdi" )
public interface IMapper {

	//-------
	// Compte
	//-------

	Compte    map( DtoCompte source );

	DtoCompte map( Compte source );

	Compte duplicate( Compte source );

	Compte update( @MappingTarget Compte target, Compte source );

	//-------
	// Parent
	//-------

	Parent    map( DtoParent source );

	DtoParent map( Parent source );

	Parent duplicate( Parent source );
	

	//-------
	// Contrat
	//-------

	Contrat    map( DtoContrat source );

	DtoContrat map( Contrat source );

	Contrat duplicate( Contrat source );
	
//	List<Contrat> duplicate( List<Contrat> source );
	
	//-------
	// Garde
	//-------

	Garde    map( DtoGarde source );

	DtoGarde map( Garde source );

	Garde duplicate( Garde source );

//	List<Garde> duplicate( List<Garde> source );

	//-------
	// Tarifs
	//-------

	Tarifs    map( DtoTarifs source );

	DtoTarifs map( Tarifs source );

	Tarifs duplicate( Tarifs source );

	
	
}
