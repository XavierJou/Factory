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
	
	@Query("SELECT c FROM Cours c LEFT JOIN FETCH c.formateur f LEFT JOIN FETCH f.utilisateur")
    List<Cours> findAllWithFormateurAndUtilisateur();
	
	@Query("SELECT DISTINCT c FROM Cours c LEFT JOIN FETCH c.matiere WHERE c.formation.id = :idFormation")
    List<Cours> findAllWithMatiere(@Param("idFormation") Integer idFormation);
	
	
	 @Query("SELECT COUNT(c) FROM Cours c WHERE c.formateur.id = :idFormateur")
	  Integer countCoursByFormateurId(@Param("idFormateur") Integer idFormateur);
	 
	 @Query("SELECT c FROM Cours c WHERE c.formateur.id <> :formateurId OR c.formateur IS NULL")
	    List<Cours> findByFormateurNot(@Param("formateurId") Integer formateurId);
	

}
