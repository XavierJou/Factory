package formation.conceptdev.facto.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere, Integer> {

	public List<Matiere> findAllByTitre(String titre);

	public List<Matiere> findByTitreContaining(String recherche);

}
