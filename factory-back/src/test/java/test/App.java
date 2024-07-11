package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

		List<Competence> competences= new ArrayList<Competence>();		
		// ajout données Competences
        for (int i = 1; i <= 100; i++) 
        {
            Competence competence = new Competence("Competence " + i);
            competences.add(competence);
            competenceService.insert(competence);
        }

        List<Formateur> formateurs= new ArrayList<Formateur>();
        // ajout données Formateurs
        for (int i = 1; i <= 20; i++) 
        {
            Formateur formateur = new Formateur();
            formateurs.add(formateur);
            formateurService.insert(formateur);
        }
        
        List<Videoprojecteur> videoprojecteurs= new ArrayList<Videoprojecteur>();
        // ajout données Videoprojecteurs
        for (int i = 1; i <= 10; i++) 
        {
        	
            Videoprojecteur videoprojecteur = new Videoprojecteur("Nom" + i, "Marque" + i,LocalDate.now().plusDays(-i*50));
            videoprojecteurs.add(videoprojecteur);
            videoprojecteurService.insert(videoprojecteur);
        }

        List<Matiere> matieres = new ArrayList<Matiere>();
        // ajout données Matieres
        for (int i = 1; i <= 10; i++) 
        {
            Matiere matiere = new Matiere("Titre " + i,i*50, "Objectif " + i, "Contenu " + i);
            matieres.add(matiere);
            matiereService.insert(matiere);
        }

        List<Formation> formations = new ArrayList<Formation>();
        // ajout données Formations
        for (int i = 1; i <= 5; i++) 
        {        	
        	Formation formation = new Formation("Formation " + i, i*50, "Objectif " + i, "Contenu " + i, LocalDateTime.now().plusMonths(-i*8),  20 + new java.util.Random().nextInt(50));
        	formations.add(formation);
        	formationService.insert(formation);
        	 // ajout données Prerequis
            for (int j = 1; j <= 5; j++) 
            {           	
                
                Prerequis prerequis = new Prerequis("nom_prerequis " + i*5+j, formation);
                prerequisService.insert(prerequis); 
            }
        }

        List<Ordinateur> ordinateurs = new ArrayList<Ordinateur>();
        		
     // ajout données Ordinateurs
        for (int i = 1; i <= 50; i++) 
        {
            Ordinateur ordinateur = new Ordinateur("Nom" + i, "marque" + i,"OS "+i,LocalDate.now().plusMonths(-i));
            ordinateurs.add(ordinateur);
            ordinateurService.insert(ordinateur);
        }
        
        List<Salle> salles= new ArrayList<Salle>();
        // ajout données Salles
        for (int i = 1; i <= 10; i++) {
            Salle salle = new Salle("Salle " + i,5 + new java.util.Random().nextInt(10));
            salles.add(salle);
            salleService.insert(salle);
        }
        
        // ajout données Cours
        for (int i = 1; i <= 10; i++) {
        	
        	Collections.shuffle(formations);        	
            Formation formation = formations.get(0);
             
            Collections.shuffle(formateurs);  
            Formateur formateur = formateurs.get(0);
            
            Collections.shuffle(matieres);
            Matiere matiere = matieres.get(0);
            
            Collections.shuffle(videoprojecteurs);
            Videoprojecteur videoprojecteur = videoprojecteurs.get(0);
            
            Collections.shuffle(salles);
            Salle salle = salles.get(0);
            
            
            Cours cours = new Cours(LocalDateTime.now(), "titre" +i,true, true, true, matiere, formateur,formation,null,videoprojecteur,salle);
            coursService.insert(cours);
        }

        

        // ajout données CoursOrdinateurs
        for (int i = 1; i <= 10; i++) {
            Cours cours = coursService.getById(i);
            Ordinateur ordinateur = ordinateurService.getById(i);
            CoursOrdinateurs coursOrdinateurs = new CoursOrdinateurs();
            coursOrdinateurs.setOrdinateur(ordinateur);
            coursOrdinateurs.setCours(cours);
            coursOrdinateursService.insert(coursOrdinateurs);
        }
        
        

        // ajout données DisponibiliteFormateurs
        for (int i = 1; i <= 10; i++) {
            Formateur formateur = formateurService.getById(i);
            int debut=0;
            int fin=0;
            for (int j=1;j<=10;j++)
            {
            	debut =  new java.util.Random().nextInt(8);
            	fin = debut+2+new java.util.Random().nextInt(8);
            	DisponibiliteFormateur disponibiliteFormateur = new DisponibiliteFormateur(formateur, LocalDate.now().plusWeeks(debut), LocalDate.now().plusWeeks(fin));
                disponibiliteFormateurService.insert(disponibiliteFormateur);
                debut=fin;
            }
            
        }

        
       

      
     // ajout données Stagiaires
        for (int i = 1; i <= 100; i++) 
        {
        	Collections.shuffle(formations);
            Stagiaire stagiaire = new Stagiaire(formations.get(0));            
            stagiaireService.insert(stagiaire);
            Utilisateur utilisateur = new Utilisateur("nom" + i,"prenom" + i,"username" + i,"password" + i,"email" + i+"@mail"+i+".com",stagiaire,Role.stagiaire);
            utilisateurService.insert(utilisateur);
        }
        
        

        // ajout données Utilisateurs
        for (int i = 101; i <= 105; i++) {
            Utilisateur utilisateur = new Utilisateur("nom" + i,"prenom" + i,"username" + i,"password" + i,"email" + i+"@mail"+i+".com",Role.administrateur);
            utilisateurService.insert(utilisateur);
        }
        
        // ajout données Utilisateurs
        for (int i = 106; i <= 110; i++) {
            Utilisateur utilisateur = new Utilisateur("nom" + i,"prenom" + i,"username" + i,"password" + i,"email" + i+"@mail"+i+".com",Role.gestionaire);
            utilisateurService.insert(utilisateur);
        }
        
     // ajout données Utilisateurs
        for (int i = 111; i <= 115; i++) {
            Utilisateur utilisateur = new Utilisateur("nom" + i,"prenom" + i,"username" + i,"password" + i,"email" + i+"@mail"+i+".com",Role.technicien);
            utilisateurService.insert(utilisateur);
        }
        
        for (int i = 116; i <= 126; i++) {
            Utilisateur utilisateur = new Utilisateur("nom" + i,"prenom" + i,"username" + i,"password" + i,"email" + i+"@mail"+i+".com",Role.technicien);
            utilisateurService.insert(utilisateur);
        }
        
     
  
        
        	
            
        
        
        // ajout données Utilisateurs
        for (int i = 127; i <= 137; i++) {
        	Formateur formateur = formateurService.getById(i-126);    
            Utilisateur utilisateur = new Utilisateur("nom" + i,"prenom" + i,"username" + i,"password" + i,"email" + i+"@mail"+i+".com",formateur,Role.administrateur);
            utilisateurService.insert(utilisateur);
            
            Collections.shuffle(competences);
	    	
        	for (int j=1;j<=15;j++)
        	{        		
        		CompetenceFormateur competenceFormateur = new CompetenceFormateur(competences.get(j), formateur);
                competenceFormateurService.insert(competenceFormateur);
        	}  
        }

       

       

        // ajout données CompetenceMatieres
        for (int i = 1; i <= 10; i++) {
        	Collections.shuffle(competences);
           
            Matiere matiere = matiereService.getById(i);            
            
            
            for (int j=1;j<=5;j++)
        	{        		
            	CompetenceMatiere competenceMatiere = new CompetenceMatiere(competences.get(j), matiere);
        		competenceMatiererService.insert(competenceMatiere);
        	}  
        }
        
        
        System.out.println("fin chargement");
		
	}

}
