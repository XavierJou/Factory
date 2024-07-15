package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOPrerequis;
import factory.entity.Prerequis;

@Service
public class PrerequisService {

	@Autowired
	IDAOPrerequis daoPrerequis;

	public Prerequis getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Ordinateur sans id");
		}
		return daoPrerequis.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Prerequis> getAll() {
		return daoPrerequis.findAll();
	}

	public List<Prerequis> getAllByNom(String nom) {
		return daoPrerequis.findAllByNom(nom);
	}

	public Prerequis insert(Prerequis prerequis) {
		if (prerequis.getNom() == null) {
			throw new RuntimeException("Impossible d'insert daoPrerequis sans nom");
		}
		return daoPrerequis.save(prerequis);
	}

	public Prerequis update(Prerequis prerequis) {
		if (prerequis.getNom() == null) {
			throw new RuntimeException("Impossible d'insert daoPrerequis sans nom");
		}
		return daoPrerequis.save(prerequis);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete daoPrerequis sans id");
		}
		daoPrerequis.deleteById(id);
	}

	public void delete(Prerequis prerequis) {
		deleteById(prerequis.getId());
	}

}
