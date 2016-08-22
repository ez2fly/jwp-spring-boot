package core.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import next.model.User;
import next.repository.UserRepository;

public class UserTest extends IntegrationTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void crud() throws Exception {
		User user = new User("testId", "password", "name", "email@email.com");
		userRepository.save(user);
	}
}
