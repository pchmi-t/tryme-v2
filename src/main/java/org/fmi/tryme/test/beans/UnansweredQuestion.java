package org.fmi.tryme.test.beans;

import java.util.HashSet;
import java.util.Set;

import org.fmi.tryme.admin.beans.Answer;
import org.fmi.tryme.admin.beans.Question;

public class UnansweredQuestion {

	private final String id;

	private final String text;

	private final int points;

	private final Set<AnswerOption> answers;

	UnansweredQuestion(String id, String text, int points) {
		this.answers = new HashSet<>();
		this.points = points;
		this.text = text;
		this.id = id;
	}

	public static UnansweredQuestion fromQuestion(Question testQuestion) {
		UnansweredQuestion unanswered = new UnansweredQuestion(testQuestion.getId(), testQuestion.getText(),
				testQuestion.getPoints());
		for (Answer answer : testQuestion.getAnswers().values()) {
			unanswered.addAnswer(AnswerOption.fromAnswer(answer));
		}
		return unanswered;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public int getPoints() {
		return points;
	}

	public Set<AnswerOption> getAnswers() {
		return answers;
	}

	void addAnswer(AnswerOption answer) {
		this.answers.add(answer);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnansweredQuestion [id=").append(id).append(", text=").append(text).append(", points=")
				.append(points).append(", answers=").append(answers).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UnansweredQuestion other = (UnansweredQuestion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
