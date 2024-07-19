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

import formation.conceptdev.facto.dto.request.SalleRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.SalleResponse;
import formation.conceptdev.facto.entities.Salle;
import formation.conceptdev.facto.services.SalleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/salle")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class SalleRestController {

    @Autowired
    private SalleService salleService;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<SalleResponse> getAll() {
        return salleService.getAll().stream().map(salle -> new SalleResponse(salle,false)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public SalleResponse create(@Valid @RequestBody SalleRequest salleRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Salle salle = new Salle();
        BeanUtils.copyProperties(salleRequest, salle);
        return new SalleResponse(salleService.insert(salle),false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public SalleResponse getById(@PathVariable Integer id) {
        return new SalleResponse(salleService.getById(id),false);
    }

    @GetMapping("/{id}/cours")
    public SalleResponse getByIdWithCours(@PathVariable Integer id) {
        return new SalleResponse(salleService.getByIdWithCours(id),false);
    }
}