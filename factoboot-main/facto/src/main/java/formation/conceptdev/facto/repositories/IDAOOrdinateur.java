package formation.conceptdev.facto.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Ordinateur;

public interface IDAOOrdinateur extends JpaRepository<Ordinateur, Integer> {

	public List<Ordinateur> findAllByNom(String nom);

	public List<Ordinateur> findByNomContaining(String recherche);

}
