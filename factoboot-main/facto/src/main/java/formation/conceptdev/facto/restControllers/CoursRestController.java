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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import formation.conceptdev.facto.dto.request.CoursRequest;
import formation.conceptdev.facto.dto.response.CoursResponse;
import formation.conceptdev.facto.dto.response.CustomJsonViews;
import formation.conceptdev.facto.dto.response.FormateurResponse;
import formation.conceptdev.facto.entities.Cours;
import formation.conceptdev.facto.entities.CoursOrdinateurs;
import formation.conceptdev.facto.services.CoursOrdinateursService;
import formation.conceptdev.facto.services.CoursService;
import formation.conceptdev.facto.services.FormateurService;
import formation.conceptdev.facto.services.FormationService;
import formation.conceptdev.facto.services.MatiereService;
import formation.conceptdev.facto.services.OrdinateurService;
import formation.conceptdev.facto.services.SalleService;
import formation.conceptdev.facto.services.VideoprojecteurService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cours")
@SecurityRequirement(name = "basicAuth")
@CrossOrigin(origins = "*")
public class CoursRestController {

	@Autowired
	private CoursService coursSrv;
    
	@Autowired
	private FormationService formationSrv;

	@Autowired
	private FormateurService formateurSrv;

	@Autowired
	private MatiereService matiereSrv;

	@Autowired
	private SalleService salleSrv;

	@Autowired
	private CoursOrdinateursService coursOrdinateurSrv;

	@Autowired
	private OrdinateurService ordinateurSrv;

	@Autowired
	private VideoprojecteurService videoprojecteurSrv;
	
    @GetMapping("/countFormateur/{idFormateur}")
    public Integer getCountCoursByFormateurId(@PathVariable Integer idFormateur) {
        return coursSrv.NombreCoursFormateurId(idFormateur);
    }

	@GetMapping("")
	@JsonView(CustomJsonViews.Common.class)
	public List<CoursResponse> getAll() {
		return coursSrv.getAll().stream()
				.map(cours -> new CoursResponse(cours, false, false, false, false, false, false, false))
				.collect(Collectors.toList());
	}

