import {Injectable} from 'angular2/core'

export interface myData {
   twitterId:string;
}

@Injectable()
export class SharedService {

  sharingData: myData={twitterId:"anyTwitterId"};

  saveData(str){
    this.sharingData.twitterId=str; 
  }

  getData()
  {
    return this.sharingData.twitterId;
  }
} 
