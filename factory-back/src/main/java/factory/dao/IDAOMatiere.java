package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere, Integer> {

	public List<Matiere> findAllByTitre(String titre);

	public List<Matiere> findByTitleContaining(String recherche);

}
