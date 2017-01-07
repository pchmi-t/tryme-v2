package org.fmi.tryme.test.beans;

import org.fmi.tryme.admin.beans.Question;

public class ScoredQuestion {

	private final String questionId;

	private final String questionText;

	private final int questionPoints;

	private final boolean answeredCorrectly;

	public ScoredQuestion(Question question, boolean answeredCorrectly) {
		this.questionId = question.getId();
		this.questionText = question.getText();
		this.questionPoints = question.getPoints();
		this.answeredCorrectly = answeredCorrectly;
	}

	public String getQuestionId() {
		return questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public int getQuestionPoints() {
		return questionPoints;
	}

	public boolean isAnsweredCorrectly() {
		return answeredCorrectly;
	}

}
