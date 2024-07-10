package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Prerequis;

public interface IDAOPrerequis extends JpaRepository<Prerequis, Integer> {

	public List<Prerequis> findAllByNom(String nom);

	public List<Prerequis> findByNameContaining(String recherche);

}
