package formation.conceptdev.facto.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.CoursOrdinateurs;
import formation.conceptdev.facto.entities.Ordinateur;




public interface IDAOCoursOrdinateurs extends JpaRepository<CoursOrdinateurs, Integer> {

}
