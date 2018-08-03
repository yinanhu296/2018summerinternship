package com.luv2code.web.jdbc;

public class Worker {
	private String name;

	private int level;
	
	// each agent need to be trained twice to be promoted to next level
	private int training;
	
	// record how many seasons the agent has not been trained
	private int no_training;
	private String uname;
	
	public Worker(String name, int level, int training, int no_training, String uname) {
		this.name = name;
		this.level = level;
		this.training = training;
		this.no_training = no_training;
		this.uname = uname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTraining() {
		return training;
	}

	public void setTraining(int training) {
		this.training = training;
	}
	
	public int getNo_training() {
		return no_training;
	}

	public void setNo_training(int no_training) {
		this.no_training = no_training;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Worker [name=" + name + ", level=" + level + ", training=" + training + ", no_training=" + no_training
				+ ", uname=" + uname + "]";
	}
	
}