	@GetMapping("/formateur")
	@JsonView(CustomJsonViews.CoursResponseWithFormateur.class)
	public List<CoursResponse> getAllCoursWithFormateurAndUtilisateur() {
		return coursSrv.getAll().stream()
				.map(cours -> new CoursResponse(cours, false, true, false, false, false, false, true))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/formation/{idFormation}")
	@JsonView(CustomJsonViews.CoursResponseWithDetails.class)
    public List<CoursResponse> getCoursByFormationId(@PathVariable Integer idFormation) {
		return coursSrv.getCoursByFormationId(idFormation).stream()
				.map(cours -> new CoursResponse(cours, true, false, false, false, false, true, false))
		.collect(Collectors.toList());
       
    }
	
	@GetMapping("/formation")
	@JsonView(CustomJsonViews.CoursResponseWithDetails.class)
	public List<CoursResponse> getAllCoursWithFormation() {
		return coursSrv.getAll().stream()
				.map(cours -> new CoursResponse(cours, true, true, true, true, true, true, true))
				.collect(Collectors.toList());
	}
	
	//Utilisé dans dashboard formation/matieres
	@GetMapping("/formation/matieres/{idFormation}")
	@JsonView(CustomJsonViews.CoursResponseWithDetails.class)
	public List<CoursResponse> getAllCoursWithMatiere(@PathVariable Integer idFormation) {
		return coursSrv.getAllCoursWithMatiere(idFormation).stream()
				.map(cours -> new CoursResponse(cours, true, true, true, true, true, true, true))
				.collect(Collectors.toList());
	}
	
	//Utilisé dans dashboard formation/formateurs
	@GetMapping("/formation/formateurs/{idFormation}")
	@JsonView(CustomJsonViews.CoursResponseWithDetails.class)
	public List<CoursResponse> getAllCoursWithFormateur(@PathVariable Integer idFormation) {
		return coursSrv.getAllCoursWithFormateur(idFormation).stream()
				.map(cours -> new CoursResponse(cours, false, true, true, false, false, false, true))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@JsonView(CustomJsonViews.Common.class)
	public CoursResponse getById(@PathVariable Integer id) {
		return new CoursResponse(coursSrv.getById(id), false, false, false, false, false, false, false);
	}

	@GetMapping("/{id}/details")
	@JsonView(CustomJsonViews.CoursResponseWithDetails.class)
	public CoursResponse getByIdWithDetails(@PathVariable Integer id) {
		return new CoursResponse(coursSrv.getById(id));
	}
	
	@GetMapping("/sans-formateur/{id}")
	@JsonView(CustomJsonViews.CoursResponseWithDetails.class)
    public List<CoursResponse> getCoursSansFormateur(@PathVariable Integer id) {
		
		return coursSrv.getCoursWithoutFormateur(id).stream()
				.map(cours -> new CoursResponse(cours, true, true, true, true, true, true, false))
				.collect(Collectors.toList());   
    }

	@GetMapping("/{id}/formateurComptence")
	// @JsonView(CustomJsonViews.FormateurResponseWithUtilisateur.class)
	@JsonView(CustomJsonViews.FormateurResponseWithUtilisateur.class)
	public List<FormateurResponse> getFormateurComptence(@PathVariable Integer id) {
		return coursSrv.getFormateurComptenceCours(id).stream()
				.map(formateur -> new FormateurResponse(formateur, false, false, false, true))
				.collect(Collectors.toList());
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
		cours.setFormation(formationSrv.getById(coursRequest.getIdFormation()));
		cours.setFormateur(formateurSrv.getById(coursRequest.getIdFormateur()));
		cours.setMatiere(matiereSrv.getById(coursRequest.getIdMatiere()));
		cours.setSalle(salleSrv.getById(coursRequest.getIdSalle()));
		cours.setVideoprojecteur(videoprojecteurSrv.getById(coursRequest.getIdVideoprojecteur()));
		final Cours coursOrdi = coursSrv.insert(cours);
		
		coursRequest.getIdsOrdinateurs().stream().map(id -> ordinateurSrv.getById(id)).forEach(ordi -> {
			coursOrdinateurSrv.insert(new CoursOrdinateurs(coursOrdi, ordi));
		});
		return new CoursResponse(coursOrdi, true, true, true, true, true, true, true);
	}

	@PutMapping("/{id}")
	@JsonView(CustomJsonViews.Common.class)
	public CoursResponse update(@Valid @RequestBody CoursRequest coursRequest, BindingResult br,
			@PathVariable Integer id) {
		if (br.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Cours cours = new Cours();
		BeanUtils.copyProperties(coursRequest, cours);
		if (coursRequest.getIdFormation()!=null)			
		cours.setFormation(formationSrv.getById(coursRequest.getIdFormation()));
		if (coursRequest.getIdFormateur()!=null)
		cours.setFormateur(formateurSrv.getById(coursRequest.getIdFormateur()));
		if (coursRequest.getIdMatiere()!=null)
		cours.setMatiere(matiereSrv.getById(coursRequest.getIdMatiere()));
		if (coursRequest.getIdMatiere()!=null)
		cours.setSalle(salleSrv.getById(coursRequest.getIdMatiere()));
		if (coursRequest.getIdVideoprojecteur()!=null)
		cours.setVideoprojecteur(videoprojecteurSrv.getById(coursRequest.getIdVideoprojecteur()));
		cours.setId(id);

		final Cours coursOrdi = coursSrv.update(cours);
		
//		coursRequest.getIdsOrdinateurs().stream().map(idod -> ordinateurSrv.getById(idod))
//			.forEach(ordi -> {coursOrdinateurSrv.delete(new CoursOrdinateurs(coursOrdi, ordi));
//			});
		
		if(cours.getCoursOrdinateurs()!=null) {
			cours.getCoursOrdinateurs().stream().forEach(ordi -> coursOrdinateurSrv.delete(ordi));
		}
		
		if(coursRequest.getIdsOrdinateurs()!=null){
		coursRequest.getIdsOrdinateurs().stream().map(ido -> ordinateurSrv.getById(ido)).forEach(ordi -> {
			coursOrdinateurSrv.update(new CoursOrdinateurs(coursOrdi, ordi));
		});
		}
			
		return new CoursResponse(coursOrdi, true, true, true, true, true, true, false);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		coursSrv.deleteById(id);
	}
}