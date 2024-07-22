package formation.conceptdev.facto.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.conceptdev.facto.entities.Cours;

public interface IDAOCours extends JpaRepository<Cours, Integer> {

	public List<Cours> findAllByTitre(String titre);

	public List<Cours> findByTitreContaining(String recherche);
	
	@Query("SELECT cm.competence.id " +
	           "FROM Cours c " +
	           "INNER JOIN CompetenceMatiere cm ON c.matiere.id = cm.matiere.id " +
	           "WHERE c.id = :coursId")
	    List<Integer> findCompetencesByCoursId(@Param("coursId") Integer coursId);
	
	

}
