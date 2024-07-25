import { Component } from '@angular/core';
import {
  ActivatedRoute,
  ChildrenOutletContexts,
  RouterOutlet,
} from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { slideInAnimation } from './animations';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MenuComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  animations: [slideInAnimation],
})
export class AppComponent {
  title = 'factory-angular';

  constructor(protected route: ActivatedRoute) {}
}
