import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import * as d3 from 'd3';
import { PlanifSalle } from '../../models/planif-salle';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Formation } from '../../models/formation';
import { Salle } from '../../models/salle';
import { CoursService } from '../../services/cours.service';
import { FormationService } from '../../services/formation.service';
import { Cours } from '../../models/cours';
import { SalleService } from '../../services/salle.service';

@Component({
  selector: 'app-planification',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, FormsModule],
  templateUrl: './planification.component.html',
  styleUrl: './planification.component.css',
})
export class PlanificationComponent implements OnInit {
  formations: Formation[] = [];
  formationSelection: Formation = new Formation();
  listCours: Cours[] = [];

  // Liste des salles
  salles: string[] = []; //['Salle 1', 'Salle 2', 'Salle 3'];
  dictionnaireSalle: { [key: string]: number } = {};

  planifSalles: PlanifSalle[] = [];

  private bornesX!: d3.ScaleTime<number, number>;

  decalageJour: number = 0;

  @ViewChild('chart', { static: true }) private chartContainer!: ElementRef;

  private marges = { top: 20, right: 20, bottom: 30, left: 50 };
  private largeur!: number;
  private hauteur!: number;
  private x!: d3.ScaleTime<number, number>;

  dateDebut: Date = new Date('2024-07-01');

  dateDebutString: string = '';
  dateFinString: string = '';

  private borneX!: d3.ScaleTime<number, number>;
  private svg!: d3.Selection<SVGSVGElement, unknown, null, undefined>;
  private g!: d3.Selection<SVGGElement, unknown, null, undefined>;

  constructor(
    private formationSrv: FormationService,
    private coursService: CoursService,
    private salleService: SalleService
  ) {}

  calculDateDebutFin(dateDepart: Date) {
    this.dateDebutString = this.convertiDateString(
      this.trouverLundiProche(dateDepart)
    );

    this.dateFinString = this.ajouterMois(this.dateDebutString, 1);
  }

  private trouverLundiProche(date: Date): Date {
    const jour = date.getDay(); // 0 = Dimanche, 1 = Lundi, ..., 6 = Samedi

    // Calculer la différence en jours pour atteindre le lundi
    const diff = (jour === 0 ? -6 : 1) - jour;

    // Créer une nouvelle date pour le lundi le plus proche
    const lundiProche = new Date(date);
    lundiProche.setDate(date.getDate() + diff);

    return lundiProche;
  }
  ngOnInit(): void {
    this.formationSrv.getAll().subscribe((formations) => {
      this.formations = formations;

      this.salleService.getAll().subscribe((salles) => {
        this.salles = [];
        salles.forEach((salle) => {
          this.salles.push(salle.nom ?? '');
        });
        this.createSallDictionnaire(salles);

        this.calculDateDebutFin(this.dateDebut);
        this.createChart();
      });
    });
  }
  private createSallDictionnaire(salles: Salle[]): void {
    salles.forEach((salle) => {
      let nom_salle: string = salle.nom ?? '';
      this.dictionnaireSalle[nom_salle] = salle.id ?? 0;
    });
  }

  choixFormation(idFormation: number) {
    this.coursService.getCoursByFormationId(idFormation).subscribe((cours) => {
      this.listCours = cours;
      this.updatePlanifSalles();
      this.updateChart();
    });
  }
  /*
  planifSalles: PlanifSalle[] = [
    new PlanifSalle(
      1,
      'Salle 1',
      new Date('2024-07-01'),
      new Date('2024-07-03'),
      'steelblue', // Couleur du rectangle
      'Cours 1',
      true
    ),
    new PlanifSalle(
      2,
      'Salle 2',
      new Date('2024-07-02'),
      new Date('2024-07-04'),
      'green', // Couleur du rectangle
      'Cour 2',
      true
    ),
    new PlanifSalle(
      3,
      'Salle 1',
      new Date('2024-07-04'),
      new Date('2024-07-05'),
      'red', // Couleur du rectangle
      'Cours 3',
      true
    ),

    // Ajoutez d'autres données selon vos besoins
  ];*/

