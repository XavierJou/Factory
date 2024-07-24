package formation.conceptdev.facto.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import formation.conceptdev.facto.entities.DisponibiliteFormateur;

public interface IDAODisponibiliteFormateur extends JpaRepository<DisponibiliteFormateur, Integer> {

	@Modifying
    @Transactional
    @Query("DELETE FROM DisponibiliteFormateur df WHERE df.formateur.id = :idFormateur")
    void deleteAllByFormateurId(@Param("idFormateur") Integer idFormateur);

}
