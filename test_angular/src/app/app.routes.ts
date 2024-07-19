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

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: 'inscription', component: InscriptionComponent },
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent,
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
        path: 'formateur',
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
        path: 'formation',
        component: FormationComponent,
      },
      {
        path: 'formation/edit',
        component: EditFormationComponent,
      },
      {
        path: 'formation/edit/:id',
        component: EditFormationComponent,
      },
      {
        path: 'matiere',
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
        path: 'stagiaire',
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
];
