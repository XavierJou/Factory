package factory.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import factory.model.Formation;

public interface IDAOFormation extends JpaRepository<Formation, Integer> {
	
	@Query("select f from Formation f left join fetch f.stagiaires where f.id=:id")
	Optional<Formation> findByIdFetchStagiaires(@Param("id") Integer id);

	public List<Formation> findAllByTitre(String titre);

	public List<Formation> findByTitreContaining(String recherche);

}
