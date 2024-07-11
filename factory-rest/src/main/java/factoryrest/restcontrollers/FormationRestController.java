package factoryrest.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import factory.model.Formation;
import factory.service.FormationService;
import factoryrest.dto.request.FormationRequest;
import factoryrest.dto.response.CustomJsonViews;
import factoryrest.dto.response.FormationResponse;

@RestController
@RequestMapping("/api/formation")
public class FormationRestController {

	@Autowired
	private FormationService formationSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.Common.class)
	public List<FormationResponse> getAll() {
		return formationSrv.getAll().stream().map(formation -> new FormationResponse(formation,false)).collect(Collectors.toList());
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
		return new FormationResponse(formationSrv.insert(formation),false);
	}

	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.Common.class)
	public FormationResponse getById(@PathVariable Integer id) {
		return new FormationResponse(formationSrv.getById(id),false);
	}

	@GetMapping("/{id}/stagiaire")
	public FormationResponse getByIdWithStagiaire(@PathVariable Integer id) {
		return new FormationResponse(formationSrv.getByIdWithStagiaire(id));
	}
	
}
