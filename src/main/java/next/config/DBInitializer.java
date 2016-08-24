package next.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import next.model.Answer;
import next.model.Question;
import next.model.User;
import next.repository.AnswerRepository;
import next.repository.QuestionRepository;
import next.repository.UserRepository;

@Component
public class DBInitializer {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostConstruct
	public void init() {
		User admin, user;
		Question question;
		
		admin = new User("admin", "admin1234", "관리자", "admin@admin.com");
		user = new User("user", "user1234", "사용자", "user@user.com");
		userRepository.save(admin);
		userRepository.save(user);
		
		question = new Question(admin, "1번째 질문", "1번째 내용");
		questionRepository.save(question);
		answerRepository.save(new Answer(user, "답변a", question));
		answerRepository.save(new Answer(user, "답변b", question));
		answerRepository.save(new Answer(user, "답변c", question));
		
		question = new Question(admin, "2번째 질문", "2번째 내용");
		questionRepository.save(question);
		
		question = new Question(admin, "3번째 질문", "3번째 내용");
		questionRepository.save(question);
		answerRepository.save(new Answer(user, "답변a", question));
		answerRepository.save(new Answer(user, "답변b", question));
		
	}

}
