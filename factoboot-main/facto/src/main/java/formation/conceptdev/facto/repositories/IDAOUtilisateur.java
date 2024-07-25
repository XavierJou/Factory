package formation.conceptdev.facto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import formation.conceptdev.facto.entities.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
	
	Optional<Utilisateur> findByLogin(String login);
	Optional<Utilisateur> findByEmail(String email);
	
	@Query("SELECT u FROM Utilisateur u WHERE u.formateur.id = :formateurId")
    Optional<Utilisateur> findByFormateurId(@Param("formateurId") Integer formateurId);
	
	@Query("SELECT u FROM Utilisateur u WHERE u.formateur.id = :stagiaireId")
    Optional<Utilisateur> findByStagiaireId(@Param("stagiaireId") Integer formateurId);
	
	//Query("SELECT cf FROM CompetenceFormateur cf ORDER BY cf.formateur.id ASC")
	// List<CompetenceFormateur> findAllOrderByFormateurIdAsc();
	
	@Query("SELECT u FROM Utilisateur u WHERE "
	        + "LOWER(u.login) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
	        + "LOWER(u.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
	        + "LOWER(u.prenom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
	        + "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
	        + "LOWER(u.role) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Utilisateur> searchByKeyword(@Param("keyword") String keyword);
	
	@Modifying
    @Transactional
    @Query("UPDATE Utilisateur f SET f.formateur = null WHERE f.id = :idUtilisateur")
    void detachFormateurFromUtilisateur(@Param("idUtilisateur") Integer idUtilisateur);
	
	
	@Modifying
    @Transactional
    @Query("UPDATE Utilisateur f SET f.stagiaire = null WHERE f.id = :idUtilisateur")
    void detachStagiaireFromUtilisateur(@Param("idUtilisateur") Integer idUtilisateur);

}
