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
	public List<OrdinateurResponse> getAll() {
		return ordinateurSrv.getAll().stream().map(o -> new OrdinateurResponse(o)).collect(Collectors.toList());
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdinateurResponse create(@RequestBody OrdinateurRequest ordinateurRequest) {
		Ordinateur o = new Ordinateur();
		BeanUtils.copyProperties(ordinateurRequest, o);
		return new OrdinateurResponse(ordinateurSrv.insert(o));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		ordinateurSrv.deleteById(id);
	}
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.Common.class)
	public OrdinateurResponse create(@Valid @RequestBody OrdinateurRequest ordinateurRequest, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Ordinateur ordinateur = new Ordinateur();
		BeanUtils.copyProperties(ordinateurRequest, ordinateur);
		return new OrdinateurResponse(ordinateurSrv.insert(ordinateur), false);
	}
}
