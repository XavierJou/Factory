package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Stagiaire;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer> {

	public List<Stagiaire> findAllByNom(String nom);

	public List<Stagiaire> findByNameContaining(String recherche);

}
