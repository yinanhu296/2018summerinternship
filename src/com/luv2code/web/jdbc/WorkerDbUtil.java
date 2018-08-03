package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class WorkerDbUtil {
	private DataSource dataSource;
	public WorkerDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Worker> getWorkers(String get_uname) throws Exception {
		List<Worker> workerList = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			// create sql statement
			String sql = "select * from workers where uname=?";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setString(1, get_uname);
			// execute query
			myRs = myStmt.executeQuery();
			
			// process result set
			while(myRs.next()) {
				// retrieve data from result set row
				String name = myRs.getString("name");
				int level = myRs.getInt("level");
				int training = myRs.getInt("training");
				int no_training = myRs.getInt("no_training");
				String this_uname = myRs.getString("uname");
				
				
				// create new student object
				Worker tempWorker = new Worker(name, level, training, no_training, this_uname);
				// add it to the list of students
				workerList.add(tempWorker);
			}
			// close JDBC objects
			return workerList;
		}
		finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
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

	public void addWorker(Worker theWorker) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			// create sql for insert
			String sql = "INSERT IGNORE INTO workers "
					   + "(name, level, training, uname) "
					   + "VALUES (?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			// set the param values for the worker
			
			// a trick equivalent to setChar
			myStmt.setString(1, String.valueOf(theWorker.getName()));
			myStmt.setInt(2, theWorker.getLevel());
			myStmt.setInt(3, theWorker.getTraining());
			myStmt.setString(4, theWorker.getUname());

			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}

	public void deleteWorker(String Dname, String Duname) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from workers where name=? and uname=?";
			
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

	// train agents so they can level up: 
	// * level 1 agents need 1 training point to update to level 2
	// * level 2-12 agents need 2 training points to update to the next level 
	public void trainWorker(String Tname, String Tuname) throws Exception{
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql1 = "select * from workers where name=? and uname=?";
			myStmt = myConn.prepareStatement(sql1);

			myStmt.setString(1, Tname);
			myStmt.setString(2, Tuname);
	
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				
				String name = myRs.getString("name");
				String uname = myRs.getString("uname");
				int old_level = myRs.getInt("level");
				
				if(old_level == 1) {
		
					String sql = "UPDATE workers "
							+ "SET level=?, training=?, no_training=? "
							+ "WHERE name=? AND uname=?";
					
					myStmt = myConn.prepareStatement(sql);
					myStmt.setInt(1, 2);
					myStmt.setInt(2, 0);
					myStmt.setInt(3, 0);
					myStmt.setString(4, name);
					myStmt.setString(5, uname);
					myStmt.execute();
					
				}
				else if(myRs.getInt("training")==1) {
					
					int new_level;
					if(old_level <= 12) {
						new_level = old_level + 1;
					}
					else {
						new_level = 13;
					}
					
					String sql = "UPDATE workers "
							+ "SET level=?, training=?, no_training=? "
							+ "WHERE name=? AND uname=?";
					myStmt = myConn.prepareStatement(sql);
					myStmt.setInt(1, new_level);
					myStmt.setInt(2, 0);
					myStmt.setInt(3, 0);
					myStmt.setString(4, name);
					myStmt.setString(5, uname);
					myStmt.execute();
					
				}
				else {
					String sql = "UPDATE workers "
							+ "SET training=?, no_training=? "
							+ "WHERE name=? AND uname=?";
					myStmt = myConn.prepareStatement(sql);
					myStmt.setInt(1, 1);
					myStmt.setInt(2, 0);
					myStmt.setString(3, name);
					myStmt.setString(4, uname);
					myStmt.execute();
				}
			
			}
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	public String choosePromote(String uname) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		String nameToPromote = null;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from workers where uname=? and level=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, 13);
			myRs = myStmt.executeQuery();
			
			
			if(myRs.next()) {
				nameToPromote = myRs.getString("name");
				
			}
			// close JDBC objects
			return nameToPromote;
		}
		finally {
			close(myConn, myStmt, myRs);
		}

	}
	
	// delete new agents(level 1) who are not trained immediately after they are hired
	public void deleteLevelOneWorkers(String uname) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "delete from workers where level=? and uname=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, 1);
			myStmt.setString(2, uname);
			
			myStmt.execute(); 
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	// delete agents with high no_training point, the higher the point the more likely
	// that agent will be deleted. Established agent will NOT be deleted
	public void deleteNoTrainingWorkers(String uname) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		//-------------------------------
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from workers where uname=? and no_training=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, 2);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				String name = myRs.getString("name");
				int level = myRs.getInt("level");
				Random rand = new Random(); 
				double value = rand.nextDouble(); 
				if(value < 0.25 && level < 13) {
					deleteWorker(name, uname);
				}
			}
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		//-------------------------------
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from workers where uname=? and no_training=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, 3);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				String name = myRs.getString("name");
				int level = myRs.getInt("level");
				Random rand = new Random(); 
				double value = rand.nextDouble(); 
				if(value < 0.5 && level < 13) {
					deleteWorker(name, uname);
				}
			}
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		//-------------------------------
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from workers where uname=? and no_training=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, 4);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				String name = myRs.getString("name");
				int level = myRs.getInt("level");
				Random rand = new Random(); 
				double value = rand.nextDouble(); 
				if(value < 0.75 && level < 13) {
					deleteWorker(name, uname);
				}
			}
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		//-------------------------------
		try {
			myConn = dataSource.getConnection();
			
			String sql = "select * from workers where uname=? and no_training=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, 5);
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				String name = myRs.getString("name");
				int level = myRs.getInt("level");
				if(level < 13) {
					deleteWorker(name, uname);
				}
			}
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	// add one no_training points to EVERY non-established(level 13) agent
	public void updateWorkersNoTraining(String uname) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			
			String sql = "UPDATE workers SET no_training=no_training+1 "
					+ "WHERE uname=? and level<>?";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, uname);
			myStmt.setInt(2, 13);
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	public void deleteAll(String uname) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "delete from workers where uname=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, uname);
			
			myStmt.execute(); 
		}
		finally {
			close(myConn, myStmt, null);
		}
		
	}

	public boolean checkInput(HttpServletRequest request, String[] training_names, String[] training_points, int total_training1,
			int total_training2, int total_training36, int total_training712, int total_trainingE, String uname) 
	throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		double[] required_training_array = new double[14];
		
		for (int i = 0; i < training_names.length; i++) {
			
			if(training_names[i] != "") {
	        	String training_name = training_names[i];
	        	int training_time = 0;
	        	if(training_points[i] == "") {
	        		training_time = 0;
	        	}
	        	else {
	        		training_time = Integer.parseInt(training_points[i]);
	        	}
	        	
	        	try {
	    			myConn = dataSource.getConnection();
	    			
	    			String sql = "SELECT * FROM workers WHERE name=? and uname=?";
	    			myStmt = myConn.prepareStatement(sql);
	    			myStmt.setString(1, training_name);
	    			myStmt.setString(2, uname);
	    			myRs = myStmt.executeQuery();
	    			
	    			if(myRs.next()) {
	    				int training_level = myRs.getInt("level");
	    				incrementArray(required_training_array, training_level, training_time);
	    			}
	    		}
	    		finally {
	    			close(myConn, myStmt, myRs);
	    		}
			}
	    }
		
		double required_training36 = required_training_array[3]+required_training_array[4]+
							  		 required_training_array[5]+required_training_array[6];
		double required_training712 = required_training_array[7]+required_training_array[8]+
									  required_training_array[9]+required_training_array[10]+
									  required_training_array[11]+required_training_array[12];
		
