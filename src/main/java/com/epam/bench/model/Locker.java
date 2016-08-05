package com.epam.bench.model;

public final class Locker {

	private final int id;
	private final User user;
	private final String pass;

	private Locker(Builder builder) {
		this.user = builder.user;
		this.id = builder.id;
		this.pass = builder.pass;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Locker other = (Locker) obj;
		if (id != other.id)
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Locker [id=" + id + ", user=" + user + ", pass=" + pass + "]";
	}

	public static class Builder {
		private int id;
		private User user;
		private String pass;

		public Builder withId(int id) {
			this.id = id;
			return this;
		}

		public Builder withUser(User user) {
			this.user = user;
			return this;
		}

		public Builder withPass(String pass) {
			this.pass = pass;
			return this;
		}

		public Locker build() {
			return new Locker(this);
		}
	}

}
