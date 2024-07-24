package formation.conceptdev.facto.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.FormationResponse;
import formation.conceptdev.facto.services.FormationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/dashboard")
@SecurityRequirement(name = "basicAuth")
@CrossOrigin(origins = "*")
public class DashboardRestController {

	@Autowired
	private FormationService formationSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.FormationWithAll.class)
	public List<FormationResponse> getAllWithDetails() {
		return formationSrv.getAll().stream().map(formation -> new FormationResponse(formation, true))
				.collect(Collectors.toList());
	}

}
