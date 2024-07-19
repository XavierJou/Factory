package formation.conceptdev.facto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.services.UtilisateurService;
import jakarta.transaction.Transactional;

@SpringBootTest
class FactoinitAdmin {

	@Autowired
	UtilisateurService utilisateurSrv;

	@Test
	@Transactional
	@Commit
	void test() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin("admin");
		utilisateur.setPassword("admin");
		utilisateurSrv.create(utilisateur);

		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setLogin("user");
		utilisateur2.setPassword("user");
		utilisateurSrv.create(utilisateur2);
	}

}
