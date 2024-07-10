package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOFormation;
import factory.model.Formation;

@Service
public class FormationService {

	@Autowired
	IDAOFormation daoFormation;

	public Formation getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Formation sans id");
		}
		return daoFormation.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Formation> getAll() {
		return daoFormation.findAll();
	}

	public List<Formation> getAllByTitre(String titre) {
		return daoFormation.findAllByTitre(titre);
	}

	public Formation insert(Formation formation) {
		if (formation.getTitre() == null) {
			throw new RuntimeException("Impossible d'insert Formation sans titre");
		}
		return daoFormation.save(formation);
	}

	public Formation update(Formation formation) {
		if (formation.getTitre() == null) {
			throw new RuntimeException("Impossible d'insert Formation sans titre");
		}
		return daoFormation.save(formation);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Formation sans id");
		}
		daoFormation.deleteById(id);
	}

	public void delete(Formation formation) {
		deleteById(formation.getId());
	}

}
