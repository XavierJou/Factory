package formation.conceptdev.facto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.Matiere;
import formation.conceptdev.facto.entities.Ordinateur;
import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.repositories.IDAOMatiere;

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
	
	public Matiere getByIdWithCours(Integer id) {
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
	
	public List<Matiere> searchByTitre(String titre) {
	    return daoMatiere.findByTitreContaining(titre);
	}

	public Matiere insert(Matiere matiere) {
		String titre = matiere.getTitre();
		if (matiere.getTitre() == null) {
			throw new RuntimeException("Impossible d'insert Matiere sans titre");
		}
		
		if (!daoMatiere.findAllByTitreIgnoreCase(titre).isEmpty()) {
			throw new RuntimeException("La matiere existe déjà");
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
