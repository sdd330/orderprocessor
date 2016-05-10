import {Component} from 'angular2/core';
import {SharedService} from '../service/sharedService';
import {Router} from 'angular2/router';
import {Http, HTTP_PROVIDERS} from 'angular2/http';
@Component({
  selector: 'secondPage',
  templateUrl: 'app/second/second.component.html',
})

export class SecondPageComponent   {

  router:Router;

  service:SharedService;

  twitterId: string;

  http:Http;

  url:string = 'http://jsonplaceholder.typicode.com/posts/1';

  result:string;
  
  constructor(router:Router,service:SharedService, http:Http)
  {
    this.router=router;
    this.service=service;
    this.http=http;
    this.twitterId=service.getData();

    this.http.get(this.url).subscribe((data) => {
        this.result = JSON.stringify(data);
    });
  }

  back()
  {
    this.router.navigate(['/HomePageComponent']);
  }
  
}