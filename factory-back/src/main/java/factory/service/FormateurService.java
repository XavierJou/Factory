package factory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import factory.dao.IDAOFormateur;
import factory.entity.Formateur;

@Service
public class FormateurService {

	@Autowired
	IDAOFormateur daoFormateur;

	public Formateur getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Formateur sans id");
		}
		return daoFormateur.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<Formateur> getAll() {
		return daoFormateur.findAll();
	}
	

	public Formateur insert(Formateur formateur) {
		
		return daoFormateur.save(formateur);
	}

	public Formateur update(Formateur formateur) {
		
		return daoFormateur.save(formateur);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete Formateur sans id");
		}
		daoFormateur.deleteById(id);
	}

	public void delete(Formateur formateur) {
		deleteById(formateur.getId());
	}

}
