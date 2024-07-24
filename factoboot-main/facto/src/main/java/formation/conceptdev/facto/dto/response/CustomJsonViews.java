package formation.conceptdev.facto.dto.response;

public class CustomJsonViews {

	public interface Common {

	}
	
	
	public interface CompetenceFormateurResponseWithCompetence extends Common {

	}
	
	public interface CompetenceFormateurResponseWithFormateur extends Common {

	}
	
	public interface CompetenceFormateurResponseWithDetail extends CompetenceFormateurResponseWithCompetence,CompetenceFormateurResponseWithFormateur {

	}
	
	public interface CompetenceMatiereResponseWithMatiere extends Common {}

	public interface CompetenceMatiereResponseWithCompetence extends Common {}

	public interface CompetenceMatiereResponseWithDetails extends CompetenceMatiereResponseWithMatiere, CompetenceMatiereResponseWithCompetence {}


	public interface CompetenceWithFormateurs extends Common {}

	public interface CompetenceWithMatieres extends Common {}

	public interface CompetenceWithDetails extends CompetenceWithFormateurs, CompetenceWithMatieres {}
	
	
	public interface CoursOrdinateursWithCours extends Common {}

	public interface CoursOrdinateursWithOrdinateur extends Common {}

	public interface CoursOrdinateursWithDetails extends CoursOrdinateursWithCours, CoursOrdinateursWithOrdinateur {}

	
	
	public interface CoursResponseWithMatiere extends Common {}

	public interface CoursResponseWithFormateur extends Common,FormateurResponseWithUtilisateur {}
	
	public interface CoursResponseWithFormation extends Common {}

	public interface CoursResponseWithCoursOrdinateurs extends Common {}
	
	public interface CoursResponseWithVideoprojecteur extends Common {}

	public interface CoursResponseWithSalle extends Common {}
	
	public interface CoursResponseWithDetails extends Common {}

	public interface CoursWithDetails extends CoursResponseWithMatiere, CoursResponseWithFormateur, CoursResponseWithFormation,CoursResponseWithCoursOrdinateurs,CoursResponseWithVideoprojecteur,CoursResponseWithSalle{}
	
	
	public interface DisponibiliteFormateurResponseWithDetails extends Common {}

	
	public interface FormateurResponseWithCours extends Common {}

	public interface FormateurResponseWithCompetenceFormateur extends Common {}
	
	public interface FormateurResponseWithDisponibiliteFormateur extends Common {}

	public interface FormateurResponseWithUtilisateur extends Common {}

	public interface FormateurWithDetails extends CompetenceFormateurResponseWithCompetence,FormateurResponseWithCours ,FormateurResponseWithCompetenceFormateur ,FormateurResponseWithDisponibiliteFormateur ,FormateurResponseWithUtilisateur {}

	
	public interface FormationWithStagiaire extends Common {}

	public interface FormationWithCours extends Common {}
	
	public interface FormationWithPrerequis extends Common {}
	
	public interface FormationWithAll extends FormationWithStagiaire,FormationWithCours, FormationWithPrerequis, CoursResponseWithDetails {}


	public interface FormationsWithDetails extends FormationWithStagiaire , FormationWithCours , FormationWithPrerequis{}

	
	public interface MatiereWithCompetenceMatiere extends Common {}

	public interface MatiereWithCours extends Common {}

	public interface MatiereWithDetails extends MatiereWithCompetenceMatiere, MatiereWithCours {}

	
	public interface OrdinateurWithCoursOrdinateurs extends Common {}
	
	public interface OrdinateurWithDetails extends OrdinateurWithCoursOrdinateurs {}
	
	
	
	public interface PreRequisWithFormation extends Common {}
	
	public interface PreRequisWithDetails extends PreRequisWithFormation {}
	
	public interface SalleResponseWithCours extends Common {}
	
	public interface SalleWithDetails extends SalleResponseWithCours {}
	
	
	public interface StagiaireWithFormation extends Common {}
	
	public interface StagiaireWithDetails extends StagiaireWithFormation {}

	
	public interface UtilisateurResponseWithFormateur extends Common {}
	public interface UtilisateurResponseWithStagiaire extends Common {}
	
	public interface UtilisateurResponseWithDetails extends UtilisateurResponseWithFormateur,UtilisateurResponseWithStagiaire {}

	
	
	public interface FormateurPossiblePourCoursWithDisponibiliteFormateur extends Common {

	}
	
	public interface VideoprojecteurResponse extends Common {

	}
	
	public interface SalleResponse extends Common {

	}
	
	
	public interface CompetenceWithMatiere extends Common {
	
	}
	
	public interface CoursWithSalle extends Common {
		
	}
	

	public interface CompetenceWithFormation extends Common {
	
	}
	

	public interface CompetenceWithCoursOrdinateurs extends Common {
	
	}
	

	



	
	
	public interface CoursWithFormation extends Common {

	}


	
	public interface OrdinateurWithCours extends Common {

	}

	public interface CoursWithOrdinateur extends Common {

	}
}
