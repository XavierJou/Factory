package formation.conceptdev.facto.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import formation.conceptdev.facto.entities.Matiere;


public interface IDAOMatiere extends JpaRepository<Matiere, Integer> {

	public List<Matiere> findAllByTitre(String titre);

	public List<Matiere> findByTitreContaining(String recherche);

	@Query("SELECT m FROM Matiere m WHERE m.matiere.id = :matiereId")
    Optional<Matiere> findByMatiereId(@Param("matiereId") Integer matiereId);
	
	@Query("SELECT m FROM Matiere m WHERE m.matiere.titre = :matiereTitre")
    Optional<Matiere> findByTitre(@Param("matiereTitre") String matiereTitre);
	
}
