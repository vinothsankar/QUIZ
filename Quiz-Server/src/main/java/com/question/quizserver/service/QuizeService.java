package com.question.quizserver.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.question.quizserver.feign.QuizInterface;
import com.question.quizserver.model.QuestionWrapper;
import com.question.quizserver.model.Quiz;
import com.question.quizserver.model.Response;
import com.question.quizserver.repository.QuizRepository;

@Service
public class QuizeService {
	
	@Autowired
	QuizRepository quizRepo;
	
	@Autowired
	QuizInterface quizinterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> question = quizinterface.getQuestionForQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(question);
		quizRepo.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {
		Quiz quiz = quizRepo.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions = quizinterface.getQuestionFromId(questionIds);
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		ResponseEntity<Integer> score = quizinterface.getScore(responses);
		return score;
	}
	
}
