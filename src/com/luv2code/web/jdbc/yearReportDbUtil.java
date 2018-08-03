package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class yearReportDbUtil {
	
	private DataSource dataSource;
	
	public yearReportDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public void addYearReport (String uname, int seasonNum, int expectedATR, int expectedYEM, int actualYEM, double expectedYEP, boolean penalty) throws Exception {
		
		if(seasonNum <= 0) {
			return;
		}
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		
		
		double sumSubsidy = 0;
		double sumWorkingCapital = 0;
		double sumMProduction = 0;
		int sumATR = 0;
		double renewalCommission = 0;
		double additionalSubsidy = 0;
		
		
		//*************************************************************
		//*************************************************************
		// combine 1-4 seasons, sum up report info
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
			
			if(seasonNum == 4) {
				additionalSubsidy = 3000;
			}
			if(seasonNum == 8) {
				additionalSubsidy = 1800;
			}
			
			while(myRs.next()) {
				sumSubsidy += myRs.getDouble("subsidy");
				sumWorkingCapital += myRs.getDouble("working_capital");
				sumMProduction += myRs.getDouble("mProduction");
				sumATR += myRs.getInt("ATR");
			}
	
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		//*************************************************************
		//*************************************************************
		// for calculating renewalCommission, summing up combineAll from last year
		double sumCombineAllLast = 0;
		if(seasonNum >= 5) {
			try {
				int yearNum = (seasonNum-1) / 4; // (5, 6, 7, 8)-->1; (9, 10, 11, 12)-->2
				int seasonBase = (yearNum-1) * 4 + 1;   // 1-->1 ; 2-->5; 3-->9
				myConn = dataSource.getConnection();
				String sql;
				sql = "select * from reports where uname=? and (season=? or season=? or season=? or season=?)";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, uname);
				myStmt.setInt(2, seasonBase);
				myStmt.setInt(3, seasonBase+1);
				myStmt.setInt(4, seasonBase+2);
				myStmt.setInt(5, seasonBase+3);
				myRs = myStmt.executeQuery();
				
				while(myRs.next()) {
					sumCombineAllLast += myRs.getDouble("combineAll");
				}
				renewalCommission = sumCombineAllLast*0.08;
			}
			finally {
				close(myConn, myStmt, myRs);
			}
		}
		else {
			renewalCommission = 72642 * 0.08;
		}
		
		//*************************************************************
		//*************************************************************
		// for calculating actualYEM, summing up combineAll from this year, only calculated at the 
		// end of the year, otherwise actualYEM is 0
		double sumCombineAll = 0;
		if(seasonNum % 4 == 0) {
		  try {
			  	int yearNum = (seasonNum-1) / 4; // 4-->0; 8-->1
			  	int seasonBase = yearNum * 4 + 1;   // 1-->1 ; 2-->5; 3-->9
			  	myConn = dataSource.getConnection();
			  	String sql;
			  	sql = "select * from reports where uname=? and (season=? or season=? or season=? or season=?)";
			  	myStmt = myConn.prepareStatement(sql);
			  	myStmt.setString(1, uname);
			  	myStmt.setInt(2, seasonBase);
			  	myStmt.setInt(3, seasonBase+1);
			  	myStmt.setInt(4, seasonBase+2);
			  	myStmt.setInt(5, seasonBase+3);
			  	myRs = myStmt.executeQuery();
						
			  	while(myRs.next()) {
			  		sumCombineAll += myRs.getDouble("combineAll");
			  	}
	
		  }
		  finally {
				close(myConn, myStmt, myRs);
		  }
		}
		
		//*************************************************************
		//*************************************************************
		// calculate total score
		double totalScore = 0;
		double thisSubsidy = 0;
		double thisWorkingCapital = 0;
		double thisMProduction = 0;
		double thisRenewalCommission = 0;
		double thisAdditionalSubsidy = 0;
		
		// one time add on
		if(seasonNum % 4 == 0) {
			thisRenewalCommission = renewalCommission;
			thisAdditionalSubsidy = additionalSubsidy;
		}
		
		
		// get totalScore from last season's year_reports
		if(seasonNum % 4 == 1) {
			// beginning of year totalScore must be recalculated
			totalScore = 0;
		}
		else {
			// can get totalScore from last season
			try {
				myConn = dataSource.getConnection();
				String sql = "select * from year_reports where uname=? and season=?";
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, uname);
				myStmt.setInt(2, seasonNum-1);
				myRs = myStmt.executeQuery();
				if(myRs.next()) {
					totalScore = myRs.getDouble("total_score");
				}
			}
			finally {
				close(myConn, myStmt, myRs);
			}
		}
		
		// get data from reports to add to totalScore
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from reports where uname=? and season=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, seasonNum);
			myRs = myStmt.executeQuery();
			if(myRs.next()) {
				thisSubsidy = myRs.getDouble("subsidy");
				thisWorkingCapital = myRs.getDouble("working_capital");
				thisMProduction = myRs.getDouble("mProduction");
			}
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		
		totalScore = totalScore + thisSubsidy + thisWorkingCapital + thisMProduction + thisAdditionalSubsidy + thisRenewalCommission;
		System.out.println("totalScore before adding ratios is: " + totalScore);
		
		// add bonus or penalties to totalScoreRatio
		double totalScoreRatio = 1.0;
		
		// if administrative work is not done, lose 10%
		if(penalty == true) {
			totalScoreRatio -= 0.1;
		}
		
		// deviation calculation only makes sense at the end of year! 
		// actualYEM is only valid at the end of year && sumCombineAll is only valid at the end of year
		if(seasonNum % 4 == 0) {
			// percentage of how much the prediction deviates from the actual
			double YEMDeviation = ((double)actualYEM - (double)expectedYEM) / (double) actualYEM;
			YEMDeviation = Math.abs(YEMDeviation);
			// deviate x% : (1-x%) / 50  one can gain at most 2% bonus if predicts exactly right
			double YEMAddRatio = (1.0-YEMDeviation) / 50.0;
			if(YEMAddRatio < 0) {
				YEMAddRatio = 0.0;
			}
		
			// same as above   actualYEP = sumCombineAll
			double YEPDeviation = ((double)sumCombineAll - (double)expectedYEP) / (double) sumCombineAll;
			YEPDeviation = Math.abs(YEPDeviation);
			// deviate x% : (1-x%) / 50  one can gain at most 2% bonus if predicts exactly right
			double YEPAddRatio = (1.0-YEPDeviation) / 50.0;
			if(YEMAddRatio < 0) {
				YEPAddRatio = 0.0;
			}
		
			totalScoreRatio += YEMAddRatio;
			totalScoreRatio += YEPAddRatio;
		}
		
		totalScore = totalScore * totalScoreRatio;
		
		//*************************************************************
		//*************************************************************
		// add a year_report
		try {
			
			myConn = dataSource.getConnection();
			String sql2 = "INSERT IGNORE INTO year_reports "
					   + "(season, subsidy, working_capital, mProduction, renewal_commission, "
					   + "additional_subsidy, total_score, expected_ATR, expected_YEM, actual_YEM, "
					   + "expected_YEP, actual_YEP, uname) "
					   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql2);
	
			myStmt.setInt(1, seasonNum);
			myStmt.setDouble(2, sumSubsidy);
			myStmt.setDouble(3, sumWorkingCapital);
			myStmt.setDouble(4, sumMProduction);
			myStmt.setDouble(5, renewalCommission);
			myStmt.setDouble(6, additionalSubsidy);
			myStmt.setDouble(7, totalScore);
			myStmt.setInt(8, sumATR);
			myStmt.setInt(9, expectedYEM);
			
			if(seasonNum % 4 == 0) {
				myStmt.setInt(10, actualYEM);
			}
			else {
				myStmt.setInt(10, 0);
			}
			myStmt.setDouble(11, expectedYEP);
			
			if(seasonNum % 4 == 0) {
				myStmt.setDouble(12, sumCombineAll);
			}
			else {
				myStmt.setDouble(12, 0.0);
			}
			
			myStmt.setString(13, uname);
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
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

	public yearReport getYearReport (String uname, int seasonNum) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from year_reports where uname=? and season=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, seasonNum);
			myRs = myStmt.executeQuery();
			yearReport tempYearReport = null;
			
			if(myRs.next()) {
			
				int season = myRs.getInt("season");
				double subsidy = myRs.getDouble("subsidy");
				double working_capital = myRs.getDouble("working_capital");
				double mProduction = myRs.getDouble("mProduction");
				double renewal_commision = myRs.getDouble("renewal_commission");
				double additional_subsidy = myRs.getDouble("additional_subsidy");
				double total_score = myRs.getDouble("total_score");
				int expected_ATR = myRs.getInt("expected_ATR");
				int expected_YEM = myRs.getInt("expected_YEM");
				int actual_YEM = myRs.getInt("actual_YEM");
				double expected_YEP = myRs.getDouble("expected_YEP");
				double actual_YEP = myRs.getDouble("actual_YEP");
				String temp_uname = myRs.getString("uname");
				
				tempYearReport = new yearReport(season, subsidy, working_capital, 
						mProduction, renewal_commision, additional_subsidy, total_score, 
						expected_ATR, expected_YEM, actual_YEM, expected_YEP, actual_YEP, temp_uname);
				
			}
			return tempYearReport;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}

	public double calcFinalScore(String uname) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		double finalScore = 0;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from year_reports where uname=? and (season=? or season=? or season=?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, 4);
			myStmt.setInt(3, 8);
			myStmt.setInt(4, 12);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				finalScore += myRs.getDouble("total_score");
				System.out.println("fs12: " + myRs.getDouble("total_score"));
			}
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		// update score in members table 
		try {
			myConn = dataSource.getConnection();
			String sql = "update members set score=? where uname=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setDouble(1, finalScore);
			myStmt.setString(2, uname);
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	
		return finalScore;
	}

	public void deleteAll(String uname) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "delete from year_reports where uname=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, uname);
			
			myStmt.execute(); 
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}
		
		

}
