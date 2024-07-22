package formation.conceptdev.facto.restControllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.UtilisateurResponse;
import formation.conceptdev.facto.entities.Utilisateur;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@SecurityRequirement(name="basicAuth")
public class AuthRestController {

	@JsonView(CustomJsonViews.Common.class)
	@GetMapping("")
	public UtilisateurResponse authentication(@AuthenticationPrincipal Utilisateur utilisateur) {
		return new UtilisateurResponse(utilisateur);
	}
}

