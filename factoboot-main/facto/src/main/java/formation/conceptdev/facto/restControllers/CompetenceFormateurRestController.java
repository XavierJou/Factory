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

import formation.conceptdev.facto.dto.request.CompetenceFormateurRequest;
import formation.conceptdev.facto.dto.response.CompetenceFormateurResponse;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.entities.Competence;
import formation.conceptdev.facto.entities.CompetenceFormateur;
import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.services.CompetenceFormateurService;
import formation.conceptdev.facto.services.CompetenceService;
import formation.conceptdev.facto.services.FormateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/competencesFormateur")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class CompetenceFormateurRestController {

    @Autowired
    private CompetenceFormateurService competenceFormateurService;
    @Autowired
    private CompetenceService competenceSrv;
    @Autowired
    private FormateurService formateurService;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<CompetenceFormateurResponse> getAll() {
        return competenceFormateurService.getAll().stream().map(competenceFormateur -> new CompetenceFormateurResponse(competenceFormateur,false,false)).collect(Collectors.toList());
    }
    
    @GetMapping("/details")
    @JsonView(CustomJsonViews.CompetenceFormateurResponseWithDetail.class)
    public List<CompetenceFormateurResponse> getAllWithDetail() {
        return competenceFormateurService.getAll().stream().map(competenceFormateur -> new CompetenceFormateurResponse(competenceFormateur,true,true)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceFormateurResponse create(@Valid @RequestBody CompetenceFormateurRequest competenceFormateurRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Competence competence = competenceSrv.getById(competenceFormateurRequest.getCompetenceId());
        Formateur formateur = formateurService.getById(competenceFormateurRequest.getFormateurId());
        
        CompetenceFormateur competenceFormateur = new CompetenceFormateur();
        
        competenceFormateur.setCompetence(competence);
        competenceFormateur.setFormateur(formateur);
        BeanUtils.copyProperties(competenceFormateurRequest, competenceFormateur);
        return new CompetenceFormateurResponse(competenceFormateurService.insert(competenceFormateur),false,false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public CompetenceFormateurResponse getById(@PathVariable Integer id) {
        return new CompetenceFormateurResponse(competenceFormateurService.getById(id),false,false);
    }

    @GetMapping("/{id}/details")
    @JsonView(CustomJsonViews.CompetenceFormateurResponseWithDetail.class)
    public CompetenceFormateurResponse getByIdWithDetails(@PathVariable Integer id) {
        return new CompetenceFormateurResponse(competenceFormateurService.getByIdWithDetails(id),true,true);
    }
    
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
    	competenceFormateurService.deleteById(id);   }
    
}