import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor() {}

  url = 'http://localhost:8080/';

  approve(id: Number): Promise<any> {
    return fetch(this.url + 'updateApproved/' + id, {
      method: 'PATCH',
    }).then((res) => res.text());
  }

  delete(id: Number): Promise<string> {
    return fetch(this.url + 'deleteQuestion/' + id, {
      method: 'DELETE',
    }).then((res) => res.text());
  }

  getQuestions(): Promise<Body> {
    return fetch(this.url + 'questions/notapprovedByAdmin', {
      method: 'GET',
    }).then((res) => res.json());
  }

  getCount(): Promise<string> {
    return fetch(this.url + 'totalQuestions', {
      method: 'GET',
    }).then((res) => res.text());
  }
}
