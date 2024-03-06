package com.question.quizserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.question.quizserver.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
