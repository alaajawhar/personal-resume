export interface ContactMeRequest {
  name: string;
  email: string;
  subject: string;
  message: string;
}

export interface ContactMeResponse {
  isError: boolean;
  errorMessage: string;
}
