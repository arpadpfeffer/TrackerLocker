package com.epam.bench.model;

public final class User {
	private final String name;

	private User(Builder builder) {
		this.name = builder.name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

	public static class Builder {
		private String name;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
