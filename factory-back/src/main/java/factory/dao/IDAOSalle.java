package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.model.Salle;

public interface IDAOSalle extends JpaRepository<Salle, Integer> {

	public List<Salle> findAllByNom(String nom);

	public List<Salle> findByNomContaining(String recherche);

}
