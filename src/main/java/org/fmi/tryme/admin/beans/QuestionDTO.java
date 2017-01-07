package org.fmi.tryme.admin.beans;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionDTO {

	private long id;
	private String text;
	private int points;
	private Set<AnswerDTO> answers;

	public Question toQuestion() {
		Question question = new Question();
		question.setId(Long.toString(id));
		question.setText(text);
		question.setPoints(points);
		Map<String, Answer> converted = answers.stream().map(AnswerDTO::toAnswer)
				.collect(Collectors.toMap(Answer::getId, a -> a));
		question.setAnswers(converted);
		return question;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionDTO [id=").append(id).append(", text=").append(text).append(", points=").append(points)
				.append(", answers=").append(answers).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		QuestionDTO other = (QuestionDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
