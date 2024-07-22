package formation.conceptdev.facto.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.CompetenceFormateur;


public interface IDAOCompetenceFormateur extends JpaRepository<CompetenceFormateur, Integer> {

	//List<CompetenceFormateur> findByFormateur_Matiere_Id(
	//List<CompetenceFormateur> findByCompetence_IdAndFormateur_Matiere_Id(Integer competenceId, Integer matiereId);
}
