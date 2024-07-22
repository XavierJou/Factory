package formation.conceptdev.facto.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.conceptdev.facto.entities.CompetenceFormateur;


public interface IDAOCompetenceFormateur extends JpaRepository<CompetenceFormateur, Integer> {

	
	 @Query("SELECT cf FROM CompetenceFormateur cf ORDER BY cf.formateur.id ASC")
	 List<CompetenceFormateur> findAllOrderByFormateurIdAsc();
	
}
