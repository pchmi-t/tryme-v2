package org.fmi.tryme.admin.beans;

public class AnswerDTO {

	private long id;
	private String text;
	private boolean is_correct;

	public Answer toAnswer() {
		Answer answer = new Answer();
		answer.setCorrect(is_correct);
		answer.setId(Long.toString(id));
		answer.setText(text);
		return answer;
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
		AnswerDTO other = (AnswerDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnswerDTO [id=").append(id).append(", text=").append(text).append(", is_correct=")
				.append(is_correct).append("]");
		return builder.toString();
	}

}