//		System.out.println("time required for training lv1: " + required_training_array[1]);
//		System.out.println("time allocated for training lv1: " + total_training1);
//		System.out.println("time required for training lv2: " + required_training_array[2]);
//		System.out.println("time allocated for training lv2: " + total_training2);
//		System.out.println("time required for training lv3-6: " + required_training36);
//		System.out.println("time allocated for training lv3-6: " + total_training36);
//		System.out.println("time required for training lv7-12: " + required_training712);
//		System.out.println("time allocated for training lv7-12: " + total_training712);
//		System.out.println("required_training_array: " + Arrays.toString(required_training_array));
		
		HttpSession session = request.getSession();
		if(required_training_array[1] > total_training1) {
			session.setAttribute("wrongInputNum", 1);
			session.setAttribute("wrongInputGiven", total_training1);
			session.setAttribute("wrongInputRequired", required_training_array[1]);
			return false;
		}
		else if(required_training_array[2] > total_training2) {
			session.setAttribute("wrongInputNum", 2);
			session.setAttribute("wrongInputGiven", total_training2);
			session.setAttribute("wrongInputRequired", required_training_array[2]);
			return false;
		}
		else if(required_training36 > total_training36) {
			session.setAttribute("wrongInputNum", 36);
			session.setAttribute("wrongInputGiven", total_training36);
			session.setAttribute("wrongInputRequired", required_training36);
			return false;
		}
		else if(required_training712 > total_training712) {
			session.setAttribute("wrongInputNum", 712);
			session.setAttribute("wrongInputGiven", total_training712);
			session.setAttribute("wrongInputRequired", required_training712);
			return false;
		}
		else {
			return true;
		}
	}

	private void incrementArray(double[] required_training_array, int training_level, int training_time) {
		if(training_level == 1) {
			required_training_array[training_level] += (1 * training_time);
		}
		if(training_level == 2) {
			required_training_array[training_level] += ((1.0/2.0) * training_time);
		}
		if(training_level >= 3 && training_level <= 6) {
			//System.out.println("training_level: " + training_level + " training_time: " + training_time);
			required_training_array[training_level] += ((1.0/3.0) * training_time);
			//System.out.println((double)(1/3) * training_time);
		}
		if(training_level >= 7 && training_level <= 12) {
			required_training_array[training_level] += ((1.0/4.0) * training_time);
		}
		if(training_level == 13) {
			required_training_array[training_level] += ((1.0/8.0) * training_time);
		}
		
	}

}
