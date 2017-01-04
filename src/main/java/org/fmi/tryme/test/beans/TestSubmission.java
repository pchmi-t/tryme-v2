package org.fmi.tryme.test.beans;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class TestSubmission {

	@NotBlank
	private String id;

	@NotEmpty
	private List<AnsweredQuestion> answers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AnsweredQuestion> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnsweredQuestion> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestSubmission [id=").append(id).append(", answers=").append(answers).append("]");
		return builder.toString();
	}

}
