package formation.conceptdev.facto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import formation.conceptdev.facto.entities.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
	Optional<Utilisateur> findByLogin(String login);

}
