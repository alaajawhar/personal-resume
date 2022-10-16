import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ThankYouComponent} from "./thank-you/thank-you.component";
import {MainScreenComponent} from "./main-screen/main-screen.component";

const routes: Routes = [
  { path: 'thank-you', component: ThankYouComponent },
  { path: 'main', component: MainScreenComponent },
  { path: '', component: MainScreenComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
