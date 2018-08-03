package com.luv2code.web.jdbc;

public class UM {
	
	private String name;
	private String uname;
	private int training;
	
	public UM(String name, String uname, int training) {
		this.name = name;
		this.uname = uname;
		this.training = training;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getTraining() {
		return training;
	}

	public void setTraining(int training) {
		this.training = training;
	}

	@Override
	public String toString() {
		return "UM [name=" + name + ", uname=" + uname + ", training=" + training + "]";
	}

}
