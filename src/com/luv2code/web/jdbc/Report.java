package com.luv2code.web.jdbc;

public class Report {
	private int season;
	
	private double agents1;
	private double agents2;
	private double agents3;
	private double agents4;
	private double agents5;
	private double agents6;
	private double agents7;
	private double agents8;
	private double agents9;
	private double agents10;
	private double agents11;
	private double agents12;
	private double agents13;
	
	private double agentsAll;
	private double combineAll;
	private double mProduction;
	private double umProduction;
	private double subsidyA;
	private double subsidyB;
	private double subsidy;
	private double cost;
	private double budget;
	private double budgetNext;
	private double working_capital;
	private int ATR;
	private int YEM;
	private double YEP;
	private String uname;
	

	public Report(int season, double agents1, double agents2, double agents3, double agents4, double agents5,
			double agents6, double agents7, double agents8, double agents9, double agents10, double agents11,
			double agents12, double agents13, double agentsAll, double combineAll, double mProduction, double umProduction,
			double subsidyA, double subsidyB, double subsidy, double cost, double budget, double budgetNext,
			double working_capital, int ATR, int YEM, double YEP, String uname) {
		
		this.season = season;
		this.agents1 = agents1;
		this.agents2 = agents2;
		this.agents3 = agents3;
		this.agents4 = agents4;
		this.agents5 = agents5;
		this.agents6 = agents6;
		this.agents7 = agents7;
		this.agents8 = agents8;
		this.agents9 = agents9;
		this.agents10 = agents10;
		this.agents11 = agents11;
		this.agents12 = agents12;
		this.agents13 = agents13;
		this.agentsAll = agentsAll;
		this.combineAll = combineAll;
		this.mProduction = mProduction;
		this.umProduction = umProduction;
		this.subsidyA = subsidyA;
		this.subsidyB = subsidyB;
		this.subsidy = subsidy;
		this.cost = cost;
		this.budget = budget;
		this.budgetNext = budgetNext;
		this.working_capital = working_capital;
		this.ATR = ATR;
		this.YEM = YEM;
		this.YEP = YEP;
		this.uname = uname;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public double getAgents1() {
		return agents1;
	}

	public void setAgents1(double agents1) {
		this.agents1 = agents1;
	}

	public double getAgents2() {
		return agents2;
	}

	public void setAgents2(double agents2) {
		this.agents2 = agents2;
	}

	public double getAgents3() {
		return agents3;
	}

	public void setAgents3(double agents3) {
		this.agents3 = agents3;
	}

	public double getAgents4() {
		return agents4;
	}

	public void setAgents4(double agents4) {
		this.agents4 = agents4;
	}

	public double getAgents5() {
		return agents5;
	}

	public void setAgents5(double agents5) {
		this.agents5 = agents5;
	}

	public double getAgents6() {
		return agents6;
	}

	public void setAgents6(double agents6) {
		this.agents6 = agents6;
	}

	public double getAgents7() {
		return agents7;
	}

	public void setAgents7(double agents7) {
		this.agents7 = agents7;
	}

	public double getAgents8() {
		return agents8;
	}

	public void setAgents8(double agents8) {
		this.agents8 = agents8;
	}

	public double getAgents9() {
		return agents9;
	}

	public void setAgents9(double agents9) {
		this.agents9 = agents9;
	}

	public double getAgents10() {
		return agents10;
	}

	public void setAgents10(double agents10) {
		this.agents10 = agents10;
	}

	public double getAgents11() {
		return agents11;
	}

	public void setAgents11(double agents11) {
		this.agents11 = agents11;
	}

	public double getAgents12() {
		return agents12;
	}

	public void setAgents12(double agents12) {
		this.agents12 = agents12;
	}
	
	public double getAgents13() {
		return agents13;
	}

	public void setAgents13(double agents13) {
		this.agents13 = agents13;
	}

	public double getAgentsAll() {
		return agentsAll;
	}

	public void setAgentsAll(double agentsAll) {
		this.agentsAll = agentsAll;
	}

	public double getCombineAll() {
		return combineAll;
	}

	public void setCombineAll(double combineAll) {
		this.combineAll = combineAll;
	}

	public double getmProduction() {
		return mProduction;
	}

	public void setmProduction(double mProduction) {
		this.mProduction = mProduction;
	}

	public double getUmProduction() {
		return umProduction;
	}

	public void setUmProduction(double umProduction) {
		this.umProduction = umProduction;
	}

	public double getSubsidyA() {
		return subsidyA;
	}

	public void setSubsidyA(double subsidyA) {
		this.subsidyA = subsidyA;
	}

	public double getSubsidyB() {
		return subsidyB;
	}

	public void setSubsidyB(double subsidyB) {
		this.subsidyB = subsidyB;
	}

	public double getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(double subsidy) {
		this.subsidy = subsidy;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getBudgetNext() {
		return budgetNext;
	}

	public void setBudgetNext(double budgetNext) {
		this.budgetNext = budgetNext;
	}

	public double getWorking_capital() {
		return working_capital;
	}

	public void setWorking_capital(double working_capital) {
		this.working_capital = working_capital;
	}

	public int getYEM() {
		return YEM;
	}

	public void setYEM(int yEM) {
		YEM = yEM;
	}

	public int getATR() {
		return ATR;
	}

	public void setATR(int aTR) {
		ATR = aTR;
	}

	public double getYEP() {
		return YEP;
	}

	public void setYEP(double yEP) {
		YEP = yEP;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		return "Report [season=" + season + ", agents1=" + agents1 + ", agents2=" + agents2 + ", agents3=" + agents3
				+ ", agents4=" + agents4 + ", agents5=" + agents5 + ", agents6=" + agents6 + ", agents7=" + agents7
				+ ", agents8=" + agents8 + ", agents9=" + agents9 + ", agents10=" + agents10 + ", agents11=" + agents11
				+ ", agents12=" + agents12 + ", agents13=" + agents13 + ", agentsAll=" + agentsAll + ", combineAll="
				+ combineAll + ", mProduction=" + mProduction + ", umProduction=" + umProduction + ", subsidyA="
				+ subsidyA + ", subsidyB=" + subsidyB + ", subsidy=" + subsidy + ", cost=" + cost + ", budget=" + budget
				+ ", budgetNext=" + budgetNext + ", working_capital=" + working_capital + ", ATR=" + ATR + ", YEM="
				+ YEM + ", YEP=" + YEP + ", uname=" + uname + "]";
	}

	
	
}
