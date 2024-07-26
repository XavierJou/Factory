import {
  animate,
  animateChild,
  group,
  query,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';

export const slideInAnimation = trigger('routeAnimations', [
  transition('* <=> *', [
    state(
      '*',
      style({
        top: 0,
        right: 0,
        width: '100%',
        bottom: 0,
      })
    ),

    transition(':enter', [
      style({
        right: '-100%',
      }),
      animate(
        '.5s ease-in-out',
        style({
          right: 0,
        })
      ),
    ]),

    transition(':leave', [
      animate(
        '.5s ease-in-out',
        style({
          right: '-100%',
        })
      ),
    ]),
  ]),
]);
