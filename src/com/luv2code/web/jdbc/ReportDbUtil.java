package com.luv2code.web.jdbc;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class ReportDbUtil {
	
	static final int[] AtoProduce;
	static final int[] AtoCost;
	static { AtoProduce=new int[13]; 
			 AtoProduce[0]=2000; AtoProduce[1]=4000; AtoProduce[2]=5000; 
			 AtoProduce[3]=5000; AtoProduce[4]=5000; AtoProduce[5]=5000;  
			 AtoProduce[6]=6000; AtoProduce[7]=6000; AtoProduce[8]=6000; 
			 AtoProduce[9]=6000; AtoProduce[10]=6000; AtoProduce[11]=6000; 
			 AtoProduce[12]=8000; 
		   }
	
	static { AtoCost=new int[13]; 
	 		 AtoCost[0]=1600; AtoCost[1]=1650; AtoCost[2]=1500; 
	 		 AtoCost[3]=1350; AtoCost[4]=1200; AtoCost[5]=1050;  
	 		 AtoCost[6]=900; AtoCost[7]=750; AtoCost[8]=600; 
	 		 AtoCost[9]=450; AtoCost[10]=300; AtoCost[11]=150; 
	 		 AtoCost[12]=0;
		   }
	
	private DataSource dataSource;
	public ReportDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Report> getReports(String uname) throws Exception {
		
		
		List<Report> reportList = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from reports where uname=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				
				int season = myRs.getInt("season");
				
				double agents1 = myRs.getDouble("agents1");
				double agents2 = myRs.getDouble("agents2");
				double agents3 = myRs.getDouble("agents3");
				double agents4 = myRs.getDouble("agents4");
				double agents5 = myRs.getDouble("agents5");
				double agents6 = myRs.getDouble("agents6");
				double agents7 = myRs.getDouble("agents7");
				double agents8 = myRs.getDouble("agents8");
				double agents9 = myRs.getDouble("agents9");
				double agents10 = myRs.getDouble("agents10");
				double agents11 = myRs.getDouble("agents11");
				double agents12 = myRs.getDouble("agents12");
				double agents13 = myRs.getDouble("agents13");
				
				double agentsAll = myRs.getDouble("agentsAll");
				double combineAll = myRs.getDouble("combineAll");
				double mProduction = myRs.getDouble("mProduction");
				double umProduction = myRs.getDouble("umProduction");
				double subsidyA = myRs.getDouble("subsidyA");
				double subsidyB = myRs.getDouble("subsidyB");
				double subsidy = myRs.getDouble("subsidy");
				double cost = myRs.getDouble("cost");
				double budget = myRs.getDouble("budget");
				double budgetNext = myRs.getDouble("budgetNext");
				double working_captital = myRs.getDouble("working_captital");
				int ATR = myRs.getInt("ATR");
				int YEM = myRs.getInt("YEM");
				double YEP = myRs.getDouble("YEM");
				String temp_uname = myRs.getString("uname");
				
				Report tempReport = new Report(season, agents1, agents2, 
						agents3, agents4, agents5, agents6, agents7, agents8,
						agents9, agents10, agents11, agents12, agents13, agentsAll, combineAll, 
						mProduction, umProduction, subsidyA, subsidyB, subsidy, 
						cost, budget, budgetNext, working_captital, ATR, YEM, YEP, temp_uname);
				
				reportList.add(tempReport);
			}
			return reportList;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	
	public Report getReport(String uname, int seasonNum) throws Exception{
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from reports where uname=? and season=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, seasonNum);
			myRs = myStmt.executeQuery();
			Report tempReport;
			
			if(myRs.next()) {
				
				int season = myRs.getInt("season");
				
				double agents1 = myRs.getDouble("agents1");
				double agents2 = myRs.getDouble("agents2");
				double agents3 = myRs.getDouble("agents3");
				double agents4 = myRs.getDouble("agents4");
				double agents5 = myRs.getDouble("agents5");
				double agents6 = myRs.getDouble("agents6");
				double agents7 = myRs.getDouble("agents7");
				double agents8 = myRs.getDouble("agents8");
				double agents9 = myRs.getDouble("agents9");
				double agents10 = myRs.getDouble("agents10");
				double agents11 = myRs.getDouble("agents11");
				double agents12 = myRs.getDouble("agents12");
				double agents13 = myRs.getDouble("agents13");
				
				double agentsAll = myRs.getDouble("agentsAll");
				double combineAll = myRs.getDouble("combineAll");
				double mProduction = myRs.getDouble("mProduction");
				double umProduction = myRs.getDouble("umProduction");
				double subsidyA = myRs.getDouble("subsidyA");
				double subsidyB = myRs.getDouble("subsidyB");
				double subsidy = myRs.getDouble("subsidy");
				double cost = myRs.getDouble("cost");
				double budget = myRs.getDouble("budget");
				double budgetNext = myRs.getDouble("budgetNext");
				double working_captital = myRs.getDouble("working_capital");
				int ATR = myRs.getInt("ATR");
				int YEM = myRs.getInt("YEM");
				double YEP = myRs.getDouble("YEM");
				String temp_uname = myRs.getString("uname");
				
				tempReport = new Report(season, agents1, agents2, 
						agents3, agents4, agents5, agents6, agents7, agents8,
						agents9, agents10, agents11, agents12, agents13, agentsAll, combineAll, 
						mProduction, umProduction, subsidyA, subsidyB, subsidy, 
						cost, budget, budgetNext, working_captital, ATR, YEM, YEP, temp_uname);
				return tempReport;
			}
			else {
				return null;
			}
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public List<Report> getReportGroup (String uname, int seasonNum) throws Exception {
		
		List<Report> reportList = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql;
			
			if(seasonNum % 4 == 1) {
				sql = "select * from reports where uname=? and season=?";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, uname);
				myStmt.setInt(2, seasonNum);
			}
			else if(seasonNum % 4 == 2) {
				sql = "select * from reports where uname=? and (season=? or season=?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, uname);
				myStmt.setInt(2, seasonNum);
				myStmt.setInt(3, seasonNum-1);
			}
			else if(seasonNum % 4 == 3) {
				sql = "select * from reports where uname=? and (season=? or season=? or season=?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, uname);
				myStmt.setInt(2, seasonNum);
				myStmt.setInt(3, seasonNum-1);
				myStmt.setInt(4, seasonNum-2);
			}
			else if(seasonNum % 4 == 0) {
				sql = "select * from reports where uname=? and (season=? or season=? or season=? or season=?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, uname);
				myStmt.setInt(2, seasonNum);
				myStmt.setInt(3, seasonNum-1);
				myStmt.setInt(4, seasonNum-2);
				myStmt.setInt(5, seasonNum-3);
			}
	
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				
				int season = myRs.getInt("season");
				
				double agents1 = myRs.getDouble("agents1");
				double agents2 = myRs.getDouble("agents2");
				double agents3 = myRs.getDouble("agents3");
				double agents4 = myRs.getDouble("agents4");
				double agents5 = myRs.getDouble("agents5");
				double agents6 = myRs.getDouble("agents6");
				double agents7 = myRs.getDouble("agents7");
				double agents8 = myRs.getDouble("agents8");
				double agents9 = myRs.getDouble("agents9");
				double agents10 = myRs.getDouble("agents10");
				double agents11 = myRs.getDouble("agents11");
				double agents12 = myRs.getDouble("agents12");
				double agents13 = myRs.getDouble("agents13");
				
				double agentsAll = myRs.getDouble("agentsAll");
				double combineAll = myRs.getDouble("combineAll");
				double mProduction = myRs.getDouble("mProduction");
				double umProduction = myRs.getDouble("umProduction");
				double subsidyA = myRs.getDouble("subsidyA");
				double subsidyB = myRs.getDouble("subsidyB");
				double subsidy = myRs.getDouble("subsidy");
				double cost = myRs.getDouble("cost");
				double budget = myRs.getDouble("budget");
				double budgetNext = myRs.getDouble("budgetNext");
				double working_captital = myRs.getDouble("working_capital");
				int ATR = myRs.getInt("ATR");
				int YEM = myRs.getInt("YEM");
				double YEP = myRs.getDouble("YEM");
				String temp_uname = myRs.getString("uname");
				
				Report tempReport = new Report(season, agents1, agents2, 
						agents3, agents4, agents5, agents6, agents7, agents8,
						agents9, agents10, agents11, agents12, agents13, agentsAll, combineAll, 
						mProduction, umProduction, subsidyA, subsidyB, subsidy, 
						cost, budget, budgetNext, working_captital, ATR, YEM, YEP, temp_uname);
				reportList.add(tempReport);
			}
			return reportList;
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
	
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// this function returns the number of agents
	public int addReport(String uname, int seasonNum, double campaign, int M_promotion,  
			int M_production, int UM_production, int umNum, int ATR, int YEM, double YEP, HttpServletRequest request) throws Exception
	{
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int agent_count = 0;
		
		try {
			
			//*********************************************************
			//*********************************************************
	
			double [] A_produce_list = new double[13];
			double [] A_produce_list_new = new double[13];
			double [] A_cost_list = new double[13];
			
			myConn = dataSource.getConnection();
			String sql = "SELECT * FROM workers WHERE uname=? AND level=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			for(int i = 0; i <= 12; i++) {
				int A_level = i + 1;
				myStmt.setInt(2, A_level);
				myRs = myStmt.executeQuery();
				while(myRs.next()) {
					agent_count += 1;
					A_produce_list[i] += AtoProduce[i];
					A_cost_list[i] += AtoCost[i];
					//A_num_list[i] += 1;
				}
			}
			
			// add campaign bonus: in the 3rd season of each year, campaign has no effect
			if(seasonNum % 4 != 3) {
				for(int i = 0; i <= 12; i++) {
					A_produce_list_new[i] = A_produce_list[i] * (double)(1+campaign);
				}
			}
			else {
				for(int i = 0; i <= 12; i++) {
					A_produce_list_new[i] = A_produce_list[i];
				}
			}
			
			// add general promotion bonus
			if(M_promotion > 0) {
				for(int i = 0; i <= 12; i++) {
					A_produce_list_new[i] += A_produce_list[i] * 0.1;
				}
			}
			
			close(myConn, myStmt, myRs);
			
			//*********************************************************
			//*********************************************************
			// calculate newAgents1to12, which is the total production of all level 1-12
			// agents that are newly recruited (i.e. their names can't be A, B, C, D, E, F)
			double newRecruitedAgents1to12 = 0;
			
			try {
			  	myConn = dataSource.getConnection();
			  	String sql4 = "SELECT * FROM workers WHERE uname=? AND name<>? "
			  			+ "AND name<>? AND name<>? AND name<>? AND name<>? "
			  			+ "AND name<>? AND level<>?";
			  	myStmt = myConn.prepareStatement(sql4);
			  	myStmt.setString(1, uname);
			  	myStmt.setString(2, "A");
			  	myStmt.setString(3, "B");
			  	myStmt.setString(4, "C");
			  	myStmt.setString(5, "D");
			  	myStmt.setString(6, "E");
			  	myStmt.setString(7, "F");
			  	myStmt.setInt(8, 13);
			  	myRs = myStmt.executeQuery();
						
			  	while(myRs.next()) {
			  		int level = myRs.getInt("level");
			  		newRecruitedAgents1to12 += AtoProduce[level-1];
			  	}
	
			}
			finally {
				close(myConn, myStmt, myRs);
			}
			
			double origValue = newRecruitedAgents1to12;
			
			if(seasonNum % 4 != 3) {
				newRecruitedAgents1to12 += origValue * campaign;
			}
			
			// add general promotion bonus
			if(M_promotion > 0) {
				newRecruitedAgents1to12 += origValue * 0.1;
			}
			
			
			//*********************************************************
			//*********************************************************
			// calculate subsidyA and subsidyB
			
			double agentsAll = 0;
			double cost = 0;
			System.out.println("A_cost_list: " + Arrays.toString(A_cost_list));
			
			for(int i = 0; i <= 12; i++) {
				if(i < 12) {
					agentsAll += A_produce_list_new[i];
					cost += A_cost_list[i];
				}
				else {
					// i == 12
					agentsAll += A_produce_list_new[i];
				}
			}
			
			System.out.println("cost after agentlist: " + cost);
			
			/////////////////////////
			
			double subsidyA = (double)agentsAll * 0.1;
			double subsidyB = (double)newRecruitedAgents1to12 * 0.1;
			
			double subsidy = subsidyA + subsidyB;
			double budgetNext = 2 * subsidy;
			/////////////////////////
			// finishing calculating cost
			cost += subsidy;
			
			System.out.println("cost after adding subsidy: " + cost);
			
			if(campaign < 0.05) {
				cost += 0;
			}
			else if(campaign < 0.15) {
				cost += (125 + 10*agent_count);
			}
			else if(campaign < 0.25) {
				cost += (250 + 20*agent_count);
			}
			else if(campaign < 0.35) {
				cost += (375 + 30*agent_count);
			}
		
			System.out.println("cost after campaign: " + cost);
			
			//////////////////////////////
			double mProduction = 0;
			double umProduction = 0;
			double combineAll;
			
			if(M_production > 0) {
				mProduction = 1000;
			}
			if(umNum > UM_production) {
				umProduction = 2000 * UM_production;
			}
			else {
				umProduction = 2000 * umNum;
			}
			
			double mProductionNew = mProduction;
			double umProductionNew = umProduction;
			
			// add campaign bonus: in the 3rd season of each year, campaign has no effect
			if(seasonNum % 4 != 3) {
				mProductionNew += mProduction * campaign;
				umProductionNew += umProduction * campaign;
			}
			
			// add general promotion bonus
			if(M_promotion > 0) {
				mProductionNew += mProduction * 0.1;
				umProductionNew += umProduction * 0.1;
			}
			
			
			combineAll = agentsAll + mProductionNew + umProductionNew;
			///////////////////////////////
			
			double budget ;
			if((seasonNum % 4) == 1) {
				budget = 4816.0;
			}
			else {
					
				int lastSeasonNum = seasonNum - 1;
				myConn = dataSource.getConnection();
				String sql2 = "SELECT * FROM reports WHERE season=? AND uname=?";
				myStmt = myConn.prepareStatement(sql2);
				myStmt.setInt(1, lastSeasonNum);
				myStmt.setString(2, uname);
				myRs = myStmt.executeQuery();
					
				if(myRs.next()) {
					double lastSeasonBudget = myRs.getDouble("budget");
					double lastSeasonCost = myRs.getDouble("cost");
					double lastSeasonBudgetNext = myRs.getDouble("budgetNext");
					budget = lastSeasonBudget - lastSeasonCost + lastSeasonBudgetNext;
				}
				else {
					budget = 0.0;
				}
				
				close(myConn, myStmt, myRs);
			}
				
			
			///////////////////////////////
			
			double working_capital;
			double ratio = cost / budget;
			
			if(ratio > 2.0) {
				working_capital = 0;
			}
			else if(ratio > 1.1) {
				working_capital = 0.45*subsidy;
			}
			else if(ratio > 0.9) {
				working_capital = subsidy;
			}
			else if(ratio > 0.75) {
				working_capital = 1.2*subsidy;
			}
			else {
				working_capital = 1.25*subsidy;
			}
			
			///////////////////////////////

			myConn = dataSource.getConnection();
			String sql3 = "INSERT IGNORE INTO reports "
						 + "(season, agents1, agents2, agents3, agents4, agents5, agents6, "
						 + "agents7, agents8, agents9, agents10, agents11, agents12, agents13, "
						 + "agentsAll, combineAll, "
						 + "mProduction, umProduction, subsidyA, subsidyB, subsidy, "
						 + "cost, budget, budgetNext, working_capital, ATR, YEM, YEP, uname) "
						 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
						 + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql3);
			// set the param values for the student
			myStmt.setInt(1, seasonNum);
			
			myStmt.setDouble(2, A_produce_list_new[0]);
			myStmt.setDouble(3, A_produce_list_new[1]);
			myStmt.setDouble(4, A_produce_list_new[2]);
			myStmt.setDouble(5, A_produce_list_new[3]);
			myStmt.setDouble(6, A_produce_list_new[4]);
			myStmt.setDouble(7, A_produce_list_new[5]);
			myStmt.setDouble(8, A_produce_list_new[6]);
			myStmt.setDouble(9, A_produce_list_new[7]);
			myStmt.setDouble(10, A_produce_list_new[8]);
			myStmt.setDouble(11, A_produce_list_new[9]);
			myStmt.setDouble(12, A_produce_list_new[10]);
			myStmt.setDouble(13, A_produce_list_new[11]);
			myStmt.setDouble(14, A_produce_list_new[12]);
			
			myStmt.setDouble(15, agentsAll);
			myStmt.setDouble(16, combineAll);
			myStmt.setDouble(17, mProductionNew);
			myStmt.setDouble(18, umProductionNew);
			myStmt.setDouble(19, subsidyA);
			myStmt.setDouble(20, subsidyB);
			myStmt.setDouble(21, subsidy);
			myStmt.setDouble(22, cost);
			myStmt.setDouble(23, budget);
			myStmt.setDouble(24, budgetNext);
			myStmt.setDouble(25, working_capital);
			myStmt.setInt(26, ATR);
			// only use season1 YEM & YEP
			// this is handled before this function is called (in WorkerControllerServlet)
			myStmt.setInt(27, YEM);
			myStmt.setDouble(28, YEP);
			myStmt.setString(29, uname);

			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		return agent_count;
	}

	public void addUMCost(int seasonNum, String uname) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			
			String sql = "UPDATE reports SET cost=cost+1600 "
					+ "WHERE season=? AND uname=?";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, seasonNum);
			myStmt.setString(2, uname);
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	public void deleteAll(String uname) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "DELETE FROM reports WHERE uname=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, uname);
			
			myStmt.execute(); 
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	


}
	
	
