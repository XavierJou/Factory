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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.request.MatiereRequest;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.MatiereResponse;
import formation.conceptdev.facto.dto.response.OrdinateurResponse;
import formation.conceptdev.facto.entities.Matiere;
import formation.conceptdev.facto.entities.Ordinateur;
import formation.conceptdev.facto.services.MatiereService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/matiere")
@SecurityRequirement(name="basicAuth")
@CrossOrigin(origins = "*")
public class MatiereRestController {

    @Autowired
    private MatiereService matiereService;
    

    @GetMapping("")
    @JsonView(CustomJsonViews.Common.class)
    public List<MatiereResponse> getAll() {
        return matiereService.getAll().stream().map(matiere -> new MatiereResponse(matiere,false,false)).collect(Collectors.toList());
    }
    
    @GetMapping("/search")
    @JsonView(CustomJsonViews.Common.class)
    public List<MatiereResponse> getAll(@RequestParam(value = "search", required = false) String search) {
        List<Matiere> matieres;
        if (search != null && !search.trim().isEmpty()) {
            matieres = matiereService.searchByTitre(search);
        } else {
            matieres = matiereService.getAll();
        }
        return matieres.stream()
                       .map(matiere -> new MatiereResponse(matiere, false, false))
                       .collect(Collectors.toList());
    }
    
    @GetMapping("/details")
    @JsonView(CustomJsonViews.Common.class)
    public List<MatiereResponse> getWithDetails() {
        return matiereService.getAll().stream().map(matiere -> new MatiereResponse(matiere,true,true)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonViews.Common.class)
    public MatiereResponse getById(@PathVariable Integer id) {
        return new MatiereResponse(matiereService.getById(id),false,false);
    }

    @GetMapping("/{id}/cours")
    @JsonView(CustomJsonViews.MatiereWithCours.class)
    public MatiereResponse getByIdWithCours(@PathVariable Integer id) {
        return new MatiereResponse(matiereService.getByIdWithCours(id),false,true);
    }
    
    @GetMapping("/{id}/details")
    @JsonView(CustomJsonViews.MatiereWithDetails.class)
    public MatiereResponse getByIdWithDetails(@PathVariable Integer id) {
        return new MatiereResponse(matiereService.getByIdWithCours(id),true,true);
    }
    
    @DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
    	matiereService.deleteById(id);
	}
    
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @JsonView(CustomJsonViews.Common.class)
    public MatiereResponse create(@Valid @RequestBody MatiereRequest matiereRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Matiere matiere = new Matiere();
        BeanUtils.copyProperties(matiereRequest, matiere);
        return new MatiereResponse(matiereService.insert(matiere),false,false);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @JsonView(CustomJsonViews.Common.class)
    public MatiereResponse update(@Valid @RequestBody MatiereRequest matiereRequest, BindingResult br, @PathVariable Integer id) {
        if (br.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Matiere matiere = new Matiere();
        BeanUtils.copyProperties(matiereRequest, matiere);
        matiere.setId(id);
        return new MatiereResponse(matiereService.update(matiere),false,false);
    }
}