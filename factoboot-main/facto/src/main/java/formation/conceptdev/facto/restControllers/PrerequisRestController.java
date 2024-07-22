package formation.conceptdev.facto.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.request.PrerequisRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.PrerequisResponse;
import formation.conceptdev.facto.entities.Prerequis;
import formation.conceptdev.facto.services.PrerequisService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/prerequis")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class PrerequisRestController {

	@Autowired
	private PrerequisService prerequisSrv;

	@GetMapping("")
	@JsonView(CustomJsonViews.Common.class)
	public List<PrerequisResponse> getAll() {
		return prerequisSrv.getAll().stream().map(p -> new PrerequisResponse(p,false)).collect(Collectors.toList());
	}
	
	@GetMapping("/details")
	@JsonView(CustomJsonViews.PreRequisWithDetails.class)
	public List<PrerequisResponse> getWithDetails() {
		return prerequisSrv.getAll().stream().map(p -> new PrerequisResponse(p,true)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}/details")
    @JsonView(CustomJsonViews.PreRequisWithDetails.class)
    public PrerequisResponse getByIdWithDetails(@PathVariable Integer id) {
        return new PrerequisResponse(prerequisSrv.getById(id),false);
    }
	
	@GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public PrerequisResponse getById(@PathVariable Integer id) {
        return new PrerequisResponse(prerequisSrv.getById(id),false);
    }
	
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PrerequisResponse create(@RequestBody PrerequisRequest prerequisRequest) {
		Prerequis p = new Prerequis();
		BeanUtils.copyProperties(prerequisRequest, p);
		return new PrerequisResponse(prerequisSrv.insert(p),false);
	}
		
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		prerequisSrv.deleteById(id);
	}
}
