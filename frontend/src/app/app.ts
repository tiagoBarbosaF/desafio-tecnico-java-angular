import {Component, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {CreditoModel} from './models/credito.model';
import {CreditoService} from './services/credito.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
  providers: [CreditoService]
})
export class App {
  creditos = signal<CreditoModel[]>([]);

  constructor(private creditoService: CreditoService) {}

  buscarPorNfse(valor: string): void {
    this.creditoService.buscarPorNfse(valor).subscribe({
      next: (response) => {
        this.creditos.set(response);
      },
      error: () => {
        this.creditos.set([]);
      }
    });
  }

  buscarPorCredito(valor: string): void {
    this.creditoService.buscarPorNumeroCredito(valor).subscribe({
      next: (response) => {
        this.creditos.set([response]);
      },
      error: () => {
        this.creditos.set([]);
      }
    });
  }
}
