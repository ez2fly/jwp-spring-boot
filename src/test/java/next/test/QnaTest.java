package next.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import next.config.DBInitializer;
import next.service.QnaService;

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
