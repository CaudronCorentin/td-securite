import {inject, Injectable, signal} from '@angular/core';
import {HttpClient, httpResource} from '@angular/common/http';
import {API, Compte} from '../api/banque';
import {firstValueFrom} from 'rxjs';

export interface Utilisateur {
  readonly login: string;
  readonly roles: readonly string[];
  readonly connecte: boolean;
}

/**
 * Garde l'utilisateur connecté. Squelette du TD : à compléter en partie 2.
 */
@Injectable({ providedIn: 'root' })
export class AuthService {
  readonly utilisateur = signal<Utilisateur | null>(null);
  private readonly http = inject(HttpClient);
  private login?: string;
  private password?: string;
  public connected: boolean = false;
  private identifiants: string|undefined;


  /**
   * Vérifie les identifiants auprès du serveur (GET /api/me avec l'en-tête
   * Basic) et mémorise l'utilisateur. Doit échouer (throw) si le serveur répond 401.
   */
  async connecter(login: string, motDePasse: string): Promise<void> {
    this.identifiants = btoa(`${login}:${motDePasse}`);
    try {
      this.utilisateur.set(await firstValueFrom(this.http.get<Utilisateur>(`${API}/me`)));
      this.connected = true;
    } catch (e) {
      this.deconnecter();
      this.connected = false;
      throw e;
    }
  }

  deconnecter(): void {
    this.identifiants = undefined;
    this.utilisateur.set(null);
  }

  basic():string | undefined {
    return this.identifiants;
  }

  estConnecte():boolean{
    return this.connected;

  }
}
