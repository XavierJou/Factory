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

import formation.conceptdev.facto.dto.request.DisponibiliteFormateurRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.DisponibiliteFormateurResponse;
import formation.conceptdev.facto.entities.DisponibiliteFormateur;
import formation.conceptdev.facto.services.DisponibiliteFormateurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/disponibiliteFormateur")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class DisponibiliteFormateurRestController {

    @Autowired
    private DisponibiliteFormateurService disponibiliteFormateurService;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<DisponibiliteFormateurResponse> getAll() {
        return disponibiliteFormateurService.getAll().stream().map(disponibiliteFormateur -> new DisponibiliteFormateurResponse(disponibiliteFormateur,false)).collect(Collectors.toList());
    }
    
    @GetMapping("/details")
    @JsonView(CustomJsonViews.DisponibiliteFormateurResponseWithDetails.class)
    public List<DisponibiliteFormateurResponse> getWithDetails() {
        return disponibiliteFormateurService.getAll().stream().map(disponibiliteFormateur -> new DisponibiliteFormateurResponse(disponibiliteFormateur,true)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public DisponibiliteFormateurResponse create(@Valid @RequestBody DisponibiliteFormateurRequest disponibiliteFormateurRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        DisponibiliteFormateur disponibiliteFormateur = new DisponibiliteFormateur();
        BeanUtils.copyProperties(disponibiliteFormateurRequest, disponibiliteFormateur);
        return new DisponibiliteFormateurResponse(disponibiliteFormateurService.insert(disponibiliteFormateur),false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public DisponibiliteFormateurResponse getById(@PathVariable Integer id) {
        return new DisponibiliteFormateurResponse(disponibiliteFormateurService.getById(id),false);
    }

    @GetMapping("/{id}/details")
    @JsonView(CustomJsonViews.DisponibiliteFormateurResponseWithDetails.class)
    public DisponibiliteFormateurResponse getByIdWithDetails(@PathVariable Integer id) {
        return new DisponibiliteFormateurResponse(disponibiliteFormateurService.getByIdWithDetails(id),true);
    }
}