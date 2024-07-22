import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import * as d3 from 'd3';
import { PlanifSalle } from '../../models/planif-salle';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-planification',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './planification.component.html',
  styleUrl: './planification.component.css',
})
export class PlanificationComponent implements OnInit {
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

    new PlanifSalle(
      4,
      'Salle 1',
      new Date('2024-07-06'),
      new Date('2024-07-07'),
      'black', // Couleur du rectangle
      '',
      false
    ),

    // Ajoutez d'autres données selon vos besoins
  ];

  // Liste des salles
  salles: string[] = ['Salle 1', 'Salle 2', 'Salle 3'];
  private bornesX!: d3.ScaleTime<number, number>;

  @ViewChild('chart', { static: true }) private chartContainer!: ElementRef;

  private marges = { top: 20, right: 20, bottom: 30, left: 50 };
  private largeur!: number;
  private hauteur!: number;
  private x!: d3.ScaleTime<number, number>;
  dateDebut: string = this.convertiDateString(new Date('2024-07-01'));
  dateFin: string = this.convertiDateString(new Date('2024-07-31'));
  private borneX!: d3.ScaleTime<number, number>;
  private svg!: d3.Selection<SVGSVGElement, unknown, null, undefined>;
  private g!: d3.Selection<SVGGElement, unknown, null, undefined>;

  ngOnInit(): void {
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
      .domain([new Date(this.dateDebut), new Date(this.dateFin)]) // Plage de dates fixe
      .range([0, this.largeur]);

    // Configurer l'échelle de l'axe Y avec les noms des salles
    const y = d3
      .scaleBand()
      .domain(this.salles)
      /*
      .domain(
        this.planifSalles
          .map((d) => d.nom!)
          .filter((nom): nom is string => nom !== undefined)
      )*/
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

    // Rendre les barres déplaçables
    this.rendreDragable(bars, this.borneX, y);
  }

  public semaineSuivante() {
    this.dateDebut = this.ajouterJour(this.dateDebut, 7);
    this.dateFin = this.ajouterJour(this.dateFin, 7);
    this.ChangementBorne();
  }

  public semainePrecedente() {
    this.dateDebut = this.ajouterJour(this.dateDebut, -7);

    this.dateFin = this.ajouterJour(this.dateFin, -7);
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
    this.dateFin = this.ajouterMois(this.dateDebut, 1);
    this.ChangementBorne();
  }

  ChangementBorneFin() {
    this.dateDebut = this.ajouterMois(this.dateFin, -1);
    this.ChangementBorne();
  }

  ChangementBorne() {
    this.borneX.domain([new Date(this.dateDebut), new Date(this.dateFin)]);

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

  // Rendre les barres déplaçables avec D3 Drag
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
          d3.select(event.sourceEvent.target.parentElement)
            .classed('dragging', true)
            .select('rect');
          d.couleur = 'orange';
        }
      )
      .on(
        'drag',
        (
          event: d3.D3DragEvent<SVGGElement, PlanifSalle, any>,
          d: PlanifSalle
        ) => {
          // Mettre à jour la position de la barre pendant le déplacement
          if (!d.estDragable) return;

          // Déplacement horizontal (arrondi au jour le plus proche)
          let newX = x.invert(event.x - this.marges.left);
          newX = this.arrondiAuJour(newX);
          const duration = d.fin!.getTime() - d.debut!.getTime();
          d.debut = newX;
          d.fin = new Date(newX.getTime() + duration);

          // Déplacement vertical
          const newY = y.domain().find((nom, index) => {
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
          // Retirer la classe et restaurer la couleur de la barre après le déplacement
          if (!d.estDragable) return;

          d3.select(event.sourceEvent.target.parentElement)
            .classed('dragging', false)
            .select('rect');
          //  .attr('fill', d.couleur ?? 'steelblue');

          // d.fin = new Date(d.fin!.getTime() + 24 * 60 * 60 * 1000);
          this.redessineBarres(bars, x, y);
        }
      );

    bars.call(drag);
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
