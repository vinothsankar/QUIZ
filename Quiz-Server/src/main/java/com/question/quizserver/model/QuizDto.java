package com.question.quizserver.model;

public class QuizDto {

	private String category;
	private Integer numQuestion;
	private String title;

	public QuizDto() {
		super();
	}

	public QuizDto(String category, Integer numQuestion, String title) {
		super();
		this.category = category;
		this.numQuestion = numQuestion;
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getNumQuestion() {
		return numQuestion;
	}

	public void setNumQuestion(Integer numQuestion) {
		this.numQuestion = numQuestion;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
