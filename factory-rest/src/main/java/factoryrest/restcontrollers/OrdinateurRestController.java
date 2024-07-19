package factoryrest.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import factory.entity.Ordinateur;
import factory.service.OrdinateurService;
import factoryrest.dto.request.OrdinateurRequest;
import factoryrest.dto.response.OrdinateurResponse;

@RestController
@RequestMapping("/api/ordinateur")
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
}
