import { Component, OnInit } from '@angular/core';
import { OffreService } from '../offre.service';
import { ActivatedRoute } from '@angular/router';
import { offre } from '../core/model/offre';
import { Location } from '@angular/common';

@Component({
  selector: 'app-offre',
  templateUrl: './offre.component.html',
  styleUrls: ['./offre.component.css']
})
export class OffreComponent implements OnInit {
  file: File | null = null;
  titre: string = '';
  description: string = '';
  lieu: string = '';
  deadline: string = '';
  nombreplaces: number = 0;
  link: string = '';
offre = new offre(0,'','','', new Date(),0,'','')
  constructor(private route: ActivatedRoute,private offreService: OffreService,private location: Location) {
  
  }
  ngOnInit(): void {

  }
  handleFileInput(event: any) {
    this.file = event.target.files[0];
  }

  submitForm() {
    const dateString = new Date(this.deadline).toISOString().split('T')[0];

    this.offreService
      .SaveOffre(this.file, this.titre, this.description, this.lieu, dateString, this.nombreplaces, this.link)
      .subscribe(
        (offre) => {
          // Handle success response
          console.log('Offre saved:', offre);
        },
        (error) => {
          // Handle error
          console.error('Error saving offre:', error);
        }
      );
      this.location.replaceState('/user/Home');
      location.reload();
  }

}