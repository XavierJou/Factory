package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOStagiaire;
import factory.model.Stagiaire;


@Service
public class StagiaireService {

	@Autowired
	IDAOStagiaire daoStagiaire;

	public Stagiaire getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find stagiaire sans id");
		}
		return daoStagiaire.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Stagiaire> getAll() {
		return daoStagiaire.findAll();
	}

	

	public Stagiaire insert(Stagiaire stagiaire) {
		
		return daoStagiaire.save(stagiaire);
	}

	public Stagiaire update(Stagiaire stagiaire) {
		
		return daoStagiaire.save(stagiaire);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete stagiaire sans id");
		}
		daoStagiaire.deleteById(id);
	}

	public void delete(Stagiaire stagiaire) {
		deleteById(stagiaire.getId());
	}

}
