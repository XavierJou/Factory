import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  if (localStorage.getItem('token') != null) {
    req = req.clone({
      headers: req.headers.append(
        'Authorization',
        'Basic ' + localStorage.getItem('token')
      ),
    });
  }
  return next(req);
};
