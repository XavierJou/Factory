package formation.conceptdev.facto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.DisponibiliteFormateur;
import formation.conceptdev.facto.repositories.IDAODisponibiliteFormateur;

@Service
public class DisponibiliteFormateurService {

	@Autowired
	IDAODisponibiliteFormateur daoDisponibiliteFormateur;

	public DisponibiliteFormateur getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find CoursOrdinateurs sans id");
		}
		return daoDisponibiliteFormateur.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public DisponibiliteFormateur getByIdWithDetails(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find CoursOrdinateurs sans id");
		}
		return daoDisponibiliteFormateur.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<DisponibiliteFormateur> getAll() {
		return daoDisponibiliteFormateur.findAll();
	}

	

	public DisponibiliteFormateur insert(DisponibiliteFormateur disponibiliteFormateur) {
		
		return daoDisponibiliteFormateur.save(disponibiliteFormateur);
	}

	public DisponibiliteFormateur update(DisponibiliteFormateur disponibiliteFormateur) {
		
		return daoDisponibiliteFormateur.save(disponibiliteFormateur);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete disponibiliteFormateur sans id");
		}
		daoDisponibiliteFormateur.deleteById(id);
	}

	public void delete(DisponibiliteFormateur disponibiliteFormateur) {
		deleteById(disponibiliteFormateur.getId());
	}
	
	public void deleteDisponibiliteByFormateurId(Integer idFormateur) {
		daoDisponibiliteFormateur.deleteAllByFormateurId(idFormateur);
    }
	

}
