package formation.conceptdev.facto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.conceptdev.facto.entities.Competence;
import formation.conceptdev.facto.entities.Cours;

public interface IDAOCompetence extends JpaRepository<Competence, Integer> {

	public List<Competence> findAllByNom(String nom);

	public List<Competence> findByNomContaining(String nom);
	
	

}
