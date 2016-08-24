package next.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.ForeignKey;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long questionId;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	@Size(min = 1, max = 50)
	private String title;

	@Size(min = 1, max = 5000)
	private String contents;

	@Column(nullable = false)
	private Date createdDate;
	
	@Formula("(select count(*) from answer where answer.question_question_id=question_id)")
	private int countOfAnswer;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	@OrderBy("answerId ASC")
	private List<Answer> answers;

	
	public Question() {
		this.createdDate = new Date();
	}
	public Question(User writer) {
		this.writer = writer;
		this.createdDate = new Date();
	}
	public Question(User writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = new Date();
	}
	
	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public int getCountOfAnswer() {
		return countOfAnswer;
	}
	public void setCountOfAnswer(int countOfAnswer) {
		this.countOfAnswer = countOfAnswer;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public User getWriter() {
		return writer;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	
	public long getTimeFromCreateDate() {
		return this.createdDate.getTime();
	}
	
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public boolean isSameUser(User user) {
		return this.writer.isSameUser(user);
	}

	public void update(Question newQuestion) {
		this.title = newQuestion.title;
		this.contents = newQuestion.contents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (questionId ^ (questionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", writer=" + writer.getName() + ", title=" + title + ", contents="
				+ contents + ", createdDate=" + createdDate + ", countOfAnswer=" + countOfAnswer + "]";
	}
}
