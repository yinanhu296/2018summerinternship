package com.luv2code.web.jdbc;

public class yearReport {

	private int season;
	private double subsidy;
	private double working_capital;
	private double mProduction;
	private double renewal_commision;
	private double additional_subsidy;
	private double total_score;
	private int expected_ATR;
	private int expected_YEM;
	private int actual_YEM;
	private double expected_YEP;
	private double actual_YEP;
	private String uname;
	

	public yearReport(int season, double subsidy, double working_capital, double mProduction, 
			double renewal_commision, double additional_subsidy, double total_score, 
			int expected_ATR, int expected_YEM, int actual_YEM, double expected_YEP, double actual_YEP, String uname) {
		this.season = season;
		this.subsidy = subsidy;
		this.working_capital = working_capital;
		this.mProduction = mProduction;
		this.renewal_commision = renewal_commision;
		this.additional_subsidy = additional_subsidy;
		this.total_score = total_score;
		this.expected_ATR = expected_ATR;
		this.expected_YEM = expected_YEM;
		this.actual_YEM = actual_YEM;
		this.expected_YEP = expected_YEP;
		this.actual_YEP = actual_YEP;
		this.uname = uname;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public double getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(double subsidy) {
		this.subsidy = subsidy;
	}

	public double getWorking_capital() {
		return working_capital;
	}

	public void setWorking_capital(double working_capital) {
		this.working_capital = working_capital;
	}

	public double getmProduction() {
		return mProduction;
	}

	public void setmProduction(double mProduction) {
		this.mProduction = mProduction;
	}

	public double getRenewal_commision() {
		return renewal_commision;
	}

	public void setRenewal_commision(double renewal_commision) {
		this.renewal_commision = renewal_commision;
	}


	public double getAdditional_subsidy() {
		return additional_subsidy;
	}

	public void setAdditional_subsidy(double additional_subsidy) {
		this.additional_subsidy = additional_subsidy;
	}

	public double getTotal_score() {
		return total_score;
	}


	public void setTotal_score(double total_score) {
		this.total_score = total_score;
	}

	public int getExpected_ATR() {
		return expected_ATR;
	}

	public void setExpected_ATR(int expected_ATR) {
		this.expected_ATR = expected_ATR;
	}

	public int getExpected_YEM() {
		return expected_YEM;
	}

	public void setExpected_YEM(int expected_YEM) {
		this.expected_YEM = expected_YEM;
	}

	public int getActual_YEM() {
		return actual_YEM;
	}

	public void setActual_YEM(int actual_YEM) {
		this.actual_YEM = actual_YEM;
	}

	public double getExpected_YEP() {
		return expected_YEP;
	}

	public void setExpected_YEP(double expected_YEP) {
		this.expected_YEP = expected_YEP;
	}
	
	public double getActual_YEP() {
		return actual_YEP;
	}

	public void setActual_YEP(double actual_YEP) {
		this.actual_YEP = actual_YEP;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "yearReport [season=" + season + ", subsidy=" + subsidy + ", working_capital=" + working_capital
				+ ", mProduction=" + mProduction + ", renewal_commision=" + renewal_commision + ", additional_subsidy="
				+ additional_subsidy + ", total_score=" + total_score + ", expected_ATR=" + expected_ATR
				+ ", expected_YEM=" + expected_YEM + ", actual_YEM=" + actual_YEM + ", expected_YEP=" + expected_YEP
				+ ", actual_YEP=" + actual_YEP + ", uname=" + uname + "]";
	}

	

	
}
