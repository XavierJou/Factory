package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Formateur;

public interface IDAOFormateur extends JpaRepository<Formateur, Integer> {

	public List<Formateur> findAllByNom(String nom);

	public List<Formateur> findByNameContaining(String recherche);

}
