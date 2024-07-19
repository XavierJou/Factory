package formation.conceptdev.facto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.repositories.IDAOCours;

@Service
public class CoursService {

	@Autowired
	IDAOCours daoCours;

	public Cours getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Cours sans id");
		}
		return daoCours.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public Cours getByIdWithStagiaire(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Cours sans id");
		}
		return daoCours.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Cours> getAll() {
		return daoCours.findAll();
	}

	public List<Cours> getAllByTitre(String titre) {
		return daoCours.findAllByTitre(titre);
	}

	public Cours insert(Cours cours) {
		if (cours.getTitre() == null) {
			throw new RuntimeException("Impossible d'insert Cours sans titre");
		}
		return daoCours.save(cours);
	}

	public Cours update(Cours cours) {
		if (cours.getTitre() == null) {
			throw new RuntimeException("Impossible d'insert Cours sans titre");
		}
		return daoCours.save(cours);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Cours sans id");
		}
		daoCours.deleteById(id);
	}

	public void delete(Cours cours) {
		deleteById(cours.getId());
	}

}
