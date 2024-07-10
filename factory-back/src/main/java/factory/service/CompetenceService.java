package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOCompetence;
import factory.model.Competence;

@Service
public class CompetenceService {

	@Autowired
	IDAOCompetence daoCompetence;

	public Competence getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Competence sans id");
		}
		return daoCompetence.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Competence> getAll() {
		return daoCompetence.findAll();
	}


	public Competence insert(Competence competence) {
		if (competence.getNom() == null) {
			throw new RuntimeException("Impossible d'insert Competence sans nom");
		}
		return daoCompetence.save(competence);
	}

	public Competence update(Competence competence) {
		if (competence.getNom() == null) {
			throw new RuntimeException("Impossible de mettre Competence sans titre");
		}
		return daoCompetence.save(competence);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Competence sans id");
		}
		daoCompetence.deleteById(id);
	}

	public void delete(Competence competence) {
		deleteById(competence.getId());
	}

}
