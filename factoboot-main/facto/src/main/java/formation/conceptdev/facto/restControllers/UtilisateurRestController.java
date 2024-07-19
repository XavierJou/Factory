package formation.conceptdev.facto.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import formation.conceptdev.facto.dto.request.UtilisateurRequest;
import formation.conceptdev.facto.dto.response.UtilisateurResponse;
import formation.conceptdev.facto.entities.Utilisateur;
import formation.conceptdev.facto.services.UtilisateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

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

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UtilisateurResponse create(@RequestBody UtilisateurRequest utilisateurRequest) {
		Utilisateur u = new Utilisateur();
		BeanUtils.copyProperties(utilisateurRequest, u);
		return new UtilisateurResponse(utilisateurSrv.insert(u));
	}
}
