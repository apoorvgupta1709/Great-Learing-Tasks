package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {



	    List<Question> getByIsApproved(boolean isApproved);

	    @Query("SELECT COUNT(q) FROM Question q where q.isApproved=:param")
	    Integer getCountByIsApproved(@Param("param") boolean isApproved);

	    List<Question> getQuestionsByTopic(String topic);

	    @Query("SELECT DISTINCT q.topic FROM Question q")
	    List<String> getDistinctTopic();
}
