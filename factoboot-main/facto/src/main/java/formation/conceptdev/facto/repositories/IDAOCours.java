package formation.conceptdev.facto.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Cours;

public interface IDAOCours extends JpaRepository<Cours, Integer> {

	public List<Cours> findAllByTitre(String titre);

	public List<Cours> findByTitreContaining(String recherche);

}
