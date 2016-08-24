package next.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import next.model.User;
import next.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void crud() {
		User user = new User("testId", "password", "name", "email@email.com");
		userRepository.save(user);
		assertThat(userRepository.findByUserId("testId").getEmail(), is("email@email.com"));
	}
}
