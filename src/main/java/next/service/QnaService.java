package next.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import next.CannotOperateException;
import next.aop.PerformanceAspect;
import next.model.Answer;
import next.model.Question;
import next.model.User;
import next.repository.AnswerRepository;
import next.repository.QuestionRepository;

@Service
public class QnaService {
	private static final Logger log = LoggerFactory.getLogger(QnaService.class);
	
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;

	@Autowired
	public QnaService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
	}

	public Question findById(long questionId) {
		return questionRepository.findOne(questionId);
	}

	public List<Answer> findAllByQuestionId(long questionId) {
		Question question = questionRepository.findOne(questionId);
		if (question != null){
			return question.getAnswers();
		}
		return new ArrayList<Answer>();
	}

	public void deleteQuestion(long questionId, User user) throws CannotOperateException {
		Question question = questionRepository.findOne(questionId);
		if (question == null) {
			log.debug("존재하지 않는 질문입니다.");
			throw new EmptyResultDataAccessException("존재하지 않는 질문입니다.", 1);
		}
		
		if (!question.isSameUser(user)) {
			log.debug("다른 사용자가 쓴 글을 삭제할 수 없습니다.");
			throw new CannotOperateException("다른 사용자가 쓴 글을 삭제할 수 없습니다.");
		}
		
		List<Answer> answers = question.getAnswers();
		if (answers == null || answers.isEmpty()) {
			log.debug("답변 없음");
			questionRepository.delete(questionId);
			return;
		}
		
		boolean canDelete = true;
		for (Answer answer : answers) {
			User writer = question.getWriter();
			if (!writer.equals(answer.getWriter())) {
				canDelete = false;
				break;
			}
		}

		if (!canDelete) {
			log.debug("다른 사용자가 추가한 댓글이 존재해 삭제할 수 없습니다.");
			throw new CannotOperateException("다른 사용자가 추가한 댓글이 존재해 삭제할 수 없습니다.");
		}

		questionRepository.delete(questionId);
	}

	public void updateQuestion(long questionId, Question newQuestion, User user) throws CannotOperateException {	
		Question question = questionRepository.findOne(questionId);
        if (question == null) {
        	throw new EmptyResultDataAccessException("존재하지 않는 질문입니다.", 1);
        }
        
        if (!question.isSameUser(user)) {
            throw new CannotOperateException("다른 사용자가 쓴 글을 수정할 수 없습니다.");
        }
        
        question.update(newQuestion);
        questionRepository.save(question);
	}
}
