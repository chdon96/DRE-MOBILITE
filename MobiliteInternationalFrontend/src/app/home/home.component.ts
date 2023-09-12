import { Component,OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { OffreService } from '../offre.service';
import { OfferUpdateService } from '../offer-update.service';
import { offre } from '../core/model/offre';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  offres: any[];
  constructor(
    private service : OffreService,
    private location: Location,
    private router: Router,
    private http: HttpClient,
    private sanitizer: DomSanitizer,
    private offerUpdateService: OfferUpdateService
  ) {}
  public imageURL: string;
  file: File | null = null;
  message: any;
 
  ngOnInit(): void {
    this.getOffers();
    };
 processImages(): void {
    for (const offre of this.offres) {
      offre.image = 'data:image/jpeg;base64,' + offre.image;
    }
  }
  
  getOffers(){
     this.service.FindOffers().subscribe(data => {
      this.offres = data as any[];
      this.processImages();
    });
    /*this.service.FindOffers().subscribe((data) => {
      this.Offrelist = data as offre[];
    });*/
  }

  deleteoffers(id: number) {
    this.service.Delete(id).subscribe(
      response => {
        console.log("Offer deleted successfully.");
        this.getOffers();
      },
      error => {
        console.log("Error while deleting Offer:", error);
      }
    );
 
}

/*updateOffer(id: number) {
  // Use the OfferUpdateService to handle the update logic
  this.offerUpdateService.updateOffer(id).subscribe(
    response => {
      console.log("Offer updated successfully.");
      // If needed, you can redirect to another page or update the offer list here
    },
    error => {
      console.log("Error while updating Offer:", error);
    }
  );
}
*/
redirectToTopCandidatures(selectedOffer: offre) {
  // Redirect to the candidature list and pass the selected offer's information as a query parameter
  this.router.navigate(['/user/TopCandidature'], { queryParams: { idOffre: selectedOffer.idOffre } });
}

}
  