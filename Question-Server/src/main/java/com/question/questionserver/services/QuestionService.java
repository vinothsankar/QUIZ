package com.question.questionserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.question.questionserver.model.Question;
import com.question.questionserver.model.QuestionWrapper;
import com.question.questionserver.model.Response;
import com.question.questionserver.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
			return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String Category) {

		try {
			return new ResponseEntity<>(questionRepository.findByCategory(Category), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			questionRepository.save(question);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Not Created", HttpStatus.BAD_GATEWAY);
	}

	public ResponseEntity<String> addQuestions(List<Question> questions) {
		try {
			questionRepository.saveAll(questions);
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Not Created", HttpStatus.BAD_GATEWAY);
	}

	public String deleteQuestion(int id) {
		if (questionRepository.existsById(id)) {
			questionRepository.deleteById(id);
			return "Delete successfully";
		} else
			return "Id doesn\'t not exist in our database";
	}

	public Question getById(int id) {
		if (questionRepository.findById(id).isPresent()) {
			return questionRepository.findById(id).get(); // Safe because we checked for presence
		} else {
			throw new RuntimeException("Question not found with the id:" + id);
		}

	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numberQuestion) {
		List<Integer> questions = questionRepository.findRandomQuestionsByCategory(category, numberQuestion);
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionId) {
		List<QuestionWrapper> wrappers = new ArrayList<>(); 
		List<Question> questions = new ArrayList<>();
		
		for(Integer id : questionId) {
			questions.add(questionRepository.findById(id).get());
		}
		
		for(Question ques: questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(ques.getId());
			wrapper.setQuestionTitle(ques.getQuestionTitle());
			wrapper.setOption1(ques.getOption1());
			wrapper.setOption2(ques.getOption2());
			wrapper.setOption3(ques.getOption3());
			wrapper.setOption4(ques.getOption4());
			wrappers.add(wrapper);
		}
		
		return new ResponseEntity<>(wrappers, HttpStatus.CREATED);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right = 0;
		for(Response response : responses) {
			Question question = questionRepository.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) {
				right++;
			}
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
		
	}

	

}
