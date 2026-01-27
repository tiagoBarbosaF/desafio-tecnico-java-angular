import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CreditoModel} from '../models/credito.model';

@Injectable({
  providedIn: 'root'
})
export class CreditoService {

  private readonly apiUrl = 'http://localhost:8080/api/creditos';

  constructor(private http: HttpClient) {}

  buscarPorNfse(numeroNfse: string): Observable<CreditoModel[]> {
    return this.http.get<CreditoModel[]>(`${this.apiUrl}/${numeroNfse}`);
  }

  buscarPorNumeroCredito(numeroCredito: string): Observable<CreditoModel> {
    return this.http.get<CreditoModel>(`${this.apiUrl}/credito/${numeroCredito}`);
  }
}
