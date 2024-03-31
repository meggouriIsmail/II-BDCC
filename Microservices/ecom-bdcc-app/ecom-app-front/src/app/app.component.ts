import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'ecom-app-front';
  profile: any;

  constructor(public keycloakService : KeycloakService) {

  }
  ngOnInit(): void {
    if(this.keycloakService.isLoggedIn()) {
      this.profile = this.keycloakService.loadUserProfile().then(profile => {
        this.profile = profile;
      })
    }
  }

  async handleLogin() {
    await this.keycloakService.login({
      redirectUri: window.location.origin
    })
  };

  handleLogout() {
    this.keycloakService.logout(window.location.origin)
  };
}
