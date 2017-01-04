package org.fmi.tryme.test.beans;

import org.fmi.tryme.admin.beans.Answer;

public class AnswerOption {

	private final String id;

	private final String text;

	AnswerOption(String id, String text) {
		this.text = text;
		this.id = id;
	}

	public static AnswerOption fromAnswer(Answer answer) {
		return new AnswerOption(answer.getId(), answer.getText());
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnswerOption [id=").append(id).append(", text=").append(text).append("]");
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
		AnswerOption other = (AnswerOption) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
