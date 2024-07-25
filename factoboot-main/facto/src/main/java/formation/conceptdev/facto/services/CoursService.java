package formation.conceptdev.facto.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.conceptdev.facto.entities.CompetenceFormateur;
import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.repositories.IDAOCompetenceFormateur;
import formation.conceptdev.facto.repositories.IDAOCours;
import formation.conceptdev.facto.repositories.IDAOFormateur;
import formation.conceptdev.facto.repositories.IDAOUtilisateur;

@Service
public class CoursService {

	@Autowired
	IDAOCours daoCours;
	
	 @Autowired
	 IDAOCompetenceFormateur daoCompetenceFormateur;
	 
	 @Autowired
	 IDAOFormateur daoFormateur;
	 
	 @Autowired
	 IDAOUtilisateur daoUtilisateur;

	public Cours getById(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Cours sans id");
		}
		return daoCours.findById(id).orElseThrow(RuntimeException::new);
	}
	
	
	public List<Cours> getAllCoursWithFormateurAndUtilisateur() {
        return daoCours.findAllWithFormateurAndUtilisateur();
    }
	
	public List<Cours> getAllCoursWithMatiere(Integer id) {
        return daoCours.findAllWithMatiere(id);
    }
	
	public List<Cours> getAllCoursWithFormateur(Integer id) {
        return daoCours.findAllWithFormateur(id);
    }
	
	public Cours getByIdWithStagiaire(Integer id) {
		if (id == null) {
			throw new RuntimeException("Impossible de find Cours sans id");
		}
		return daoCours.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public Integer NombreCoursFormateurId(Integer idFormateur) {
		if (idFormateur == null) {
			throw new RuntimeException("Impossible de find Cours sans id");
		}
		return daoCours.countCoursByFormateurId(idFormateur);
	}
	

	public List<Formateur> getFormateurComptenceCours(Integer id_cours)
	{
		if (id_cours == null) {
			throw new RuntimeException("Impossible de find Cours sans id");
		}
		
		
		

		
		
		List<Integer> competences =daoCours.findCompetencesByCoursId(id_cours);
		
		
		
		List<CompetenceFormateur>  competenceFormateurs=daoCompetenceFormateur.findAllOrderByFormateurIdAsc();
		
		Integer idFormateurEnCours=-1;
		Formateur formateurEnCours=null;
		int nbComptenceTrouve=0;
		
		List<Formateur> formateurs= new ArrayList<Formateur>();
		
		boolean premier=false;
		
		
		for(CompetenceFormateur competenceFormateur  :competenceFormateurs)
		{	
			
			
			
			if (competenceFormateur.getFormateur().getId()!=idFormateurEnCours)
			{
				
				
				if (nbComptenceTrouve==competences.size())
				{
					
					
					formateurs.add(formateurEnCours);
					Utilisateur utilisateur= daoUtilisateur.findByFormateurId(idFormateurEnCours).orElse(null);
					if (utilisateur!=null)
					{
						formateurEnCours.setUtilisateur(utilisateur);
						daoFormateur.save(formateurEnCours);
					}
					
				}
				
				nbComptenceTrouve=0;
						
				idFormateurEnCours=competenceFormateur.getFormateur().getId();	
				formateurEnCours=competenceFormateur.getFormateur(); 
				
			}
			
			
			
			if (competences.contains(competenceFormateur.getCompetence().getId())) 
			{
				nbComptenceTrouve++;
				
							
			}
			
		}
		
		
		return formateurs;
		
		
		
	}
	
	public List<Cours> getCoursWithoutFormateur(Integer formateurId) {
        return daoCours.findByFormateurNot(formateurId);
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
	
	/*
	public List<Formateur> getFormateursForCours(Integer coursId) {
        Cours cours = daoCours.findById(coursId)
            .orElseThrow(() -> new IllegalArgumentException("Cours not found"));

        List<CompetenceFormateur> competenceFormateurs = daoCompetenceFormateur
            .findByCompetence_IdAndFormateur_Matiere_Id(cours.getMatiere().getId());

        return competenceFormateurs.stream()
            .map(CompetenceFormateur::getFormateur)
            .distinct()
            .collect(Collectors.toList());
    }*/

}
