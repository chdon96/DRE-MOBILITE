import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClient, HttpClientModule } from '@angular/common/http';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AllTemplateAdminComponent } from 'src/backOffice/all-template-admin/all-template-admin.component';
import { BodyAdminComponent } from 'src/backOffice/body-admin/body-admin.component';
import { FooterAdminComponent } from 'src/backOffice/footer-admin/footer-admin.component';
import { HeaderAdminComponent } from 'src/backOffice/header-admin/header-admin.component';
import { SideAdminComponent } from 'src/backOffice/side-admin/side-admin.component';
import { AllTemplateUserComponent } from 'src/frontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from 'src/frontOffice/body-user/body-user.component';
import { FooterUserComponent } from 'src/frontOffice/footer-user/footer-user.component';
import { HeaderUserComponent } from 'src/frontOffice/header-user/header-user.component';
import { OffreComponent } from './offre/offre.component';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { ListOfferComponent } from './list-offer/list-offer.component';
import { CandidatureFormComponent } from './candidature-form/candidature-form.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { NewPasswordComponent } from './new-password/new-password.component';
import { TopConidatureComponent } from './top-conidature/top-conidature.component';
import { ListcandidatureComponent } from './listcandidature/listcandidature.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderAdminComponent,
    FooterAdminComponent,
    SideAdminComponent,
    AllTemplateAdminComponent,
    BodyAdminComponent,
    HeaderUserComponent,
    BodyUserComponent,
    FooterUserComponent,
    AllTemplateUserComponent,
    SigninComponent,
    SignupComponent,
    OffreComponent,
    HomeComponent,
    ListOfferComponent,
    CandidatureFormComponent,
    ResetPasswordComponent,
    NewPasswordComponent,
    TopConidatureComponent,
    ListcandidatureComponent,
    
    
 

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatCardModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
