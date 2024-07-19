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

import formation.conceptdev.facto.dto.request.CoursOrdinateusRequest;
import formation.conceptdev.facto.dto.response.CoursOrdinateursResponse;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.entities.CoursOrdinateurs;
import formation.conceptdev.facto.services.CoursOrdinateursService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/coursOrdinateur")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class CoursOrdinateurRestController {

    @Autowired
    private CoursOrdinateursService coursOrdinateursService;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<CoursOrdinateursResponse> getAll() {
        return coursOrdinateursService.getAll().stream().map(coursOrdinateur -> new CoursOrdinateursResponse(coursOrdinateur,false,false)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public CoursOrdinateursResponse create(@Valid @RequestBody CoursOrdinateusRequest coursOrdinateurRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        CoursOrdinateurs coursOrdinateur = new CoursOrdinateurs();
        BeanUtils.copyProperties(coursOrdinateurRequest, coursOrdinateur);
        return new CoursOrdinateursResponse(coursOrdinateursService.insert(coursOrdinateur),false,false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public CoursOrdinateursResponse getById(@PathVariable Integer id) {
        return new CoursOrdinateursResponse(coursOrdinateursService.getById(id),false,false);
    }

    @GetMapping("/{id}/details")
    public CoursOrdinateursResponse getByIdWithDetails(@PathVariable Integer id) {
        return new CoursOrdinateursResponse(coursOrdinateursService.getByIdWithDetails(id));
    }
}