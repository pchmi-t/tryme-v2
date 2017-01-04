package org.fmi.tryme.test.beans;

import org.hibernate.validator.constraints.NotBlank;

public class AnsweredQuestion {

	@NotBlank
	private String questionId;

	@NotBlank
	private String answerId;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

}
