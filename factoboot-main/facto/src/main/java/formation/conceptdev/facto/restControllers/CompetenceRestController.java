package formation.conceptdev.facto.restControllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.request.CompetenceRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.CompetenceResponse;
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
    private CompetenceService competenceService;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<CompetenceResponse> getAll() {
        return competenceService.getAll().stream().map(competence -> new CompetenceResponse(competence,false)).collect(Collectors.toList());
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
        return new CompetenceResponse(competenceService.insert(competence),false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceResponse getById(@PathVariable Integer id) {
        return new CompetenceResponse(competenceService.getById(id),false);
    }

    @GetMapping("/{id}/formateurs")
    public CompetenceResponse getByIdWithFormateurs(@PathVariable Integer id) {
        return new CompetenceResponse(competenceService.getByIdWithFormateurs(id));
    }
}