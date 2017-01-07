package org.fmi.tryme.admin.beans;

public class CategoryDTO {

	private long id;
	private String name;
	private int tests_count;

	public Category toCategory() {
		return new Category(Long.toString(id), name, tests_count);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryDTO [id=").append(id).append(", name=").append(name).append(", tests_count=")
				.append(tests_count).append("]");
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
		CategoryDTO other = (CategoryDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
