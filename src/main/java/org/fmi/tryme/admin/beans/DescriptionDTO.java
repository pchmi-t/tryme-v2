package org.fmi.tryme.admin.beans;

public class DescriptionDTO {

	private long id;
	private String title;
	private String subject;
	private String description;
	private CategoryDTO category;

	public TestDescription toDescription() {
		return new TestDescription(Long.toString(id), title, description, subject, category.toCategory());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DescriptionDTO [subject=").append(subject).append(", description=").append(description)
				.append(", title=").append(title).append(", id=").append(id).append(", category=").append(category)
				.append("]");
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
		DescriptionDTO other = (DescriptionDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
