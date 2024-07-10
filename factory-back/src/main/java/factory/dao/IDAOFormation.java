package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Formation;

public interface IDAOFormation extends JpaRepository<Formation, Integer> {

	public List<Formation> findAllByTitre(String titre);

	public List<Formation> findByTitreContaining(String recherche);

}
