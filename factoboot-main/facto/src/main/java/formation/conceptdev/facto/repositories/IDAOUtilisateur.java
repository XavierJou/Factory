package formation.conceptdev.facto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import formation.conceptdev.facto.entities.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
	
	Optional<Utilisateur> findByLogin(String login);
	
	@Query("SELECT u FROM Utilisateur u WHERE u.formateur.id = :formateurId")
    Optional<Utilisateur> findByFormateurId(@Param("formateurId") Integer formateurId);
	
	@Query("SELECT u FROM Utilisateur u WHERE u.formateur.id = :stagiaireId")
    Optional<Utilisateur> findByStagiaireId(@Param("stagiaireId") Integer formateurId);
	
	//Query("SELECT cf FROM CompetenceFormateur cf ORDER BY cf.formateur.id ASC")
	// List<CompetenceFormateur> findAllOrderByFormateurIdAsc();
	
	
	@Modifying
    @Transactional
    @Query("UPDATE Utilisateur f SET f.formateur = null WHERE f.id = :idUtilisateur")
    void detachFormateurFromUtilisateur(@Param("idUtilisateur") Integer idUtilisateur);
	
	
	@Modifying
    @Transactional
    @Query("UPDATE Utilisateur f SET f.stagiaire = null WHERE f.id = :idUtilisateur")
    void detachStagiaireFromUtilisateur(@Param("idUtilisateur") Integer idUtilisateur);

}
