package formation.conceptdev.facto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.Formation;
import formation.conceptdev.facto.repositories.IDAOFormation;

@Service
public class FormationService {

	@Autowired
	IDAOFormation daoFormation;
	
	public Formation getByIdWithStagiaire(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find une formation sans id");
		}
		return daoFormation.findByIdFetchStagiaires(id).orElseThrow(RuntimeException::new);
	}
	
	public Formation getByIdWithCours(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find une formation sans id");
		}
		return daoFormation.findByIdFetchCours(id).orElseThrow(RuntimeException::new);
	}

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
	
	public List<Formation> getAllFetchCoursWithFormateurId(Integer id) {
		return daoFormation.findAllFetchCoursWithFormateurId(id);
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
