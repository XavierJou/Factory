package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOCompetenceMatiere;
import factory.model.CompetenceMatiere;

@Service
public class CompetenceMatiererService {

	@Autowired
	IDAOCompetenceMatiere daoCompetenceMatiere;
	

	public CompetenceMatiere getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find CompetenceMatiere sans id");
		}
		return daoCompetenceMatiere.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<CompetenceMatiere> getAll() {
		return daoCompetenceMatiere.findAll();
	}


	public CompetenceMatiere insert(CompetenceMatiere competenceFormateur) {
		
		return daoCompetenceMatiere.save(competenceFormateur);
	}

	public CompetenceMatiere update(CompetenceMatiere competenceFormateur) {
		
		return daoCompetenceMatiere.save(competenceFormateur);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete CompetenceMatiere sans id");
		}
		daoCompetenceMatiere.deleteById(id);
	}

	public void delete(CompetenceMatiere competence) {
		deleteById(competence.getId());
	}

}
