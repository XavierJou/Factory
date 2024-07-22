package formation.conceptdev.facto.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import formation.conceptdev.facto.dto.request.UtilisateurRequest;
import formation.conceptdev.facto.dto.response.UtilisateurResponse;
import formation.conceptdev.facto.entities.Role;
import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.services.UtilisateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/utilisateur")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class UtilisateurRestController {

	@Autowired
	private UtilisateurService utilisateurSrv;

	@GetMapping("")
	public List<UtilisateurResponse> getAll() {
		return utilisateurSrv.getAll().stream().map(u -> new UtilisateurResponse(u)).collect(Collectors.toList());
	}

	
	
	@PostMapping("/inscription")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UtilisateurResponse inscription(@Valid @RequestBody UtilisateurRequest utilisateurRequest, BindingResult br) {
		
		if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette erreeur");
        }
		
		System.out.println(utilisateurRequest.getLogin());
		
		   // Vérification si le login existe déjà
	    if (utilisateurSrv.loginExists(utilisateurRequest.getLogin())) {
	    	System.out.println("erreur login");
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "Login existe déjà");
	    }
	    
		Utilisateur utilisateur = new Utilisateur();
		BeanUtils.copyProperties(utilisateurRequest, utilisateur,"role","formateur","stagiaire");
		utilisateur.setRole(Role.valueOf(utilisateurRequest.getRole()));
		return new UtilisateurResponse(utilisateurSrv.create(utilisateur),false,false);
	}
}