  private updatePlanifSalles() {
    this.planifSalles = this.listCours.map((cours) => {
      return new PlanifSalle(
        cours.id,
        cours.salle?.nom ?? 'distanciel', // Assign a room in a round-robin fashion
        new Date(cours.dateDebut ?? '2024-07-04'),
        new Date(
          this.ajouterJour(
            this.convertiDateString(new Date(cours.dateDebut ?? '2024-07-04')),
            cours.matiere?.duree ?? 0
          )
        ),
        'steelblue', // Assign a default color
        cours.titre,
        true
      );
    });
  }

  private updateChart() {
    // Clear the existing chart

    d3.select(this.chartContainer.nativeElement).select('svg').remove();
    this.g.selectAll('*').remove();

    // Recreate the chart elements
    this.createChart();
  }

  private createChart() {
    const element = this.chartContainer.nativeElement;

    // Calculer la largeur et la hauteur du graphique en tenant compte des marges
    this.largeur = element.offsetWidth - this.marges.left - this.marges.right;
    this.hauteur = element.offsetHeight - this.marges.top - this.marges.bottom;

    // Créer un élément SVG et définir sa taille
    this.svg = d3
      .select(element)
      .append('svg')
      .attr('width', element.offsetWidth)
      .attr('height', element.offsetHeight);

    // Ajouter un groupe <g> pour contenir le graphique avec les marges définies
    this.g = this.svg
      .append('g')
      .attr('transform', `translate(${this.marges.left},${this.marges.top})`);

    // Configurer l'échelle de l'axe X avec une plage de dates fixe
    this.borneX = d3
      .scaleTime()
      .domain([new Date(this.dateDebutString), new Date(this.dateFinString)]) // Plage de dates fixe
      .range([0, this.largeur]);

    // Configurer l'échelle de l'axe Y avec les noms des salles
    const y = d3
      .scaleBand()
      .domain(this.salles)

      .range([0, this.hauteur])
      .padding(0.1);

    // Ajouter l'axe X au groupe <g>, positionné en bas du graphique
    this.g
      .append('g')
      .attr('class', 'axis axis--x')
      .attr('transform', `translate(0,${this.hauteur})`)
      .call(
        d3
          .axisBottom(this.borneX)
          .ticks(d3.timeDay.every(1)) // Afficher un repère pour chaque jour
          .tickFormat((d) => {
            // Vérifier si d est une instance de Date
            const date = d instanceof Date ? d : new Date(d as number);
            return d3.timeFormat('%d/%m')(date); // Formater les repères pour n'afficher que le jour et le mois
          })
      );

    // Ajouter l'axe Y au groupe <g>
    this.g.append('g').attr('class', 'axis axis--y').call(d3.axisLeft(y));

    // Créer les barres représentant l'occupation des salles
    const bars = this.g
      .selectAll('.bar')
      .data(this.planifSalles)
      .enter()
      .append('g')
      .attr('class', 'bar-group');

    bars
      .append('rect')
      .attr('class', 'bar')
      .attr('x', (d) => this.borneX(d.debut ?? new Date()) ?? 0)
      .attr('y', (d) => y(d.nom ?? '') ?? 0) // Utiliser une valeur par défaut pour éviter undefined
      .attr(
        'width',
        (d) =>
          (this.borneX(d.fin ?? new Date()) ?? 0) -
          (this.borneX(d.debut ?? new Date()) ?? 0)
      )
      .attr('height', y.bandwidth())
      .attr('fill', (d) => d.couleur ?? 'steelblue'); // Utiliser la couleur spécifiée

    // Ajouter le texte aux barres
    bars
      .append('text')
      .attr('x', (d) => (this.borneX(d.debut ?? new Date()) ?? 0) + 5) // Positionner le texte à l'intérieur de la barre
      .attr('y', (d) => (y(d.nom ?? '') ?? 0) + y.bandwidth() / 2)
      .attr('dy', '.35em') // Ajuster la position verticale du texte
      .text((d) => d.texte ?? '') // Utiliser le texte spécifié
      .attr('fill', 'white'); // Couleur du texte

    // Ajouter les rectangles pour les week-ends
    this.addWeekendRectangles(y);

    // Rendre les barres déplaçables
    this.rendreDragable(bars, this.borneX, y);
  }

