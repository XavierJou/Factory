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

import formation.conceptdev.facto.dto.request.OrdinateurRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.OrdinateurResponse;
import formation.conceptdev.facto.entities.Ordinateur;
import formation.conceptdev.facto.services.OrdinateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ordinateur")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class OrdinateurRestController {

	@Autowired
	private OrdinateurService ordinateurSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.Common.class)
	public List<OrdinateurResponse> getAll() {
		return ordinateurSrv.getAll().stream().map(o -> new OrdinateurResponse(o,false)).collect(Collectors.toList());
	}
	
	@GetMapping("/details")
	@JsonView(CustomJsonViews.OrdinateurWithDetails.class)
	public List<OrdinateurResponse> getWithDetails() {
		return ordinateurSrv.getAll().stream().map(o -> new OrdinateurResponse(o,true)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}/details")
    @JsonView(CustomJsonViews.OrdinateurWithDetails.class)
    public OrdinateurResponse getByIdWithDetails(@PathVariable Integer id) {
        return new OrdinateurResponse(ordinateurSrv.getById(id),false);
    }
	
	@GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public OrdinateurResponse getById(@PathVariable Integer id) {
        return new OrdinateurResponse(ordinateurSrv.getById(id),false);
    }
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdinateurResponse create(@RequestBody OrdinateurRequest ordinateurRequest) {
		Ordinateur o = new Ordinateur();
		BeanUtils.copyProperties(ordinateurRequest, o);
		return new OrdinateurResponse(ordinateurSrv.insert(o),false);
	}
	
	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.StagiaireWithFormation.class)
	public OrdinateurResponse update(@Valid @RequestBody OrdinateurRequest ordinateurRequest, BindingResult br,
			@PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Ordinateur ordinateur = new Ordinateur();
		BeanUtils.copyProperties(ordinateurRequest, ordinateur);
		ordinateur.setId(id);
		return new OrdinateurResponse(ordinateurSrv.update(ordinateur));
	}
		
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		ordinateurSrv.deleteById(id);
	}
}
