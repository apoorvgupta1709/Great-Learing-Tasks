package com.example.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
	 @Autowired
	    private QuestionRepository questionRepository;

	    public Question getById(int Id) {
	        return questionRepository.findById(Id).isPresent() ? questionRepository.findById(Id).get() : new Question();
	    }

	    public List<Question> getAllQ() {
	        return questionRepository.findAll();
	    }


	    public void update(Question q) {
	        questionRepository.save(q);
	    }

	    public void delete(int id) {
	        Question q = questionRepository.getById(id);
	        questionRepository.delete(q);
	    }

	    public void postQuestion(Question q) {
	        questionRepository.save(q);
	    }


	    public List<Question> getQuestionByApproved(boolean isApproved) {
	        return questionRepository.getByIsApproved(isApproved);
	    }

	    public Integer getAllQuesCount() {
	        return questionRepository.getCountByIsApproved(false);
	    }

	    public List<Question> getByTopic(String topic) {
	        return questionRepository.getQuestionsByTopic(topic);
	    }

	    public List<String> getDistinctByTopic() {
	        return questionRepository.getDistinctTopic();
	    }
  
}
