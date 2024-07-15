package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOCompetenceFormateur;
import factory.entity.CompetenceFormateur;

@Service
public class CompetenceFormateurService {

	@Autowired
	IDAOCompetenceFormateur daoCompetenceFormateur;

	public CompetenceFormateur getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find CompetenceFormateur sans id");
		}
		return daoCompetenceFormateur.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<CompetenceFormateur> getAll() {
		return daoCompetenceFormateur.findAll();
	}


	public CompetenceFormateur insert(CompetenceFormateur competenceMatiere) {
		
		return daoCompetenceFormateur.save(competenceMatiere);
	}

	public CompetenceFormateur update(CompetenceFormateur competenceFormateur) {
		
		return daoCompetenceFormateur.save(competenceFormateur);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete CompetenceFormateur sans id");
		}
		daoCompetenceFormateur.deleteById(id);
	}

	public void delete(CompetenceFormateur competenceMatiere) {
		deleteById(competenceMatiere.getId());
	}

}
