package formation.conceptdev.facto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import formation.conceptdev.facto.entities.Stagiaire;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer> {

//	@Query("SELECT s from Stagiaire s where s.ordinateur is null")
//	public List<Stagiaire> findAllDisponibles();
	
	@Modifying
    @Transactional
    @Query("UPDATE Stagiaire f SET f.utilisateur = null WHERE f.id = :idFormateur")
    void detachUtilisateurFromStagiaire(@Param("idFormateur") Integer idFormateur);
}
