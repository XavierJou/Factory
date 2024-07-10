package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOUtilisateur;
import factory.model.Utilisateur;

@Service
public class UtilisateurService {

	@Autowired
	IDAOUtilisateur daoUtilisateur;

	public Utilisateur getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Utilisateur sans id");
		}
		return daoUtilisateur.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Utilisateur> getAll() {
		return daoUtilisateur.findAll();
	}

	public List<Utilisateur> getAllByNom(String nom) {
		return daoUtilisateur.findAllByNom(nom);
	}

	public Utilisateur insert(Utilisateur utilisateur) {
		if (utilisateur.getNom() == null) {
			throw new RuntimeException("Impossible d'insert Utilisateur sans nom");
		}
		return daoUtilisateur.save(utilisateur);
	}

	public Utilisateur update(Utilisateur utilisateur) {
		if (utilisateur.getNom() == null) {
			throw new RuntimeException("Impossible d'insert Utilisateur sans nom");
		}
		return daoUtilisateur.save(utilisateur);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Utilisateur sans id");
		}
		daoUtilisateur.deleteById(id);
	}

	public void delete(Utilisateur utilisateur) {
		deleteById(utilisateur.getId());
	}

}
