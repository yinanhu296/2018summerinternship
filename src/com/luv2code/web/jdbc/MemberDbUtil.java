package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MemberDbUtil {
	
private DataSource dataSource;
	
	public MemberDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Member> getMembers() throws Exception {
		List<Member> members = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "SELECT * FROM members ORDER BY score DESC";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				String uname = myRs.getString("uname");
				Double score = myRs.getDouble("score");
	
				Member tempMember = new Member(uname, score);
				members.add(tempMember);
			}
			return members;
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

	public void deleteMemberByName(String uname) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "DELETE FROM members WHERE uname=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteMembersByDate(String del_date) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "DELETE FROM members WHERE regdate=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, del_date);
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
		// delete from YOUR_TABLE where your_date_column < '2009-01-01';
		
	}

	public void deleteMembersAll() throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "DELETE FROM members";
			myStmt = myConn.prepareStatement(sql);
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}
	
}
