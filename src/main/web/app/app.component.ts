import {Component, OnInit} from 'angular2/core';
import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {Router, RouteConfig, ROUTER_DIRECTIVES} from 'angular2/router';
import {SharedService} from './service/sharedService';
import {HomePageComponent} from './home/homepage-component';
import {SecondPageComponent} from './second/secondpage-component';

import 'rxjs/Rx';

@Component({
    selector: 'my-app',
    directives: [ROUTER_DIRECTIVES],
    providers:[SharedService],
    templateUrl: 'app/app.component.html'
})

@RouteConfig([
  {path: '/homepage', as: 'HomePageComponent', useAsDefault: true, component: HomePageComponent},
  {path: '/secondpage', as: 'SecondPageComponent', useAsDefault: false, component: SecondPageComponent}
 ])

export class AppComponent implements OnInit{

   router:Router;

  constructor(router:Router)
  {
    this.router=router;
  }
  
  ngOnInit() {
    this.router.navigate(['/HomePageComponent']);
  }
  
}