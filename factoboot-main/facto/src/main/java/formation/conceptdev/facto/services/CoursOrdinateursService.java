package formation.conceptdev.facto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.CoursOrdinateurs;
import formation.conceptdev.facto.repositories.IDAOCoursOrdinateurs;

@Service
public class CoursOrdinateursService {

	@Autowired
	IDAOCoursOrdinateurs daoCoursOrdinateurs;

	public CoursOrdinateurs getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find CoursOrdinateurs sans id");
		}
		return daoCoursOrdinateurs.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public CoursOrdinateurs getByIdWithDetails(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find CoursOrdinateurs sans id");
		}
		return daoCoursOrdinateurs.findById(id).orElseThrow(RuntimeException::new);
	}

	public List<CoursOrdinateurs> getAll() {
		return daoCoursOrdinateurs.findAll();
	}

	public CoursOrdinateurs insert(CoursOrdinateurs coursOrdinateurs) {
		
		return daoCoursOrdinateurs.save(coursOrdinateurs);
	}

	public CoursOrdinateurs update(CoursOrdinateurs coursOrdinateurs) {
		
		return daoCoursOrdinateurs.save(coursOrdinateurs);
	}

	public void deleteById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de delete CoursOrdinateurs sans id");
		}
		daoCoursOrdinateurs.deleteById(id);
	}

	public void delete(CoursOrdinateurs coursOrdinateurs) {
		deleteById(coursOrdinateurs.getId());
	}

}
