import {Component} from '@angular/core';
import * as scrollreveal from 'scrollreveal';
import {ContactMeRequest} from "../model/contact.me";
import {Backend} from "../services/backend";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  contactMeRequest: ContactMeRequest = {
    email: "", message: "", name: "", subject: ""
  };

  constructor(private backend: Backend) {
  }

  ngOnInit() {
    const header = document.querySelector("header");

    window.addEventListener("scroll", function () {
      header!.classList.toggle("sticky", window.scrollY > 0);
    });

    let menu: any = document.querySelector('#menu-icon');
    let navbar = document.querySelector('.navbar');

    menu!.onclick = () => {
      menu!.classList.toggle('bx-x');
      navbar!.classList.toggle('active');
    };

    window.onscroll = () => {
      menu!.classList.remove('bx-x');
      navbar!.classList.remove('active');
    };

    // let sr: any = scrollreveal(
    //   distance: '25px',
    //   duration: 2500,
    //   reset: true
    // )

    // sr.reveal('.home-text', { delay: 190, origin: 'bottom' })

    // sr.reveal('.about,.services,.portfolio,.contact', { delay: 200, origin: 'bottom' });
  }

  onSubmit() {
    console.log(this.contactMeRequest);
    this.backend.contactMeSubmit(this.contactMeRequest);
  }


}
