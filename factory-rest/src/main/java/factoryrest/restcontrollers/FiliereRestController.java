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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import factory.model.Filiere;
import factory.service.FiliereService;
import factoryrest.dto.request.FiliereRequest;
import factoryrest.dto.response.CustomJsonViews;
import factoryrest.dto.response.FiliereResponse;

@RestController
@RequestMapping("/api/filiere")
public class FiliereRestController {

	@Autowired
	private FiliereService filiereSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.Common.class)
	public List<FiliereResponse> getAll() {
		return filiereSrv.getAll().stream().map(filiere -> new FiliereResponse(filiere,false)).collect(Collectors.toList());
	}

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(CustomJsonViews.Common.class)
	public FiliereResponse create(@Valid @RequestBody FiliereRequest filiereRequest, BindingResult br) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Filiere filiere = new Filiere();
		BeanUtils.copyProperties(filiereRequest, filiere);
		return new FiliereResponse(filiereSrv.insert(filiere),false);
	}

	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.Common.class)
	public FiliereResponse getById(@PathVariable Integer id) {
		return new FiliereResponse(filiereSrv.getById(id),false);
	}

	@GetMapping("/{id}/stagiaire")
	public FiliereResponse getByIdWithStagiaire(@PathVariable Integer id) {
		return new FiliereResponse(filiereSrv.getByIdWithStagiaire(id));
	}
	
}
