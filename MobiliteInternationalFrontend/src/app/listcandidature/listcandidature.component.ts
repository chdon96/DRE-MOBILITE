import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CandidatureService } from '../candidature.service';

@Component({
  selector: 'app-listcandidature',
  templateUrl: './listcandidature.component.html',
  styleUrls: ['./listcandidature.component.css']
})
export class ListcandidatureComponent implements OnInit {
  candidatures: any[];
  constructor(
    private service : CandidatureService,
    private router:Router
  ) {}
  public imageURL: string;
  file: File | null = null;
  message: any;
 
  ngOnInit(): void {
    this.GetCandidatures();
    };

  
  GetCandidatures(){
     this.service.ShowAllCandidature().subscribe(data => {
      this.candidatures = data as any[];

    });

  }

  }