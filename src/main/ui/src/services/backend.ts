import {Injectable} from "@angular/core";
import {environment} from "../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ContactMeRequest, ContactMeResponse} from "../model/contact.me";

@Injectable()
export class Backend {

  public static BASE_URL = environment.baseUrl;
  public static SEND_CONTACT_ME_DETAILS: string = Backend.BASE_URL + "/contact-me/submit";

  constructor(private http: HttpClient) { }

  contactMeSubmit(contactMeRequest: ContactMeRequest, requestKey: any) {
    const headers = new HttpHeaders({'X-PATH': requestKey});
    return this.http.post<ContactMeResponse>(Backend.SEND_CONTACT_ME_DETAILS, contactMeRequest, {headers: headers});
  }


}
