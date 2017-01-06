import { Component } from '@angular/core';

@Component({
  selector: 'mtc-root',
  template: `
  <h1>
    {{title}}
  </h1>
  `,
  styles: []
})
export class AppComponent {
  title = 'mtc works!';
}
