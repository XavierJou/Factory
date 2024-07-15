package factory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import factory.entity.Ordinateur;

public interface IDAOOrdinateur extends JpaRepository<Ordinateur, Integer> {

	public List<Ordinateur> findAllByNom(String nom);

	public List<Ordinateur> findByNomContaining(String recherche);

}
