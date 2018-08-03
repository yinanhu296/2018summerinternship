package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

public class UMDbUtil {
	private DataSource dataSource;
	public UMDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<UM> getUMs(String get_uname) throws Exception {
		List<UM> umList = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			// create sql statement
			String sql = "select * from unit_managers where uname=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setString(1, get_uname);
			// execute query
			myRs = myStmt.executeQuery();
			
			// process result set
			while(myRs.next()) {
	
				String name = myRs.getString("name");
				String this_uname = myRs.getString("uname");
				int training = myRs.getInt("training");
				
				// create new student object
				UM tempUM = new UM(name, this_uname, training);
				// add it to the list of students
				umList.add(tempUM);
			}
			// close JDBC objects
			return umList;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	public int countUMs(String get_uname) throws Exception {
		int countUMNum = 0;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "SELECT * FROM unit_managers WHERE uname=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, get_uname);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				countUMNum++;
			}
			
			return countUMNum;
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
	
	public void addUM(UM theUM) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			// create sql for insert
			String sql = "INSERT IGNORE INTO unit_managers "
					   + "(name, uname, training) "
					   + "VALUES (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			// set the param values for the worker
			myStmt.setString(1, theUM.getName());
			myStmt.setString(2, theUM.getUname());
			myStmt.setInt(3, 0);
			
			System.out.println("in addUM: " + theUM);

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteUM(String Dname, String Duname) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			// int studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from unit_managers where name=? and uname=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, Dname);
			myStmt.setString(2, Duname);
			
			// execute sql statement
			myStmt.execute(); 
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteAll(String uname) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "delete from unit_managers where uname=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, uname);
			
			myStmt.execute(); 
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	public void trainUM(String newUMName, String uname) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "UPDATE unit_managers SET training=training+1 where name=? and uname=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, newUMName);
			myStmt.setString(2, uname);
			
			System.out.println("UPDATE umName =" + newUMName);
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteNoTrainingUMs(String uname) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "DELETE FROM unit_managers WHERE uname=? AND training=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, uname);
			myStmt.setInt(2, 0);
			
			myStmt.execute(); 
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}
		

}
