package com.question.quizserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.question.quizserver.model.QuestionWrapper;
import com.question.quizserver.model.QuizDto;
import com.question.quizserver.model.Response;
import com.question.quizserver.service.QuizeService;


@RestController
@RequestMapping("/quiz")
public class QuizApiController {
	
	@Autowired
	private QuizeService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto){
		return quizService.createQuiz(quizdto.getCategory(), quizdto.getNumQuestion(), quizdto.getTitle());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
		return quizService.getQuizQuestion(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuestion(@PathVariable Integer id, @RequestBody List<Response> response){
		return quizService.calculateResult(id, response);
	}

}
