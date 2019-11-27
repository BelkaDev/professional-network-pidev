import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExperiencesPageComponent } from './experiences-page/experiences-page.component';

@NgModule({
  declarations: [ExperiencesPageComponent],
  imports: [
    CommonModule
  ],
  exports: [ExperiencesPageComponent]
})
export class ExperiencesPageModule { }
