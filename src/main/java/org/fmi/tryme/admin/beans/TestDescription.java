package org.fmi.tryme.admin.beans;

public class TestDescription {

	private final String id;

	private final String title;

	private final String description;

	private final String subject;

	private final Category category;

	public TestDescription(String id, String title, String description, String subject, Category category) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.subject = subject;
		this.category = category;
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
		TestDescription other = (TestDescription) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TestDescription [id=").append(id).append(", title=").append(title).append(", description=")
				.append(description).append(", subject=").append(subject).append(", category=").append(category)
				.append("]");
		return builder.toString();
	}
	
	

}
