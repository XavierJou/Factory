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

import formation.conceptdev.facto.dto.request.FormateurRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.FormateurResponse;
import formation.conceptdev.facto.entities.Formateur;
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

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<FormateurResponse> getAll() {
        return formateurService.getAll().stream().map(formateur -> new FormateurResponse(formateur,false,false,false,false)).collect(Collectors.toList());
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
}