package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOMatiere;
import factory.model.Matiere;

@Service
public class MatiereService {

	@Autowired
	IDAOMatiere daoMatiere;

	public Matiere getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Matiere sans id");
		}
		return daoMatiere.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Matiere> getAll() {
		return daoMatiere.findAll();
	}

	public List<Matiere> getAllByTitre(String titre) {
		return daoMatiere.findAllByTitre(titre);
	}

	public Matiere insert(Matiere matiere) {
		if (matiere.getTitre() == null) {
			throw new RuntimeException("Impossible d'insert Matiere sans titre");
		}
		return daoMatiere.save(matiere);
	}

	public Matiere update(Matiere matiere) {
		if (matiere.getTitre() == null) {
			throw new RuntimeException("Impossible d'insert Matiere sans titre");
		}
		return daoMatiere.save(matiere);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Matiere sans id");
		}
		daoMatiere.deleteById(id);
	}

	public void delete(Matiere matiere) {
		deleteById(matiere.getId());
	}

}
