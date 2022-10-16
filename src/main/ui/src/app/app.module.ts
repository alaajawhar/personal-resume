import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from "@angular/forms";
import {Backend} from "../services/backend";
import {HttpClientModule} from "@angular/common/http";
import {CryptoUtil} from "../services/encryption";
import { ThankYouComponent } from './thank-you/thank-you.component';
import { MainScreenComponent } from './main-screen/main-screen.component';

@NgModule({
  declarations: [
    AppComponent,
    ThankYouComponent,
    MainScreenComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    Backend,
    CryptoUtil
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
