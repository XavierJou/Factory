package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import factory.model.Competence;
import factory.model.CompetenceFormateur;
import factory.model.CompetenceMatiere;
import factory.model.Cours;
import factory.model.CoursOrdinateurs;
import factory.model.DisponibiliteFormateur;
import factory.model.Formateur;
import factory.model.Formation;
import factory.model.Matiere;
import factory.model.Ordinateur;
import factory.model.Prerequis;
import factory.model.Role;
import factory.model.Salle;
import factory.model.Stagiaire;
import factory.model.Utilisateur;
import factory.model.Videoprojecteur;
import factory.service.CompetenceFormateurService;
import factory.service.CompetenceMatiererService;
import factory.service.CompetenceService;
import factory.service.CoursOrdinateursService;
import factory.service.CoursService;
import factory.service.DisponibiliteFormateurService;
import factory.service.FormateurService;
import factory.service.FormationService;
import factory.service.MatiereService;
import factory.service.OrdinateurService;
import factory.service.PrerequisService;
import factory.service.SalleService;
import factory.service.StagiaireService;
import factory.service.UtilisateurService;
import factory.service.VideoprojecteurService;



public class App {

	@Autowired
    private CompetenceService competenceService;

    @Autowired
    private CompetenceFormateurService competenceFormateurService;

    @Autowired
    private CompetenceMatiererService competenceMatiererService;

    @Autowired
    private CoursService coursService;

    @Autowired
    private CoursOrdinateursService coursOrdinateursService;

    @Autowired
    private DisponibiliteFormateurService disponibiliteFormateurService;

    @Autowired
    private FormateurService formateurService;

    @Autowired
    private FormationService formationService;

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private OrdinateurService ordinateurService;

    @Autowired
    private PrerequisService prerequisService;

    @Autowired
    private SalleService salleService;

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private VideoprojecteurService videoprojecteurService;

	
	public void run() {

		
		System.out.println("test");
		
		// Create and save Competences
        for (int i = 1; i <= 10; i++) {
            Competence competence = new Competence("Competence " + i);
            competenceService.insert(competence);
        }

        // Create and save Formateurs
        for (int i = 1; i <= 10; i++) {
            Formateur formateur = new Formateur();
            formateurService.insert(formateur);
        }
        
        // Create and save Videoprojecteurs
        for (int i = 1; i <= 10; i++) {
            Videoprojecteur videoprojecteur = new Videoprojecteur("Nom" + i, "Marque" + i,LocalDate.now());
            videoprojecteurService.insert(videoprojecteur);
        }

        // Create and save Matieres
        for (int i = 1; i <= 10; i++) {
            Matiere matiere = new Matiere("Titre " + i,i*50, "Objectif " + i, "Contenu " + i);
            matiereService.insert(matiere);
        }

        // Create and save Formations
        for (int i = 1; i <= 10; i++) {
            Formation formation = new Formation("Formation " + i, i*50, "Objectif " + i, "Contenu " + i, LocalDateTime.now(),  i+5);
            formationService.insert(formation);
        }

        
     // Create and save Ordinateurs
        for (int i = 1; i <= 100; i++) {
            Ordinateur ordinateur = new Ordinateur("Nom" + i, "marque" + i,"OS "+i,LocalDate.now());
            ordinateurService.insert(ordinateur);
        }
        
        // Create and save Salles
        for (int i = 1; i <= 10; i++) {
            Salle salle = new Salle("Salle " + i,i+2);
            salleService.insert(salle);
        }
        
        // Create and save Cours
        for (int i = 1; i <= 10; i++) {
            Formation formation = formationService.getById(i);
            Formateur formateur = formateurService.getById(i);
            Matiere matiere = matiereService.getById(i);
            Videoprojecteur videoprojecteur = videoprojecteurService.getById(i);
            Salle salle = salleService.getById(i);
            
            
            Cours cours = new Cours(LocalDateTime.now(), "titre" +i,true, true, true, matiere, formateur,formation,null,videoprojecteur,salle);
            coursService.insert(cours);
        }

        

        // Create and save CoursOrdinateurs
        for (int i = 1; i <= 10; i++) {
            Cours cours = coursService.getById(i);
            Ordinateur ordinateur = ordinateurService.getById(i);
            CoursOrdinateurs coursOrdinateurs = new CoursOrdinateurs();
            coursOrdinateurs.setOrdinateur(ordinateur);
            coursOrdinateurs.setCours(cours);
            coursOrdinateursService.insert(coursOrdinateurs);
        }

        // Create and save DisponibiliteFormateurs
        for (int i = 1; i <= 10; i++) {
            Formateur formateur = formateurService.getById(i);
            DisponibiliteFormateur disponibiliteFormateur = new DisponibiliteFormateur(formateur, LocalDate.now(), LocalDate.now().plusDays(1));
            disponibiliteFormateurService.insert(disponibiliteFormateur);
        }

        // Create and save Prerequis
        for (int i = 1; i <= 10; i++) {
            Formation formation = formationService.getById(i);
            Prerequis prerequis = new Prerequis("nom " + i, formation);
            prerequisService.insert(prerequis);
        }

      

        // Create and save Stagiaires
        for (int i = 1; i <= 10; i++) {
            Stagiaire stagiaire = new Stagiaire();
            stagiaireService.insert(stagiaire);
        }

        // Create and save Utilisateurs
        for (int i = 1; i <= 10; i++) {
            Utilisateur utilisateur = new Utilisateur("nom" + i,"prenom" + i,"username" + i,"password" + i,"email" + i+"@mail"+i+".com",Role.administrateur);
            utilisateurService.insert(utilisateur);
        }

       

        // Create and save CompetenceFormateurs
        for (int i = 1; i <= 10; i++) {
            Competence competence = competenceService.getById(i);
            Formateur formateur = formateurService.getById(i);
            CompetenceFormateur competenceFormateur = new CompetenceFormateur(competence, formateur);
            competenceFormateurService.insert(competenceFormateur);
        }

        // Create and save CompetenceMatieres
        for (int i = 1; i <= 10; i++) {
            Competence competence = competenceService.getById(i);
            Matiere matiere = matiereService.getById(i);
            CompetenceMatiere competenceMatiere = new CompetenceMatiere(competence, matiere);
            competenceMatiererService.insert(competenceMatiere);
        }
		
	}

}
