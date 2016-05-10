import {Component} from 'angular2/core';
import {SharedService} from '../service/sharedService';
import {Router} from 'angular2/router';

@Component({
  selector: 'home',
  templateUrl: 'app/home/home.component.html',
})

export class HomePageComponent { 

  service:SharedService;

  router:Router;

  constructor(service:SharedService,router:Router){
    this.service=service;
    this.router=router;
  }
  
  send(str){
    this.service.saveData(str); 
    this.router.navigate(['/SecondPageComponent']);
  }
}