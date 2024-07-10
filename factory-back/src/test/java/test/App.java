package test;

import org.springframework.beans.factory.annotation.Autowired;

import factory.model.Role;
import factory.model.Utilisateur;
import factory.service.UtilisateurService;



public class App {

		@Autowired
		private UtilisateurService uS;

	
	public void run() {

		
		System.out.println("test");
		
		Utilisateur user = new Utilisateur("Raimbaud","paul","Rpaul","toto@toto.com",Role.administrateur);
		uS.insert(user);
		
	}

}
