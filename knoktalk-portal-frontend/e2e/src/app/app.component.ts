import { Component, HostBinding, Inject, Renderer2 } from "@angular/core";
import { DOCUMENT } from "@angular/common";
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent {
  title = 'knoktalk-portal';
  text: string;

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private renderer: Renderer2,
    private spinner: NgxSpinnerService
  ) { }

  startSpinner(text: string) {
    this.text = text;
    this.spinner.show();
  }

  stopSpinner() {
    this.spinner.hide();
  }

}
