package formation.conceptdev.facto.dto.response;

public class CustomJsonViews {

	public interface Common {

	}

	public interface StagiaireWithFormation extends Common {

	}

	public interface FormationWithStagiaire extends Common {

	}
	
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
	
	public interface CoursOrdinateursWithCours extends Common {
		
	}
	
	public interface CoursOrdinateursWithOrdinateur extends Common {
		
	}
	
	
	public interface CompetenceWithFormateurs extends Common {
		
	}
	
	public interface CompetenceWithMatieres extends Common {
		
	}
	
public interface CoursResponseWithMatiere extends Common {
		
	}
public interface CoursResponseWithFormateur extends Common {
	
}
public interface CoursResponseWithFormation extends Common {
	
}
public interface CoursResponseWithCoursOrdinateurs extends Common {
	
}
public interface CoursResponseWithVideoprojecteur extends Common {
	
}
public interface CoursResponseWithSalle extends Common {
	
}
	
public interface CompetenceFormateurResponseWithDetail extends CompetenceFormateurResponseWithCompetence, CompetenceFormateurResponseWithFormateur{
	
	
}

public interface CompetenceFormateurResponseWithCompetence extends Common {
	
}

public interface CompetenceFormateurResponseWithFormateur extends Common {

}

public interface CompetenceMatiereResponseWithMatiere extends Common {}

public interface CompetenceMatiereResponseWithCompetence extends Common {}

public interface CompetenceMatiereResponseWithDetails extends CompetenceMatiereResponseWithMatiere, CompetenceMatiereResponseWithCompetence {}






public interface UtilisateurResponseWithFormateur extends Common {
	
}

public interface DisponibiliteFormateurResponseWithformateur extends Common {
	
}

public interface UtilisateurResponseWithStagiaire extends Common {
	
}

public interface FormateurResponseWithCours extends Common {
	
}

public interface FormateurResponseWithcompetenceFormateur extends Common {
	
}

public interface FormateurResponseWithdisponibiliteFormateur extends Common {
	
}

public interface FormateurResponseWithutilisateur extends Common {
	
}
	public interface FormationWithPrerequis extends Common {
		
	}
	
	public interface PreRequisWithFormation extends Common {
		
	}
	
	public interface OrdinateurWithCoursOrdinateurs extends Common {
		
	}
	
	public interface CompetenceWithVideoprojecteur extends Common {
		
	}
	
	public interface CompetenceWithSalle extends Common {
		
	}
	
	public interface CompetenceWithFormateur extends Common {
		
	}
	
	public interface CoursWithFormation extends Common {

	}

	public interface FormationWithCours extends Common {

	}
	
	public interface OrdinateurWithCours extends Common {

	}

	public interface CoursWithOrdinateur extends Common {

	}
}
