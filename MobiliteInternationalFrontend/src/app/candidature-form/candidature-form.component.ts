import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { offre } from '../core/model/offre';
import { OffreService } from '../offre.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CandidatureService } from '../candidature.service';
@Component({
  selector: 'app-candidature-form',
  templateUrl: './candidature-form.component.html',
  styleUrls: ['./candidature-form.component.css']
})
export class CandidatureFormComponent implements OnInit {
  selectedOffer: offre;
  candidatureData: any = {};
  offres: any[];

  constructor(
    private route: ActivatedRoute,
    private offreService: OffreService,
    private service:CandidatureService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params: Params) => {
      const idOffre = +params['idOffre'];
      console.log(idOffre) // Convert offerId to a number using the '+' operator
      this.getOfferById(idOffre);
    });
  }
   processImages(): void {
    for (const offre of this.offres) {
      offre.image = 'data:image/jpeg;base64,' + offre.image;
    }
  }

  getOfferById(offerId: number) {
    this.offreService.getOfferById(offerId).subscribe(
      (offer: offre) => {
        this.selectedOffer = offer;
        this.processImages();

      },
      (error) => {
        console.error('Error fetching offer by ID:', error);
      }
    );
    this.processImages();

  }

  submitCandidatureForm() {
    if (!this.selectedOffer) {
      console.error(' Cannot submit candidature without an offer.');
      return;
    }

    const candidature = {
      ...this.selectedOffer,
      ...this.candidatureData
    };

   /* this.offreService.addCandidature(candidature).subscribe(
      (response) => {
        // Handle the successful response from the server if needed
        console.log('Candidature added successfully:', response);
        console.log(this.selectedOffer);
        // Optionally, you can redirect the user to a success page or perform other actions.
      },
      (error) => {
        // Handle the error response from the server if needed
        console.error('Error adding candidature:', error);
      }
    );
  }*/
  this.service.SubmitApplication(this.selectedOffer.idOffre,candidature).subscribe(
    (response) => {
      // Handle the successful response from the server if needed
      console.log('Candidature added successfully:', response);
      console.log(this.selectedOffer);
      // Optionally, you can redirect the user to a success page or perform other actions.
    },
    (error) => {
      // Handle the error response from the server if needed
      console.error('Error adding candidature:', error);
    }
  );
}
}
