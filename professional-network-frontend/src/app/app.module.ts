import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PagesModule } from './pages/pages.module';
import { ExperiencesPageModule } from './pages/experiences-page/experiences-page.module';
import { ExperiencesService } from './services/experiences.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    PagesModule,
    ExperiencesPageModule,
    HttpClientModule
  ],
  providers: [ExperiencesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
