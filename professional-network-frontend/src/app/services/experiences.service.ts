import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Experience } from './models/experience.model';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ExperiencesService {
    constructor(private httpClient: HttpClient) { }

    getExperiencesByCandidateId(id: string): Observable<Experience[]> {
        return this.httpClient.get<Experience>(environment.backend_url + 'candidate/getExperience?id=' + id).pipe(
            map(exp => {
                return [exp];
            })
        )
        // return of([
        //     {
        //         'designation': 'web development'
        //     }
        // ] as Experience[])
    }

}