  // Ajouter des rectangles pour les week-ends
  private addWeekendRectangles(y: d3.ScaleBand<string>) {
    const weekends = this.generateWeekendData();
    this.g
      .selectAll('.weekend')
      .data(weekends)
      .enter()
      .append('rect')
      .attr('class', 'weekend')
      .attr('x', (d) => this.borneX(d.debut) ?? 0)
      .attr('y', 0) // Couvrir toute la hauteur
      .attr(
        'width',
        (d) => (this.borneX(d.fin) ?? 0) - (this.borneX(d.debut) ?? 0)
      )
      .attr('height', this.hauteur)
      .attr('fill', 'black')
      .attr('opacity', 0.1); // Ajuster l'opacité pour rendre les rectangles moins intrusifs
  }

  // Générer des données pour les week-ends
  private generateWeekendData(): { debut: Date; fin: Date }[] {
    const weekends = [];
    let currentDate = new Date(this.dateDebutString);
    const endDate = new Date(this.dateFinString);

    while (currentDate <= endDate) {
      let numJour = currentDate.getDay() + this.decalageJour;
      if (numJour > 7) numJour = numJour - 7;
      if (numJour === 6) {
        const samedi = new Date(currentDate);
        const dimanche = new Date(currentDate);
        dimanche.setDate(samedi.getDate() + 2);
        weekends.push({ debut: samedi, fin: dimanche });
      }
      currentDate.setDate(currentDate.getDate() + 1);
    }
    return weekends;
  }

  public semaineSuivante() {
    this.dateDebutString = this.ajouterJour(this.dateDebutString, 7);
    this.dateFinString = this.ajouterJour(this.dateFinString, 7);
    this.ChangementBorne();
  }

  public semainePrecedente() {
    this.dateDebutString = this.ajouterJour(this.dateDebutString, -7);

    this.dateFinString = this.ajouterJour(this.dateFinString, -7);
    this.ChangementBorne();
  }

  ajouterJour(dateAChanger: string, nbJour: number): string {
    return this.convertiDateString(
      new Date(new Date(dateAChanger).getTime() + nbJour * 24 * 60 * 60 * 1000)
    );
  }

  private convertiDateString(date: Date): string {
    return date.toISOString().split('T')[0];
  }

  public ajouterMois(dateAChanger: string, nbMois: number): string {
    const dateDate: Date = new Date(dateAChanger);
    dateDate.setMonth(dateDate.getMonth() + 1);
    return this.convertiDateString(dateDate);
  }

  ChangementBorneDebut() {
    // this.dateFinString = this.ajouterMois(this.dateDebutString, 1);

    this.calculDateDebutFin(new Date(this.dateDebutString));

    this.ChangementBorne();
  }

  ChangementBorneFin() {
    this.dateDebutString = this.ajouterMois(this.dateFinString, -1);
    this.calculDateDebutFin(new Date(this.dateDebutString));
    this.ChangementBorne();
  }

  ChangementBorne() {
    this.borneX.domain([
      new Date(this.dateDebutString),
      new Date(this.dateFinString),
    ]);

    // Mettre à jour l'axe X
    (
      this.g.select('.axis--x') as d3.Selection<
        SVGGElement,
        unknown,
        null,
        undefined
      >
    ).call(
      d3
        .axisBottom(this.borneX)
        .ticks(d3.timeDay.every(1))
        .tickFormat((d) => {
          const date = d instanceof Date ? d : new Date(d as number);
          return d3.timeFormat('%d/%m')(date);
        })
    );

    // Redessiner les barres avec le nouveau domaine
    this.redessineBarres(
      this.g.selectAll('.bar-group'),
      this.borneX,
      d3
        .scaleBand<string>()
        .domain(this.salles)
        .range([0, this.hauteur])
        .padding(0.1)
    );
  }

