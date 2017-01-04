package org.fmi.tryme.admin.beans;

import java.util.Map;

public class Trytest {

	private final TestDescription description;
	private final Map<String, Question> questions;

	public Trytest(TestDescription description, Map<String, Question> questions) {
		this.description = description;
		this.questions = questions;
	}

	public TestDescription getDescription() {
		return description;
	}

	public Map<String, Question> getQuestions() {
		return questions;
	}
	
	public Question getQuestion(String questionId) {
		return questions.get(questionId);
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
		Trytest other = (Trytest) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
