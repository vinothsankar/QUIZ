package com.question.questionserver.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.question.questionserver.model.Question;
import com.question.questionserver.model.QuestionWrapper;
import com.question.questionserver.model.Response;
import com.question.questionserver.services.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/allQuestion")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return 	questionService.getAllQuestion();
	}
	
	@GetMapping("/allQuestion/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@PostMapping(path = "/add/list")
	public ResponseEntity<String> addQuestions(@RequestBody List<Question> questions) {
		return questionService.addQuestions(questions);
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question question) {
		Question existQuestion = questionService.getById(id);
		if(existQuestion != null) {			
			existQuestion.setCategory(question.getCategory());
			existQuestion.setDifficultyLevel(question.getDifficultyLevel());
			existQuestion.setOption1(question.getOption1());
			existQuestion.setOption2(question.getOption2());
			existQuestion.setOption3(question.getOption3());
			existQuestion.setOption4(question.getOption4());
			existQuestion.setQuestionTitle(question.getQuestionTitle());
			existQuestion.setRightAnswer(question.getRightAnswer());
			questionService.addQuestion(existQuestion);
			return new ResponseEntity<>(question, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteQuestion(@PathVariable int id) {
		return questionService.deleteQuestion(id);
	}
	
	//this are quiz based 
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numberQuestion){
		return questionService.getQuestionForQuiz(category, numberQuestion);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionId){
		return questionService.getQuestionFromId(questionId);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response){
		return questionService.getScore(response);
	}
}