  private rendreDragable(
    bars: d3.Selection<SVGGElement, PlanifSalle, SVGGElement, any>,
    x: d3.ScaleTime<number, number>,
    y: d3.ScaleBand<string>
  ) {
    const drag = d3
      .drag<SVGGElement, PlanifSalle>()
      .on(
        'start',
        (
          event: d3.D3DragEvent<SVGGElement, PlanifSalle, any>,
          d: PlanifSalle
        ) => {
          if (!d.estDragable) return;

          // Ajouter une classe et changer la couleur de la barre lorsqu'elle est déplacée
          d3.select(event.sourceEvent.target).classed('dragging', true);
          // .attr('fill', 'orange');
        }
      )
      .on(
        'drag',
        (
          event: d3.D3DragEvent<SVGGElement, PlanifSalle, any>,
          d: PlanifSalle
        ) => {
          if (!d.estDragable) return;

          // Déplacement horizontal (arrondi au jour le plus proche)
          let newX = x.invert(event.x - this.marges.left);
          newX = this.arrondiAuJour(newX);
          const duration = d.fin!.getTime() - d.debut!.getTime();
          d.debut = newX;
          d.fin = new Date(newX.getTime() + duration);

          // Déplacement vertical
          const newY = y.domain().find((nom) => {
            const bandY = y(nom) ?? 0;
            return (
              event.y - this.marges.top >= bandY &&
              event.y - this.marges.top < bandY + y.bandwidth()
            );
          });

          if (newY && newY !== d.nom) {
            d.nom = newY;
          }

          this.redessineBarres(bars, x, y);
        }
      )
      .on(
        'end',
        (
          event: d3.D3DragEvent<SVGGElement, PlanifSalle, any>,
          d: PlanifSalle
        ) => {
          if (!d.estDragable) return;

          d3.select(event.sourceEvent.target).classed('dragging', false);

          //   d.fin = this.ajouterJourDate(d.fin, 1);

          this.coursService.getById(d.id ?? 0).subscribe((cours) => {
            const newDateDebut = new Date(d.debut ?? new Date());
            newDateDebut.setDate(newDateDebut.getDate() + 1);
            cours.dateDebut = newDateDebut;
            const nom_salle: string = d.nom ?? '';
            if (!cours.salle) cours.salle = new Salle();
            cours.salle.id = this.dictionnaireSalle[nom_salle];

            this.coursService.update(cours).subscribe((cours_retour) => {
              console.log('sauuvegarde demandée');
              console.log(cours_retour);
            });
          });
        }
      );
    this.redessineBarres(bars, x, y);
    bars.call(drag);
  }

  private ajouterJourDate(date: Date | undefined, nbJour: number): Date {
    if (!date) return new Date();
    const newDate = new Date(date);
    newDate.setDate(newDate.getDate() + nbJour);
    return newDate;
  }

  // Arrondir une date à la date entière la plus proche
  private arrondiAuJour(date: Date): Date {
    const roundedDate = new Date(date);
    roundedDate.setHours(0, 0, 0, 0);
    return roundedDate;
  }

  // Mettre à jour les positions des barres après déplacement
  private redessineBarres(
    bars: d3.Selection<SVGGElement, PlanifSalle, SVGGElement, any>,
    x: d3.ScaleTime<number, number>,
    y: d3.ScaleBand<string>
  ) {
    bars
      .select('rect')
      .attr('x', (d) => x(d.debut ?? new Date()) ?? 0)
      .attr('y', (d) => y(d.nom ?? '') ?? 0)
      .attr(
        'width',
        (d) => (x(d.fin ?? new Date()) ?? 0) - (x(d.debut ?? new Date()) ?? 0)
      )
      .attr('height', y.bandwidth());

    bars
      .select('text')
      .attr('x', (d) => (x(d.debut ?? new Date()) ?? 0) + 5)
      .attr('y', (d) => (y(d.nom ?? '') ?? 0) + y.bandwidth() / 2);
  }
}
