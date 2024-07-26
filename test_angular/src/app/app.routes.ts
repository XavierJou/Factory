import { isLoggedGuard } from './guards/isLogged.guard';
import { Routes } from '@angular/router';
import { LayoutComponent } from './pages/layout/layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { FormateurComponent } from './components/formateur/formateur/formateur.component';
import { FormationComponent } from './components/formation/formation/formation.component';
import { StagiaireComponent } from './components/stagiaire/stagiaire/stagiaire.component';
import { UtilisateurComponent } from './components/utilisateur/utilisateur.component';
import { VideoprojecteurComponent } from './components/videoprojecteur/videoprojecteur/videoprojecteur.component';
import { OrdinateurComponent } from './components/ordinateur/ordinateur/ordinateur.component';
import { CompetenceComponent } from './components/competence/competence/competence.component';
import { EditCompetenceComponent } from './components/competence/edit-competence/edit-competence.component';
import { EditFormateurComponent } from './components/formateur/edit-formateur/edit-formateur.component';
import { EditFormationComponent } from './components/formation/edit-formation/edit-formation.component';
import { MatiereComponent } from './components/matiere/matiere/matiere.component';
import { EditMatiereComponent } from './components/matiere/edit-matiere/edit-matiere.component';
import { EditOrdinateurComponent } from './components/ordinateur/edit-ordinateur/edit-ordinateur.component';
import { PrerequisComponent } from './components/prerequis/prerequis/prerequis.component';
import { EditPrerequisComponent } from './components/prerequis/edit-prerequis/edit-prerequis.component';
import { SalleComponent } from './components/salle/salle/salle.component';
import { EditSalleComponent } from './components/salle/edit-salle/edit-salle.component';
import { EditStagiaireComponent } from './components/stagiaire/edit-stagiaire/edit-stagiaire.component';
import { EditVideoprojecteurComponent } from './components/videoprojecteur/edit-videoprojecteur/edit-videoprojecteur.component';
import { CoursComponent } from './components/cours/cours/cours.component';
import { EditCoursComponent } from './components/cours/edit-cours/edit-cours.component';
import { InscriptionComponent } from './components/inscription/inscription.component';
import { LoginComponent } from './components/login/login.component';
import { PlanificationComponent } from './components/planification/planification.component';
import { EditUtilisateurComponent } from './components/utlisateur/edit-utilisateur/edit-utilisateur.component';
import { CoursFormationComponent } from './components/cours/cours-formation/cours-formation.component';
import { PrerequisFormationComponent } from './components/prerequis/prerequis-formation/prerequis-formation.component';
import { MatieresFormationComponent } from './components/matiere/matiere-formation/matiere-formation.component';
import { FormateursFormationComponent } from './components/formateur/formateur-formation/formateur-formation.component';
import { StagiairesFormationComponent } from './components/stagiaire/stagiaire-formation/stagiaire-formation.component';
import { FormationDetailComponent } from './components/formation/formation-detail/formation-detail.component';
import { authGuard } from './guards/auth.guard';
import { NosFormationComponent } from './components/formation/nos-formations/nos-formation.component';

export const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    data: { animation: 'HomePage' },
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [isLoggedGuard],
    data: { animation: 'LoginPage' },
  },
  {
    path: 'inscription',
    component: InscriptionComponent,
    data: { animation: 'InscriptionPage' },
  },
  {
    path: 'nos-formations',
    component: NosFormationComponent,
  },
  {
    path: '',
    component: LayoutComponent,
    data: { animation: 'LayoutPage' },
    canActivate: [authGuard],
    children: [
      {
        path: 'planning',
        component: PlanificationComponent,
      },
      {
        path: 'planning2',
        component: PlanificationComponent,
      },
      {
        path: 'dashboard',
        component: DashboardComponent,
      },
      {
        path: 'utilisateurs/edit/:id',
        component: EditUtilisateurComponent,
      },
      {
        path: 'utilisateurs',
        component: UtilisateurComponent,
      },
      {
        path: 'competence',
        component: CompetenceComponent,
      },
      {
        path: 'competence/edit',
        component: EditCompetenceComponent,
      },
      {
        path: 'competence/edit/:id',
        component: EditCompetenceComponent,
      },
      {
        path: 'cours',
        component: CoursComponent,
      },
      {
        path: 'cours/edit',
        component: EditCoursComponent,
      },
      {
        path: 'cours/edit/:id',
        component: EditCoursComponent,
      },
      {
        path: 'formateurs',
        component: FormateurComponent,
      },
      {
        path: 'formateur/edit',
        component: EditFormateurComponent,
      },
      {
        path: 'formateur/edit/:id',
        component: EditFormateurComponent,
      },
      {
        path: 'formations',
        component: FormationComponent,
      },
      {
        path: 'formation/:id',
        component: FormationDetailComponent,
      },
      {
        path: 'formations/edit',
        component: EditFormationComponent,
      },
      {
        path: 'formations/edit/:id',
        component: EditFormationComponent,
      },
      {
        path: 'formation/:id/cours',
        component: CoursFormationComponent,
      },
      {
        path: 'formation/:id/matieres',
        component: MatieresFormationComponent,
      },
      {
        path: 'formation/:id/formateurs',
        component: FormateursFormationComponent,
      },
      {
        path: 'formation/:id/stagiaires',
        component: StagiairesFormationComponent,
      },
      {
        path: 'formation/:id/prerequis',
        component: PrerequisFormationComponent,
      },
      {
        path: 'matieres',
        component: MatiereComponent,
      },
      {
        path: 'matiere/edit',
        component: EditMatiereComponent,
      },
      {
        path: 'matiere/edit/:id',
        component: EditMatiereComponent,
      },
      {
        path: 'ordinateur',
        component: OrdinateurComponent,
      },
      {
        path: 'ordinateur/edit',
        component: EditOrdinateurComponent,
      },
      {
        path: 'ordinateur/edit/:id',
        component: EditOrdinateurComponent,
      },
      {
        path: 'prerequis',
        component: PrerequisComponent,
      },
      {
        path: 'prerequis/edit',
        component: EditPrerequisComponent,
      },
      {
        path: 'prerequis/edit/:id',
        component: EditPrerequisComponent,
      },
      {
        path: 'salle',
        component: SalleComponent,
      },
      {
        path: 'salle/edit',
        component: EditSalleComponent,
      },
      {
        path: 'salle/edit/:id',
        component: EditSalleComponent,
      },
      {
        path: 'stagiaires',
        component: StagiaireComponent,
      },
      {
        path: 'stagiaire/edit',
        component: EditStagiaireComponent,
      },
      {
        path: 'stagiaire/edit/:id',
        component: EditStagiaireComponent,
      },
      {
        path: 'utilisateur',
        component: UtilisateurComponent,
      },
      {
        path: 'videoprojecteur',
        component: VideoprojecteurComponent,
      },
      {
        path: 'videoprojecteur/edit',
        component: EditVideoprojecteurComponent,
      },
      {
        path: 'videoprojecteur/edit/:id',
        component: EditVideoprojecteurComponent,
      },
    ],
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full',
    data: { animation: 'Page' },
  },
];
