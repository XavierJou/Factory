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

import formation.conceptdev.facto.dto.request.StagiaireRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.StagiaireResponse;
import formation.conceptdev.facto.entities.Stagiaire;
import formation.conceptdev.facto.services.FormationService;
import formation.conceptdev.facto.services.StagiaireService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/stagiaire")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class StagiaireRestController {

	@Autowired
	private StagiaireService stagiaireSrv;
	@Autowired
	private FormationService formationSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.StagiaireWithFormation.class)
	public List<StagiaireResponse> getAll() {
		return stagiaireSrv.getAll().stream().map(entity -> new StagiaireResponse(entity)).collect(Collectors.toList());
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.StagiaireWithFormation.class)
	public StagiaireResponse create(@Valid @RequestBody StagiaireRequest stagiaireRequest, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Stagiaire entity = new Stagiaire();
		BeanUtils.copyProperties(stagiaireRequest, entity);
		entity.setFormation(formationSrv.getById(stagiaireRequest.getIdFormation()));
		return new StagiaireResponse(stagiaireSrv.insert(entity));
	}

	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.StagiaireWithFormation.class)
	public StagiaireResponse update(@Valid @RequestBody StagiaireRequest stagiaireRequest, BindingResult br,
			@PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Stagiaire stagiaire = new Stagiaire();
		BeanUtils.copyProperties(stagiaireRequest, stagiaire);
		stagiaire.setFormation(formationSrv.getById(stagiaireRequest.getIdFormation()));
		stagiaire.setId(id);
		return new StagiaireResponse(stagiaireSrv.update(stagiaire));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		stagiaireSrv.deleteById(id);
	}
}