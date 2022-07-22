package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Question;
import com.example.demo.service.impl.QuestionService;

import javax.persistence.Id;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {
	
	  @Autowired

	    private QuestionService questionService;

	    @GetMapping("/fetchAllquestions")//
	    private List<Question> getAllQuestions() {
	        return questionService.getAllQ();
	    }

	    @GetMapping("/totalQuestions")//
	    private Integer questionCount() {
	        return questionService.getAllQuesCount();
	    }

	    @GetMapping("/questions/notapprovedByAdmin")//
	    private List<Question> questionByNotApproved() {
	        return questionService.getQuestionByApproved(false);
	    }

	    @GetMapping("/questions/approved")//
	    private List<Question> questionByApproved() {
	        return questionService.getQuestionByApproved(true);
	    }

	    @PatchMapping("/question/updateByUser")//
	    private String updateQuestion(@RequestBody Question q) {
	        try {
	            q.setAnswered(true);
	            questionService.update(q);
	            return "ok";
	        } catch (Exception e) {
	            return "no";
	        }
	    }


	    @PatchMapping("/updateApproved/{id}")//
	    private String updateQuestionApprooved(@PathVariable int id) {
	        try {
	            Question q = questionService.getById(id);
	            q.setApproved(true);
	            questionService.update(q);
	            return "ok";
	        } catch (Exception e) {
	            return "no";
	        }
	    }

	    @DeleteMapping("/deleteQuestion/{id}")//
	    private String deleteQuestion(@PathVariable int id) {
	        try {
	            questionService.delete(id);
	            return "ok";
	        } catch (Exception e) {
	            return "no";
	        }
	    }

	    @PostMapping("/addQuestion")//
	    private String postQuestion(@RequestBody HashMap<String, String> body) {
	        try {
	            String question = body.get("question");
	            String email = body.get("email");
	            String topic = body.get("topic");
	            String name = body.get("name");
	            Question q = new Question(question, email, name, topic);
	            questionService.postQuestion(q);
	            return "ok";
	        } catch (Exception e) {
	            return "no";
	        }
	    }

	    @GetMapping("/questionsByTopic/{topic}")//
	    private List<Question> getQuestionsByTopic(@PathVariable String topic) {
	        return questionService.getByTopic(topic);
	    }

	    @GetMapping("/questions/distinctQuestions")//
	    private List<String> getDistinctTopics() {
	        return questionService.getDistinctByTopic();
	    }
}


