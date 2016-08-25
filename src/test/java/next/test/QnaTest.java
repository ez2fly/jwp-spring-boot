package next.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import next.config.DBInitializer;
import next.repository.AnswerRepository;
import next.repository.QuestionRepository;
import next.repository.UserRepository;
import next.service.QnaService;

// 아래 테스트코드는 동작이 안됨.
// DataJpaTest 는 jpa 로 생성되는 repository 만 불러와서 테스트 할수 있다함 
// 요 경우에는 Mockito 로 해서 테스트 한다고 하심
@RunWith(SpringRunner.class)
@DataJpaTest
public class QnaTest {
	private static final Logger log = LoggerFactory.getLogger(QnaTest.class);
	
	@Autowired
	private DBInitializer dbinit;
	@Autowired
	private QnaService qna;	
	
	@Test
	public void deleteQuestion() {
		try {
			qna.deleteQuestion(0, null);
		}catch(Exception ex) {
			log.debug(ex.toString());
		}
	}

}
