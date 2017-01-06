import { browser, element, by } from 'protractor';

export class MtgcardsWebPage {
  navigateTo() {
    return browser.get('/');
  }
}
