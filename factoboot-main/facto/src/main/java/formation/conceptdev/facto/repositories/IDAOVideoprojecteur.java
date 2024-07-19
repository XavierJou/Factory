package formation.conceptdev.facto.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Videoprojecteur;

public interface IDAOVideoprojecteur extends JpaRepository<Videoprojecteur, Integer> {

	public List<Videoprojecteur> findAllByNom(String nom);
	
	public List<Videoprojecteur> findAllByMarque(String marque);

	public List<Videoprojecteur> findByNomContaining(String recherche);

}
