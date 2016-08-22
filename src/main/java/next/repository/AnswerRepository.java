package next.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import next.model.Answer;
import next.model.User;

public interface AnswerRepository extends JpaRepository<Answer, Long>{

	List<Answer> findAll();
	
	List<Answer> findById(long id);
	
}
