package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Cours;

public interface IDAOCours extends JpaRepository<Cours, Integer> {

	public List<Cours> findAllByTitre(String titre);

	public List<Cours> findByTitleContaining(String recherche);

}
