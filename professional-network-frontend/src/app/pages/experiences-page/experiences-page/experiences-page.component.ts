import { Component, OnInit } from '@angular/core';
import { ExperiencesService } from 'src/app/services/experiences.service';
import { Experience } from 'src/app/services/models/experience.model';

@Component({
  selector: 'app-experiences-page',
  templateUrl: './experiences-page.component.html',
  styleUrls: ['./experiences-page.component.scss']
})
export class ExperiencesPageComponent implements OnInit {

  experiences: Experience[] = [];

  constructor(private experiencesService: ExperiencesService) { }

  ngOnInit() {
    this.experiencesService.getExperiencesByCandidateId('1').subscribe(exps => {
      this.experiences = exps;
    });
  }

}
