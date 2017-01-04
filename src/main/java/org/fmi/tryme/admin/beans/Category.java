package org.fmi.tryme.admin.beans;

public class Category {

	private final String id;
	private final String name;
	private final int testsCount;

	public Category(String id, String name, int testsCount) {
		this.id = id;
		this.testsCount = testsCount;
		this.name = name;
	}

	public int getTestsCount() {
		return testsCount;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
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
		Category other = (Category) obj;
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
		builder.append("Category [id=").append(id).append(", name=").append(name).append(", testsCount=")
				.append(testsCount).append("]");
		return builder.toString();
	}

}
