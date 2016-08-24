package next.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import next.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	List<Question> findAll();
	
}
