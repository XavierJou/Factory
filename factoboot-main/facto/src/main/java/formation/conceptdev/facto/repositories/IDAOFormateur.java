package formation.conceptdev.facto.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.entities.Formation;

public interface IDAOFormateur extends JpaRepository<Formateur, Integer> {

	@Modifying
    @Transactional
    @Query("UPDATE Formateur f SET f.utilisateur = null WHERE f.id = :idFormateur")
    void detachUtilisateurFromFormateur(@Param("idFormateur") Integer idFormateur);
}
