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

import formation.conceptdev.facto.dto.request.CompetenceMatiereRequest;
import formation.conceptdev.facto.dto.response.CompetenceMatiereResponse;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.entities.CompetenceMatiere;
import formation.conceptdev.facto.services.CompetenceMatiereService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/competencesMatieres")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class CompetenceMatiereRestController {

    @Autowired
    private CompetenceMatiereService competenceMatiereService;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<CompetenceMatiereResponse> getAll() {
        return competenceMatiereService.getAll().stream().map(competenceMatiere -> new CompetenceMatiereResponse(competenceMatiere,false,false)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceMatiereResponse create(@Valid @RequestBody CompetenceMatiereRequest competenceMatiereRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        CompetenceMatiere competenceMatiere = new CompetenceMatiere();
        BeanUtils.copyProperties(competenceMatiereRequest, competenceMatiere);
        return new CompetenceMatiereResponse(competenceMatiereService.insert(competenceMatiere),false,false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceMatiereResponse getById(@PathVariable Integer id) {
        return new CompetenceMatiereResponse(competenceMatiereService.getById(id),false,false);
    }

    @GetMapping("/{id}/details")
    public CompetenceMatiereResponse getByIdWithDetails(@PathVariable Integer id) {
        return new CompetenceMatiereResponse(competenceMatiereService.getByIdWithDetails(id));
    }
}