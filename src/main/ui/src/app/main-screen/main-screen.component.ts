import {Component, OnInit} from '@angular/core';
import {ContactMeRequest} from "../../model/contact.me";
import {Backend} from "../../services/backend";
import {CryptoUtil} from "../../services/encryption";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css']
})
export class MainScreenComponent implements OnInit {
  crypto = require("crypto-js");


  contactMeRequest: ContactMeRequest = {
    email: "", message: "", name: "", subject: ""
  };


  constructor(private backend: Backend, private cryptoUtil: CryptoUtil, private router: Router) {
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
    this.cryptoUtil.generateRequestKey().then(res => {
      this.backend.contactMeSubmit(this.contactMeRequest, res).subscribe(res => {
        this.router.navigate(['/thank-you']);
      });
    });
  }


}
