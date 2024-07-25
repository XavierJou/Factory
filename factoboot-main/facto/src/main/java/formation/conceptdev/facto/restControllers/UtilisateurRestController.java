package formation.conceptdev.facto.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.request.UtilisateurRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.UtilisateurResponse;
import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.entities.Role;
import formation.conceptdev.facto.entities.Stagiaire;
import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.services.FormateurService;
import formation.conceptdev.facto.services.StagiaireService;
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
	
    @Autowired
    private FormateurService formateurService;
    
	@Autowired
	private StagiaireService stagiaireSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.Common.class)
	public List<UtilisateurResponse> getAll() {
		return utilisateurSrv.getAll().stream().map(u -> new UtilisateurResponse(u)).collect(Collectors.toList());
	}

	 @GetMapping("/{id}")
	    @JsonView(CustomJsonViews.Common.class)
	    public UtilisateurResponse getById(@PathVariable Integer id) {
	        return new UtilisateurResponse(utilisateurSrv.getById(id),false,false);
	    }
	
	@GetMapping("/details")
	@JsonView(CustomJsonViews.UtilisateurResponseWithDetails.class)
	public List<UtilisateurResponse> getWithDetails() {
		return utilisateurSrv.getAll().stream().map(u -> new UtilisateurResponse(u)).collect(Collectors.toList());
	}
	
	
	@PostMapping("/inscription")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UtilisateurResponse inscription(@Valid @RequestBody UtilisateurRequest utilisateurRequest, BindingResult br) {
		
		
		
		if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cette erreeur");
        }
		
		
		
		   // Vérification si le login existe déjà
	    if (utilisateurSrv.loginExists(utilisateurRequest.getLogin())) {
	    	
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "Login existe déjà");
	    }
	    
	    if (utilisateurSrv.emailExists(utilisateurRequest.getEmail())) {
	    	
	        throw new ResponseStatusException(HttpStatus.CONFLICT, "email existe déjà");
	    }
	    
		Utilisateur utilisateur = new Utilisateur();
		BeanUtils.copyProperties(utilisateurRequest, utilisateur,"role","formateur","stagiaire");
		utilisateur.setRole(Role.valueOf(utilisateurRequest.getRole()));
		
		System.out.println(utilisateur.toString());
		
		Utilisateur util_retour= new Utilisateur();
		util_retour.setRole(Role.ROLE_USER);
		return new UtilisateurResponse(utilisateurSrv.create(utilisateur),false,false);
		
	}
	
	 @PutMapping("/{id}")
	    @ResponseStatus(code = HttpStatus.OK)
	    @JsonView(CustomJsonViews.Common.class)
	    public UtilisateurResponse update(@PathVariable Integer id, @Valid @RequestBody UtilisateurRequest utilisateurRequest, BindingResult br) {
	        if (br.hasErrors()) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        }
	        Utilisateur utilisateur = utilisateurSrv.getById(id);
	        
	        
	        
	        if (utilisateurRequest.isAjoutFormateur())
	        {
	        	// ajout formateur
	        	Formateur formateur = new Formateur();
	        	formateur.setUtilisateur(utilisateur);	        	
	        	formateur = formateurService.insert(formateur);
	        	utilisateur.setFormateur(formateur);
	        }
	        if (utilisateurRequest.isAjoutStagiaire())
	        {
	        	// ajout formateur
	        	Stagiaire stagiaire = new Stagiaire();
	        	stagiaire.setUtilisateur(utilisateur);	        	
	        	stagiaire = stagiaireSrv.insert(stagiaire);
	        	utilisateur.setStagiaire(stagiaire);
	        	
	        }
	        BeanUtils.copyProperties(utilisateurRequest, utilisateur,"role","password");
	        utilisateur.setRole(Role.valueOf(utilisateurRequest.getRole()));
	        return new UtilisateurResponse(utilisateurSrv.update(utilisateur), false, false);
	    }
	 
	 @DeleteMapping("/{id}")
		@ResponseStatus(code = HttpStatus.OK)
		public void deleteById(@PathVariable("id") Integer id) {

		 utilisateurSrv.deleteById(id);
		}
	 
	 @DeleteMapping("/nullIdFormateur/{idUtilisateur}")
	    public void detachUtilisateurFromStagiaire(@PathVariable Integer idUtilisateur) {
		 utilisateurSrv.detachFormateurFromUtilisateur(idUtilisateur);
	    }
	 
	 @DeleteMapping("/nullIdStagiaire/{idUtilisateur}")
	    public void detachStagiaireFromStagiaire(@PathVariable Integer idUtilisateur) {
		 utilisateurSrv.detachStagiaireFromUtilisateur(idUtilisateur);
	    }
	 
}
