import { Injectable } from '@angular/core';
import { UrlTree } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class QuestionService {
  constructor() {}
  url = 'http://localhost:8080/';

  update(data: any): Promise<any> {
    return fetch(this.url + 'question/updateByUser', {
      method: 'PATCH',
      body: JSON.stringify(data),
      headers: {
        'Content-type': 'application/json',
      },
    }).then((res) => res.text());
  }

  getAllQuestions(): Promise<any> {
    return fetch(this.url + 'fetchAllquestions', {
      method: 'GET',
    }).then((res) => res.json());
  }

  getUniqueQuestions(): Promise<any> {
    return fetch(this.url + 'questions/distinctQuestions').then((res) => res.json());
  }

  getQuestionsByTopic(topic: string): Promise<any> {
    return fetch(this.url + `questionsByTopic/${topic}`).then((res) =>
      res.json()
    );
  }

  saveQuestion(data: any): Promise<any> {
    return fetch(this.url + 'addQuestion', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-type': 'application/json',
      },
    }).then((res) => res.text());
  }
}
