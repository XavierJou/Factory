package formation.conceptdev.facto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import formation.conceptdev.facto.entities.Competence;
import formation.conceptdev.facto.entities.CompetenceFormateur;
import formation.conceptdev.facto.entities.CompetenceMatiere;
import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.entities.CoursOrdinateurs;
import formation.conceptdev.facto.entities.DisponibiliteFormateur;
import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.entities.Formation;
import formation.conceptdev.facto.entities.Matiere;
import formation.conceptdev.facto.entities.Ordinateur;
import formation.conceptdev.facto.entities.Prerequis;
import formation.conceptdev.facto.entities.Role;
import formation.conceptdev.facto.entities.Salle;
import formation.conceptdev.facto.entities.Stagiaire;
import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.entities.Videoprojecteur;
import formation.conceptdev.facto.services.CompetenceFormateurService;
import formation.conceptdev.facto.services.CompetenceMatiereService;
import formation.conceptdev.facto.services.CompetenceService;
import formation.conceptdev.facto.services.CoursOrdinateursService;
import formation.conceptdev.facto.services.CoursService;
import formation.conceptdev.facto.services.DisponibiliteFormateurService;
import formation.conceptdev.facto.services.FormateurService;
import formation.conceptdev.facto.services.FormationService;
import formation.conceptdev.facto.services.MatiereService;
import formation.conceptdev.facto.services.OrdinateurService;
import formation.conceptdev.facto.services.PrerequisService;
import formation.conceptdev.facto.services.SalleService;
import formation.conceptdev.facto.services.StagiaireService;
import formation.conceptdev.facto.services.UtilisateurService;
import formation.conceptdev.facto.services.VideoprojecteurService;
import jakarta.transaction.Transactional;

@SpringBootTest
class FactoinitAdmin {

	@Autowired
	UtilisateurService utilisateurSrv;
	
	@Autowired
    private CompetenceService competenceService;

    @Autowired
    private CompetenceFormateurService competenceFormateurService;

    @Autowired
    private CompetenceMatiereService competenceMatiererService;

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


	
	@Test
	@Transactional
	@Commit
	void Ajout_Base()
	{
		

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
		            
		            
		            Cours cours = new Cours(LocalDateTime.now(), "titre" +i,true, true, true,true, matiere, formateur,formation,null,videoprojecteur,salle);
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
		            Utilisateur utilisateur = new Utilisateur("user"+i, "user", "nom" + i, "prenom" + i, "email" + i+"@mail"+i+".com",stagiaire,Role.ROLE_STAGIAIRE);
		            utilisateurService.create(utilisateur);
		        }
		        
		       

		        // ajout données Utilisateurs
		        for (int i = 101; i <= 105; i++) {
		            Utilisateur utilisateur = new Utilisateur("admin"+(i-100) , "admin", "nom" + i, "prenom" + i, "email" + i+"@mail"+i+".com",Role.ROLE_ADMINISTRATEUR);
		            utilisateurService.create(utilisateur);
		        }
		        
		        // ajout données Utilisateurs
		        for (int i = 106; i <= 110; i++) {
		            Utilisateur utilisateur = new Utilisateur("user"+i , "user", "nom" + i, "prenom" + i, "email" + i+"@mail"+i+".com",Role.ROLE_GESTIONNAIRE);
		            utilisateurService.create(utilisateur);
		        }
		        
		     // ajout données Utilisateurs
		        for (int i = 111; i <= 115; i++) {
		            Utilisateur utilisateur = new Utilisateur("user"+i, "user", "nom" + i, "prenom" + i, "email" + i+"@mail"+i+".com",Role.ROLE_TECHNICIEN);
		            utilisateurService.create(utilisateur);
		        }

		     
		  
		        
		        	
		            
		        
		        
		        // ajout données Utilisateurs
		        for (int i = 116; i <= 126; i++) {
		        	Formateur formateur = formateurService.getById(i-115);    
		            Utilisateur utilisateur = new Utilisateur("user"+i, "user", "nom" + i, "prenom" + i, "email" + i+"@mail"+i+".com",formateur,Role.ROLE_FORMATEUR);
		            utilisateurService.create(utilisateur);
		            
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
		            
		            
		            for (int j=1;j<=3;j++)
		        	{        		
		            	CompetenceMatiere competenceMatiere = new CompetenceMatiere(competences.get(j), matiere);
		        		competenceMatiererService.insert(competenceMatiere);
		        	}  
		        }
		        
		        
		        System.out.println("fin chargement");
				
			}

		

	

}
