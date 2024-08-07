package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.entity.Competence;

public interface IDAOCompetence extends JpaRepository<Competence, Integer> {

	public List<Competence> findAllByNom(String nom);

	public List<Competence> findByNomContaining(String recherche);

}
