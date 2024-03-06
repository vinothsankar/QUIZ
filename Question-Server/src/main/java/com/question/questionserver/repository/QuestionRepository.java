package com.question.questionserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.question.questionserver.model.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	List<Question> findByCategory(String category);
	
	@Query(value ="SELECT ID FROM QUESTION WHERE CATEGORY = :category ORDER BY DBMS_RANDOM.VALUE FETCH NEXT :numQ ROWS ONLY",nativeQuery = true)
	List<Integer> findRandomQuestionsByCategory(@Param("category")String category, @Param("numQ") int numQ);
}