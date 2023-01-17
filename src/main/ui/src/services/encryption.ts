import {BrowserUtil} from "./browserUtil";

export class CryptoUtil {
  cryptoJS = require("crypto-js");

  passphrase = 'passPhrase';
  salt = '73616c74';
  ivHexEncoded = '00000000000000000000000000000000';
  key = this.cryptoJS.PBKDF2(this.passphrase, this.cryptoJS.enc.Hex.parse(this.salt), {
    keySize: 128 / 32,
    iterations: 100
  });

  encrypt(message: string) {
    let encrypted = this.cryptoJS.AES.encrypt(message, this.key, {iv: this.cryptoJS.enc.Hex.parse(this.ivHexEncoded)});
    return encrypted;
  }

  decrypt(message: string) {
    let decryptedData = this.cryptoJS.AES.decrypt(message, this.key, {iv: this.cryptoJS.enc.Hex.parse(this.ivHexEncoded)})
      .toString(this.cryptoJS.enc.Utf8);
    return decryptedData;
  }

  generateRequestKey() {
    const browserDetails = BrowserUtil.getDetails();
    return new Promise((resolve, reject) => {
      let requestKey = {
        browserId: browserDetails,
        date: Date.now()
      };
      return  resolve(this.encrypt(JSON.stringify(requestKey)).toString());
    });
  }

}
