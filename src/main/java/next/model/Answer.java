package next.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long answerId;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;
	
	@Size(min = 1, max = 5000)
	private String contents;
	
	@Column(nullable = false)
	private Date createdDate;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
	@JsonBackReference
	private Question question;
	
	
	public Answer() { 
		this.createdDate = new Date();
	}
	
	public Answer(User writer, String contents, Question question) {
		this.writer = writer;
		this.contents = contents;
		this.question = question;
		this.createdDate = new Date();
	}
	
	public long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public User getWriter() {
		return writer;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
	
	public long getTimeFromCreateDate() {
		return this.createdDate.getTime();
	}
	
	public Question getQuestion() {
		return question;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isSameUser(User user) {
		if (user == null) {
			return false;
		}
		return user.isSameUser(this.writer);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (answerId ^ (answerId >>> 32));
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
		Answer other = (Answer) obj;
		if (answerId != other.answerId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String json = "Answer [answerId=" + answerId + ", writerName=" + writer.getName()
		+ ", contents=" + contents + ", createdDate=" + createdDate
		+ ", questionId=" + question.getQuestionId() + "]";
		return json; 
	}
}
