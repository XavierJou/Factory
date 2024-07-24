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

import formation.conceptdev.facto.dto.request.FormationRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.FormationResponse;
import formation.conceptdev.facto.entities.Formation;
import formation.conceptdev.facto.services.FormationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/formation")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class FormationRestController {

	@Autowired
	private FormationService formationSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.Common.class)
	public List<FormationResponse> getAll() {
		return formationSrv.getAll().stream().map(formation -> new FormationResponse(formation,false, false, false)).collect(Collectors.toList());
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.Common.class)
	public FormationResponse create(@Valid @RequestBody FormationRequest formationRequest, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Formation formation = new Formation();
		BeanUtils.copyProperties(formationRequest, formation);
		return new FormationResponse(formationSrv.insert(formation),false, false, false);
	}
	
	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.Common.class)
	public FormationResponse update(@Valid @RequestBody FormationRequest formationRequest, BindingResult br,
			@PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Formation formation = new Formation();
		BeanUtils.copyProperties(formationRequest, formation);
		formation.setId(id);
		return new FormationResponse(formationSrv.update(formation));
	}

	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.Common.class)
	public FormationResponse getById(@PathVariable Integer id) {
		return new FormationResponse(formationSrv.getById(id),false, false, false);
	}

	@GetMapping("/{id}/stagiaire")
	public FormationResponse getByIdWithStagiaire(@PathVariable Integer id) {
		return new FormationResponse(formationSrv.getByIdWithStagiaire(id));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		formationSrv.deleteById(id);
	}
	
	@GetMapping("/{id}/cours")
	@JsonView(CustomJsonViews.FormationWithCours.class)
	public FormationResponse getByIdWithCours(@PathVariable Integer id) {
		return new FormationResponse(formationSrv.getByIdWithCours(id));
	}
	
}
