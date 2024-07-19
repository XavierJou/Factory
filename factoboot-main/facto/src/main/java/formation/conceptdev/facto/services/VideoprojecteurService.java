package formation.conceptdev.facto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.Videoprojecteur;
import formation.conceptdev.facto.repositories.IDAOVideoprojecteur;

@Service
public class VideoprojecteurService {

	@Autowired
	IDAOVideoprojecteur daoVideoprojecteur;

	public Videoprojecteur getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Utilisateur sans id");
		}
		return daoVideoprojecteur.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Videoprojecteur> getAll() {
		return daoVideoprojecteur.findAll();
	}

	public List<Videoprojecteur> getAllByNom(String nom) {
		return daoVideoprojecteur.findAllByNom(nom);
	}

	public List<Videoprojecteur> getAllByMarque(String marque) {
		return daoVideoprojecteur.findAllByMarque(marque);
	}

	public Videoprojecteur insert(Videoprojecteur videoprojecteur) {
		if (videoprojecteur.getNom() == null) {
			throw new RuntimeException("Impossible d'insert Utilisateur sans nom");
		}
		return daoVideoprojecteur.save(videoprojecteur);
	}

	public Videoprojecteur update(Videoprojecteur videoprojecteur) {
		if (videoprojecteur.getNom() == null) {
			throw new RuntimeException("Impossible d'insert Utilisateur sans nom");
		}
		return daoVideoprojecteur.save(videoprojecteur);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Utilisateur sans id");
		}
		daoVideoprojecteur.deleteById(id);
	}

	public void delete(Videoprojecteur videoprojecteur) {
		deleteById(videoprojecteur.getId());
	}

}
