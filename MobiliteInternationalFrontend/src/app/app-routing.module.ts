import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllTemplateAdminComponent } from 'src/backOffice/all-template-admin/all-template-admin.component';
import { BodyAdminComponent } from 'src/backOffice/body-admin/body-admin.component';
import { AllTemplateUserComponent } from 'src/frontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from 'src/frontOffice/body-user/body-user.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { MailconfirmationComponent } from './core/mailconfirmation/mailconfirmation.component';
import { OffreComponent } from './offre/offre.component';
import { HomeComponent } from './home/home.component';
import { CandidatureFormComponent } from './candidature-form/candidature-form.component';
import { ListOfferComponent } from './list-offer/list-offer.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { NewPasswordComponent } from './new-password/new-password.component';
import { TopConidatureComponent } from './top-conidature/top-conidature.component';
import { ListcandidatureComponent } from './listcandidature/listcandidature.component';

const routes: Routes = [
  // { path: "", redirectTo: "admin", pathMatch: "full" },
  { path: 'Signin', component: SigninComponent },
  { path: 'SignUp', component: SignupComponent },
  { path: 'reset-password', component: ResetPasswordComponent },
  { path: 'new-password', component: NewPasswordComponent },

  { path: 'Confirmation', component: MailconfirmationComponent },
  //{ path: 'Offre/:id', component: ApplicationFormComponent },
  { path: 'WWW', component: ListOfferComponent },

  { path: 'App', component: CandidatureFormComponent },

  {
    path: 'admin',
    component: AllTemplateAdminComponent,
    children: [
      { path: 'AjouterMobilite', component: OffreComponent },
      { path: 'Home', component: HomeComponent },
    ],
  },
  {
    path: 'user',
    component: AllTemplateUserComponent,
    children: [
      {
        path: '',
        component: BodyUserComponent,
      },
      { path: 'AjouterMobilite', component: OffreComponent },
      { path: 'Home', component: HomeComponent },
      { path: 'TopCandidature', component: TopConidatureComponent },

      { path: 'ListOffers', component: ListOfferComponent },
      { path: 'ListCandidature', component: ListcandidatureComponent },

      { path: 'Postuler', component: CandidatureFormComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
