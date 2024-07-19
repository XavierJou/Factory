package formation.conceptdev.facto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Stagiaire;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer> {

//	@Query("SELECT s from Stagiaire s where s.ordinateur is null")
//	public List<Stagiaire> findAllDisponibles();
}
