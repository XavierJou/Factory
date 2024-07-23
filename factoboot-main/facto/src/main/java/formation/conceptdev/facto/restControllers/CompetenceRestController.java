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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.request.CompetenceRequest;
import formation.conceptdev.facto.dto.response.CompetenceResponse;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.entities.Competence;
import formation.conceptdev.facto.services.CompetenceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/competence")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class CompetenceRestController {

    @Autowired
    private CompetenceService competenceSrv;

//    @GetMapping("")
//    @JsonView(CustomJsonViews.Common.class)
//    public List<CompetenceResponse> getAll() {
//        return competenceSrv.getAll().stream().map(competence -> new CompetenceResponse(competence,false,false)).collect(Collectors.toList());
//    }
    
    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<CompetenceResponse> getAll(@RequestParam(value = "search", required = false) String search) {
        List<Competence> competences;
        if (search != null && !search.trim().isEmpty()) {
            competences = competenceSrv.searchByNom(search);
        } else {
            competences = competenceSrv.getAll();
        }
        return competences.stream()
                          .map(competence -> new CompetenceResponse(competence, false, false))
                          .collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceResponse create(@Valid @RequestBody CompetenceRequest competenceRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Competence competence = new Competence();
        BeanUtils.copyProperties(competenceRequest, competence);
        return new CompetenceResponse(competenceSrv.insert(competence),false,false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceResponse getById(@PathVariable Integer id) {
        return new CompetenceResponse(competenceSrv.getById(id),false,false);
    }

    @GetMapping("/{id}/formateurs")
    @JsonView(CustomJsonViews.CompetenceWithFormateurs.class)
    public CompetenceResponse getByIdWithFormateurs(@PathVariable Integer id) {
        return new CompetenceResponse(competenceSrv.getByIdWithFormateurs(id),true,false);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceResponse update(@PathVariable Integer id, @Valid @RequestBody CompetenceRequest competenceRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Competence existingCompetence = competenceSrv.getById(id);
        BeanUtils.copyProperties(competenceRequest, existingCompetence, "id", "competenceFormateurs", "competenceMatieres");
        return new CompetenceResponse(competenceSrv.update(existingCompetence), false, false);
    }
    
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		 Competence competence = competenceSrv.getById(id);
		 if (!competence.getCompetenceFormateurs().isEmpty() || !competence.getCompetenceMatieres().isEmpty()) {
		        throw new ResponseStatusException(HttpStatus.CONFLICT, "La compétence est utilisée et ne peut pas être supprimée.");
		    }
		competenceSrv.deleteById(id);
	}
	

}