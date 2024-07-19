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

import formation.conceptdev.facto.dto.request.CoursRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.CoursResponse;
import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.services.CoursService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cours")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class CoursRestController {

    @Autowired
    private CoursService coursSrv;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<CoursResponse> getAll() {
        return coursSrv.getAll().stream().map(cours -> new CoursResponse(cours,false)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public CoursResponse create(@Valid @RequestBody CoursRequest coursRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Cours cours = new Cours();
        BeanUtils.copyProperties(coursRequest, cours);
        return new CoursResponse(coursSrv.insert(cours),false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public CoursResponse getById(@PathVariable Integer id) {
        return new CoursResponse(coursSrv.getById(id),false);
    }

    @GetMapping("/{id}/stagiaire")
    public CoursResponse getByIdWithStagiaire(@PathVariable Integer id) {
        return new CoursResponse(coursSrv.getByIdWithStagiaire(id));
    }
}