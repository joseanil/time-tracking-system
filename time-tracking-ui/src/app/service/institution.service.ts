import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Institution } from '../model/institution';
import { MessageService } from '../service/message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class InstitutionService {

  private institutionsUrl = 'http://localhost:8080/institutions';

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }


  getAll(): Observable<Institution[]> {
    return this.http.get<Institution[]>(this.institutionsUrl)
      .pipe(
        tap(_ => this.log('Fetched institutions')),
        catchError(this.handleError('getAllInstitutions', []))
      );
  }

  add(institution: Institution): Observable<Institution> {
    return this.http.post<Institution>(this.institutionsUrl, institution, httpOptions).pipe(
      tap(_ => this.log(`Added Institution Successfully`)),
      catchError(this.handleError<Institution>('InstitutionService.add'))
    );
  }

  delete(institution: Institution | number): Observable<Institution> {
    const id = typeof institution === 'number' ? institution : institution.id;
    const url = `${this.institutionsUrl}/${id}`;
    return this.http.delete<Institution>(url, httpOptions).pipe(
      tap(_ => this.log(`Deleted Institution w/ id=${id} Successfully`)),
      catchError(this.handleError<Institution>('InstitutionService.delete'))
    );
  }

  update(institution: Institution): Observable<any> {
    const url = `${this.institutionsUrl}/${institution.id}`;

    return this.http.put(url, institution, httpOptions).pipe(
      tap(_ => this.log(`Updated Institution w/ id=${institution.id} Successfully`)),
      catchError(this.handleError<any>('InstitutionService.update'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string): void {
    this.messageService.add(`Institution Service: ${message}`);
  }

}
