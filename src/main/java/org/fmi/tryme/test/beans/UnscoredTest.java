package org.fmi.tryme.test.beans;

import java.util.HashSet;
import java.util.Set;

import org.fmi.tryme.admin.beans.Category;
import org.fmi.tryme.admin.beans.Question;
import org.fmi.tryme.admin.beans.TestDescription;
import org.fmi.tryme.admin.beans.Trytest;

public class UnscoredTest {

	private final String id;

	private final String title;

	private final String description;

	private final String subject;

	private final Category category;

	private Set<UnansweredQuestion> questions;

	UnscoredTest(String id, String title, String description, String subject, Category category) {
		this.questions = new HashSet<>();
		this.description = description;
		this.category = category;
		this.subject = subject;
		this.title = title;
		this.id = id;
	}

	public static UnscoredTest fromTest(Trytest tryTest) {
		TestDescription description = tryTest.getDescription();
		UnscoredTest unscored = new UnscoredTest(description.getId(), description.getTitle(),
				description.getDescription(), description.getSubject(), description.getCategory());
		for (Question testQuestion : tryTest.getQuestions().values()) {
			unscored.addQuestion(UnansweredQuestion.fromQuestion(testQuestion));
		}
		return unscored;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getSubject() {
		return subject;
	}

	public Category getCategory() {
		return category;
	}

	public Set<UnansweredQuestion> getQuestions() {
		return questions;
	}

	void addQuestion(UnansweredQuestion question) {
		questions.add(question);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UnscoredTest [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", subject=").append(subject).append(", category=").append(category)
				.append(", questions=").append(questions).append("]");
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
		UnscoredTest other = (UnscoredTest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
