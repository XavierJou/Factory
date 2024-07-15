package factory.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import factory.entity.Stagiaire;

public interface IDAOStagiaire extends JpaRepository<Stagiaire, Integer> {


}
