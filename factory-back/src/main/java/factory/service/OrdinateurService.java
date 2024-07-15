package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOOrdinateur;
import factory.entity.Ordinateur;

@Service
public class OrdinateurService {

	@Autowired
	IDAOOrdinateur daoOrdinateur;

	public Ordinateur getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Ordinateur sans id");
		}
		return daoOrdinateur.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Ordinateur> getAll() {
		return daoOrdinateur.findAll();
	}

	public List<Ordinateur> getAllByNom(String nom) {
		return daoOrdinateur.findAllByNom(nom);
	}

	public Ordinateur insert(Ordinateur ordinateur) {
		if (ordinateur.getNom() == null) {
			throw new RuntimeException("Impossible d'insert Ordinateur sans nom");
		}
		return daoOrdinateur.save(ordinateur);
	}

	public Ordinateur update(Ordinateur ordinateur) {
		if (ordinateur.getNom() == null) {
			throw new RuntimeException("Impossible d'insert Ordinateur sans nom");
		}
		return daoOrdinateur.save(ordinateur);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Ordinateur sans id");
		}
		daoOrdinateur.deleteById(id);
	}

	public void delete(Ordinateur ordinateur) {
		deleteById(ordinateur.getId());
	}

}
