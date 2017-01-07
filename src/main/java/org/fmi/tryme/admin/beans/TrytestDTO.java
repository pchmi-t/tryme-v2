package org.fmi.tryme.admin.beans;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TrytestDTO {

	private DescriptionDTO description;
	private Set<QuestionDTO> questions;

	public Trytest toTrytest() {
		Map<String, Question> testQuestions = questions.stream().map(QuestionDTO::toQuestion)
				.collect(Collectors.toMap(Question::getId, q -> q));
		return new Trytest(description.toDescription(), testQuestions);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TrytestDTO [description=").append(description).append(", questions=").append(questions)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		TrytestDTO other = (TrytestDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
