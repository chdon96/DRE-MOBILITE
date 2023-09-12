import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { CandidatureService } from '../candidature.service';
import { offre } from '../core/model/offre';
import { OffreService } from '../offre.service';
import { Candidature } from '../core/model/Candidature';

@Component({
  selector: 'app-top-conidature',
  templateUrl: './top-conidature.component.html',
  styleUrls: ['./top-conidature.component.css']
})
export class TopConidatureComponent   implements OnInit {
  //topCandidature: Candidature | null = null; // Initialize topCandidature as null or an appropriate default value
  loading = false;
  error = '';
  topCandidatures: Candidature[] = []; // Initialize topCandidature as an empty array
  transportlist: Candidature[] = [];

  constructor(
    private route: ActivatedRoute,
    private service: CandidatureService // Replace 'YourService' with the name of your service containing GetTopCandidature
  ) { }

  
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const idOffre = Number(params.get('idOffre'));
      if (!isNaN(idOffre)) {
        this.getTopCandidature(idOffre);
      } else {
        this.error = 'Invalid idOffre parameter.';
      }
    });
  }

  getTopCandidature(idOffre: number){
    this.service.GetTopCandidature(idOffre).subscribe((data) => {
      this.topCandidatures = data as unknown as Candidature[];
    },
      (error: any) => {
        this.error = 'Failed to fetch top candidature.';
        this.loading = false;
      }
    );
  }
  /*search(idOffre:number){
    this.service.GetTopCandidature(idOffre).subscribe((data) => {
      this.topCandidatures = data as unknown as Candidature[];
    });
   }
*/
}
