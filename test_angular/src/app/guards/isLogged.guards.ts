import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const isLoggedGuard: CanActivateFn = (route, state) => {
  if (localStorage.getItem('token') != null) {
    return inject(Router).createUrlTree(['/dashboard']);
  }
  return true;
};
