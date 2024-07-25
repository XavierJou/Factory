package formation.conceptdev.facto.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import formation.conceptdev.facto.entities.Competence;
import formation.conceptdev.facto.entities.CompetenceFormateur;


public interface IDAOCompetenceFormateur extends JpaRepository<CompetenceFormateur, Integer> {

	
	 @Query("SELECT cf FROM CompetenceFormateur cf ORDER BY cf.formateur.id ASC")
	 List<CompetenceFormateur> findAllOrderByFormateurIdAsc();
	 
	 @Modifying
	    @Transactional
	    @Query("DELETE FROM CompetenceFormateur cf WHERE cf.formateur.id = :idFormateur")
	    void deleteAllByFormateurId(@Param("idFormateur") Integer idFormateur);
	 
	 @Query("SELECT c FROM CompetenceFormateur c WHERE c.formateur.id <> :formateurId OR c.formateur IS NULL")
	    List<Competence> findByFormateurNot(@Param("formateurId") Integer formateurId);
	
}
