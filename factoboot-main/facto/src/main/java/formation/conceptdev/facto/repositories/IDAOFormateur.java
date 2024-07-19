package formation.conceptdev.facto.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Formateur;

public interface IDAOFormateur extends JpaRepository<Formateur, Integer> {


}
