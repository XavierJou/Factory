package formation.conceptdev.facto.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Salle;

public interface IDAOSalle extends JpaRepository<Salle, Integer> {

	public List<Salle> findAllByNom(String nom);

	public List<Salle> findByNomContaining(String recherche);
	
	

}
