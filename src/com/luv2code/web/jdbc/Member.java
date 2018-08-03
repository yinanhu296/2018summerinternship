package com.luv2code.web.jdbc;

public class Member {
	private String uname;
	private double score;
	
	public Member(String uname, double score) {
		this.uname = uname;
		this.score = score;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Member [uname=" + uname + ", score=" + score + "]";
	}

	
	
}
