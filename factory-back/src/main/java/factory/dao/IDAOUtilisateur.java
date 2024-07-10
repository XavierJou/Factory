package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {

	public List<Utilisateur> findAllByNom(String nom);

	public List<Utilisateur> findByNomContaining(String recherche);

}
