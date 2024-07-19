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

import formation.conceptdev.facto.dto.request.VideoprojecteurRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.VideoprojecteurResponse;
import formation.conceptdev.facto.entities.Videoprojecteur;
import formation.conceptdev.facto.services.VideoprojecteurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/videoprojecteurs")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class VideoprojecteurRestController {

    @Autowired
    private VideoprojecteurService videoprojecteurService;

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<VideoprojecteurResponse> getAll() {
        return videoprojecteurService.getAll().stream().map(videoprojecteur -> new VideoprojecteurResponse(videoprojecteur,false)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public VideoprojecteurResponse create(@Valid @RequestBody VideoprojecteurRequest videoprojecteurRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Videoprojecteur videoprojecteur = new Videoprojecteur();
        BeanUtils.copyProperties(videoprojecteurRequest, videoprojecteur);
        return new VideoprojecteurResponse(videoprojecteurService.insert(videoprojecteur),false);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public VideoprojecteurResponse getById(@PathVariable Integer id) {
        return new VideoprojecteurResponse(videoprojecteurService.getById(id),false);
    }

    @GetMapping("/{id}/cours")
    public VideoprojecteurResponse getByIdWithCours(@PathVariable Integer id) {
        return new VideoprojecteurResponse(videoprojecteurService.getByIdWithCours(id));
    }
}