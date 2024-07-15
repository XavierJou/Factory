package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOSalle;
import factory.entity.Salle;



@Service
public class SalleService {

	@Autowired
	IDAOSalle daoSalle;

	public Salle getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Salle sans id");
		}
		return daoSalle.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Salle> getAll() {
		return daoSalle.findAll();
	}

	public List<Salle> getAllByNom(String nom) {
		return daoSalle.findAllByNom(nom);
	}

	public Salle insert(Salle salle) {
		if (salle.getNom() == null) {
			throw new RuntimeException("Impossible d'insert daoPrerequis sans nom");
		}
		return daoSalle.save(salle);
	}

	public Salle update(Salle salle) {
		if (salle.getNom() == null) {
			throw new RuntimeException("Impossible d'insert daoPrerequis sans nom");
		}
		return daoSalle.save(salle);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete daoPrerequis sans id");
		}
		daoSalle.deleteById(id);
	}

	public void delete(Salle salle) {
		deleteById(salle.getId());
	}

}
