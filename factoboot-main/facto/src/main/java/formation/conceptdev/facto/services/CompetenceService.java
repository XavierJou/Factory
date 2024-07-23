package formation.conceptdev.facto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.Competence;
import formation.conceptdev.facto.repositories.IDAOCompetence;
import formation.conceptdev.facto.repositories.IDAOCompetenceFormateur;
import formation.conceptdev.facto.repositories.IDAOCompetenceMatiere;
import jakarta.transaction.Transactional;

@Service
public class CompetenceService {

	@Autowired
	IDAOCompetence daoCompetence;

	@Autowired
	IDAOCompetenceFormateur daoCompetenceFormateur;

	@Autowired
	IDAOCompetenceMatiere daoCompetenceMatiere;

	public Competence getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Competence sans id");
		}
		return daoCompetence.findById(id).orElseThrow(RuntimeException::new);
	}

	public Competence getByIdWithFormateurs(Integer id) {
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
			throw new RuntimeException("Impossible d'insert une competence sans nom");
		}
		return daoCompetence.save(competence);
	}

	public Competence update(Competence competence) {
		if (competence.getNom() == null) {
			throw new RuntimeException("Impossible de mettre une competence sans nom");
		}
		return daoCompetence.save(competence);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de dsupprimer une competence sans id");
		}
		Competence competence = daoCompetence.findById(id).orElseThrow(RuntimeException::new);
	
//		// Supprimer les associations avec CompetenceFormateur
//		competence.getCompetenceFormateurs().forEach(cf -> {
//			daoCompetenceFormateur.delete(cf);
//		});
//
//		// Supprimer les associations avec CompetenceMatiere
//		competence.getCompetenceMatieres().forEach(cm -> {
//			daoCompetenceMatiere.delete(cm);
//		});
		
		// Vérifier les associations avec CompetenceFormateur
        if (!competence.getCompetenceFormateurs().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer une compétence utilisée par des formateurs");
        }

        // Vérifier les associations avec CompetenceMatiere
        if (!competence.getCompetenceMatieres().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer une compétence utilisée par des matières");
        }

		daoCompetence.deleteById(id);
	}

	public void delete(Competence competence) {
		if (competence.getId() == null) {
			throw new RuntimeException("Impossible de supprimer une competence sans id ???");
		}
		deleteById(competence.getId());
	}

}
