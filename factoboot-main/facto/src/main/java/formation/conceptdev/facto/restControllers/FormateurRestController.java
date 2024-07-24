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

import formation.conceptdev.facto.dto.request.FormateurRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.FormateurResponse;
import formation.conceptdev.facto.entities.Competence;
import formation.conceptdev.facto.entities.Formateur;
import formation.conceptdev.facto.services.CompetenceFormateurService;
import formation.conceptdev.facto.services.DisponibiliteFormateurService;
import formation.conceptdev.facto.services.FormateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/formateur")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class FormateurRestController {

    @Autowired
    private FormateurService formateurService;
    @Autowired
    private DisponibiliteFormateurService disponibiliteFormateurService;

    @Autowired
    private CompetenceFormateurService competenceFormateurService;
    
    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<FormateurResponse> getAll() {
        return formateurService.getAll().stream().map(formateur -> new FormateurResponse(formateur,false,false,false,false)).collect(Collectors.toList());
    }
    
    @GetMapping("/utilisateur")
    @JsonView(CustomJsonViews.FormateurResponseWithUtilisateur.class)
    public List<FormateurResponse> getWithUtilisateur() {
        return formateurService.getAll().stream().map(formateur -> new FormateurResponse(formateur,false,false,false,true)).collect(Collectors.toList());
    }
    
    @GetMapping("/details")
    @JsonView(CustomJsonViews.FormateurWithDetails.class)
    public List<FormateurResponse> getWithDetails() {
        return formateurService.getAll().stream().map(formateur -> new FormateurResponse(formateur,true,true,true,true)).collect(Collectors.toList());
    }
    
    

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public FormateurResponse create(@Valid @RequestBody FormateurRequest formateurRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Formateur formateur = new Formateur();
        BeanUtils.copyProperties(formateurRequest, formateur);
        return new FormateurResponse(formateurService.insert(formateur),false,false,false,false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public FormateurResponse getById(@PathVariable Integer id) {
        return new FormateurResponse(formateurService.getById(id),false,false,false,false);
    }

    @GetMapping("/{id}/cours")
    public FormateurResponse getByIdWithCours(@PathVariable Integer id) {
        return new FormateurResponse(formateurService.getByIdWithCours(id),false,false,false,false);
    }
    
    @DeleteMapping("/nullIdUtilisateur/{idFormateur}")
    public void detachUtilisateurFromFormateur(@PathVariable Integer idFormateur) {
        formateurService.detachUtilisateurFromFormateur(idFormateur);
    }
    
    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
    	
    	 disponibiliteFormateurService.deleteDisponibiliteByFormateurId(id);
    	 competenceFormateurService.deleteCompetenceByFormateurId(id);
    	 formateurService.detachUtilisateurFromFormateur(id);
		 formateurService.deleteById(id);
	}
}