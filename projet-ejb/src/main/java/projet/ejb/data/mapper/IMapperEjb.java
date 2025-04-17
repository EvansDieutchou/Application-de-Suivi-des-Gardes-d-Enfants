package projet.ejb.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoContrat;
import projet.commun.dto.DtoGarde;
import projet.commun.dto.DtoParent;
import projet.commun.dto.DtoTarifs;
import projet.ejb.data.Compte;
import projet.ejb.data.Contrat;
import projet.ejb.data.Garde;
import projet.ejb.data.Parent;
import projet.ejb.data.Tarifs;

@Mapper( componentModel = "cdi" )
public interface IMapperEjb {  
	
	static final IMapperEjb INSTANCE = Mappers.getMapper( IMapperEjb.class );
	
	// Compte
	
	Compte map(DtoCompte source);
	
	DtoCompte map( Compte source );
	
	// Parent
	Parent map( DtoParent source );
	
//	@Mapping(target="compte" )
	DtoParent map( Parent source );
	
	
	
	// Contrat
	
	Contrat map( DtoContrat source );
	
//	@Mapping(target="parent")
	DtoContrat map( Contrat source );
	
//	@Mapping(target="parent", ignore = true )
//	DtoContrat mapMinimal( Contrat source );
	
	// Garde

	Garde map( DtoGarde source );
	
//	@Mapping( target="contrat", ignore = true )	
	DtoGarde map( Garde source );
	
	// Tarifs
	
	Tarifs map( DtoTarifs source );
	
	DtoTarifs map( Tarifs source );
	
	
}
