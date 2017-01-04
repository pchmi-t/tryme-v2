package org.fmi.tryme.test.beans;

import java.util.HashSet;
import java.util.Set;

public class TestScore {

	private final String testId;

	private final Set<ScoredQuestion> questions;

	private long score;

	public TestScore(String testId) {
		this.questions = new HashSet<>();
		this.testId = testId;
	}

	public String getTestId() {
		return testId;
	}

	public Set<ScoredQuestion> getQuestions() {
		return questions;
	}

	public long getScore() {
		return score;
	}

	public void addQuestion(ScoredQuestion question) {
		if (question.isAnsweredCorrectly()) {
			score += question.getQuestionPoints();
		}
		questions.add(question);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestScore [testId=").append(testId).append(", questions=").append(questions).append(", score=")
				.append(score).append("]");
		return builder.toString();
	}

}
