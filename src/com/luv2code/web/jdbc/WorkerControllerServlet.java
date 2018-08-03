package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class WorkerControllerServlet
 */
@WebServlet("/WorkerControllerServlet")
public class WorkerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String[] ANames;
	static final String[] UMNames;
	static final Double[] UMaskprobability;
	static { UMaskprobability = new Double[5];
			 UMaskprobability[0]=0.0; 
			 UMaskprobability[1]=0.25; 
			 UMaskprobability[2]=0.5; 
			 UMaskprobability[3]=0.75; 
			 UMaskprobability[4]=1.0; 
	}
	
	static { ANames=new String[65]; 
			 ANames[0]="G"; ANames[1]="H"; ANames[2]="I"; ANames[3]="J"; 
			 ANames[4]="K"; ANames[5]="L"; ANames[6]="M"; ANames[7]="N"; 
			 ANames[8]="O"; ANames[9]="P";  ANames[10]="Q"; ANames[11]="R"; 
			 ANames[12]="S"; ANames[13]="T"; ANames[14]="U"; ANames[15]="V";
			 ANames[16]="W"; ANames[17]="X"; ANames[18]="Y"; ANames[19]="Z";
			 ANames[20]="AA"; ANames[21]="BB"; ANames[22]="CC"; ANames[23]="DD";
			 ANames[24]="EE"; ANames[25]="FF"; ANames[26]="GG"; ANames[27]="HH";
			 ANames[28]="II"; ANames[29]="JJ"; ANames[30]="KK"; ANames[31]="LL";
			 ANames[32]="MM"; ANames[33]="NN"; ANames[34]="OO"; ANames[35]="PP";
			 ANames[36]="QQ"; ANames[37]="RR"; ANames[38]="SS"; ANames[39]="TT";
			 ANames[40]="UU"; ANames[41]="VV"; ANames[42]="WW"; ANames[43]="XX";
			 ANames[44]="YY"; ANames[45]="ZZ"; ANames[46]="AAA"; ANames[47]="BBB";
			 ANames[48]="CCC"; ANames[49]="DDD"; ANames[50]="EEE"; ANames[51]="FFF";
			 ANames[52]="GGG"; ANames[53]="HHH"; ANames[54]="III"; ANames[55]="JJJ";
			 ANames[56]="KKK"; ANames[57]="LLL"; ANames[58]="MMM"; ANames[59]="NNN";
			 ANames[60]="OOO"; ANames[61]="PPP"; ANames[62]="QQQ"; ANames[63]="RRR";
			 ANames[64]="SSS"; 
		   }
	
	static { UMNames=new String[13]; 
	 		 UMNames[0]="a"; UMNames[1]="b"; UMNames[2]="c"; UMNames[3]="d";
	 		 UMNames[4]="e"; UMNames[5]="f"; UMNames[6]="g"; UMNames[7]="h";
	 		 UMNames[8]="i"; UMNames[9]="j"; UMNames[10]="k"; UMNames[11]="l";
	 		 UMNames[12]="m"; 
		   }

	private WorkerDbUtil workerDbUtil;
	private UMDbUtil umDbUtil;
	private ReportDbUtil reportDbUtil;
	private yearReportDbUtil yearReportDbUtil;

	
	@Resource(name="jdbc/project1")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		// create db util ... and pass in the conn pool / datasource
		try {
			workerDbUtil = new WorkerDbUtil(dataSource);
			umDbUtil = new UMDbUtil(dataSource);
			reportDbUtil = new ReportDbUtil(dataSource);
			yearReportDbUtil = new yearReportDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listReports(request, response);
		}
		catch (Exception exc){
			throw new ServletException(exc);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		  
		  // read the command parameter
		  String theCommand = request.getParameter("command");
		  if(theCommand == null) {
			  theCommand = "NEXT";
		  }
		  // route to the appropriate method
		  switch(theCommand) {
		  
		  	case "NEXT":
		  		requestUserInput(request, response);
		  		break;
		  		
			case "LIST":
		  		listWorkers(request, response);
		  		break;
		  	
		  	case "ADD":
		  		boolean ret = checkInput(request, response);
		  		System.out.println("checkInput outputs: " + ret);
		  		if(ret == true) {
		  			addReport(request, response);
		  			addWorker(request, response);
		  			redirect(request, response);
		  		}
		  		break;
		  		
		  	case "FIN":
		  		deleteUserData(request, response);
		  		break;
		  }
		  
		}
		catch (Exception exc){
			throw new ServletException(exc);
		}
	}


	// before addReport & addWorker, first check if time allocation is correct
	// if not correct, go back to user input page
	private boolean checkInput(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String uname = (String)session.getAttribute("uname");
		
		String[] training_names = request.getParameterValues("training_names");
		String[] training_points = request.getParameterValues("training_points");
		
		int M_training1 = myGetParameterInt(request, "M_training1");
		int M_training2 = myGetParameterInt(request, "M_training2");
		int M_training36 = myGetParameterInt(request, "M_training36");
		int M_training712 = myGetParameterInt(request, "M_training712");
		int M_trainingE = myGetParameterInt(request, "M_trainingE");
		
		int UM_training1 = myGetParameterInt(request, "UM_training1");
		int UM_training2 = myGetParameterInt(request, "UM_training2");
		int UM_training36 = myGetParameterInt(request, "UM_training36");
		int UM_training712 = myGetParameterInt(request, "UM_training712");
		int UM_trainingE = myGetParameterInt(request, "UM_trainingE");
		
		int new_UM_training1 = myGetParameterInt(request, "new_UM_training1");
		int new_UM_training2 = myGetParameterInt(request, "new_UM_training2");
		
		int total_training1 = M_training1 + UM_training1 + new_UM_training1;
		int total_training2 = M_training2 + UM_training2 + new_UM_training2;
		int total_training36 = M_training36 + UM_training36;
		int total_training712 = M_training712 + UM_training712;
		int total_trainingE = M_trainingE + UM_trainingE;
		
		// there could be "" in training names
		boolean valid = workerDbUtil.checkInput(request, training_names, training_points, total_training1, total_training2, total_training36, 
				total_training712, total_trainingE, uname);
		
		if(!valid) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/wrong-input.jsp");
			dispatcher.forward(request, response);
			return false;
		}
		return true;
	}

	

	// delete or keep user data based on choice "saveData"
	private void deleteUserData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int saveData = Integer.parseInt(request.getParameter("saveData"));
		// if user choose not to save user data, delete them from db (except data in "members" table)
		if(saveData == 0) {
			HttpSession session = request.getSession();
			String uname = (String)session.getAttribute("uname");
			workerDbUtil.deleteAll(uname);
			umDbUtil.deleteAll(uname);
			reportDbUtil.deleteAll(uname);
			yearReportDbUtil.deleteAll(uname);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/redirect.jsp");
		dispatcher.forward(request, response);
	}

	private void addWorker(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		HttpSession session = request.getSession();
		String uname = (String)session.getAttribute("uname");
		int seasonNum = (int)session.getAttribute("seasonNum");
		
		// read from form

		String[] A_discharge_names = request.getParameterValues("A_discharge_names");
		session.setAttribute("A_discharge_names", A_discharge_names);
		
		String[] UM_discharge_names = request.getParameterValues("UM_discharge_names");
		session.setAttribute("UM_discharge_names", UM_discharge_names);
		
		String[] training_names = request.getParameterValues("training_names");
		String[] training_points = request.getParameterValues("training_points");
		
		
		//****************************************************************
		//****************************************************************
		//* add no_training, train agents, see if any new agent is not trained, see if anyone not training for a long time
		
		// add one no_training points to EVERY non-established agent
		// Although this add one no_training points to agents who will be trained
		// trainWorker() will zero out this change, before deleteNoTrainingWorkers() is called
		workerDbUtil.updateWorkersNoTraining(uname);
		
		for (int i = 0; i < training_names.length; i++) {
			// read training names, updated workers will have no_training set to 0
			if(training_names[i] != "") {
	        	String name = training_names[i];
	        	int training_times;
	        	if(training_points[i] == "") {
	        		training_times = 0;
	        	}
	        	else {
	        		training_times = Integer.parseInt(training_points[i]);
	        	}
	        	
	        	// each agent is trained up to its assigned training time
	        	for(int j = 0; j < training_times; j++) {
	        		workerDbUtil.trainWorker(name, uname);
	        	}
			}
	    }

		// All untrained newly recruit agents must fall off
		workerDbUtil.deleteLevelOneWorkers(uname);
		
		// Untrained agents gradually fall off
		workerDbUtil.deleteNoTrainingWorkers(uname);
		
		//****************************************************************
		//****************************************************************
		//* train new UM from last season.
				
		int M_trainingUM = 0;
		String M_trainingUMString = request.getParameter("M_trainingUM");
		if(M_trainingUMString == "") {
			M_trainingUM = 0;
		}
		else {
			M_trainingUM = Integer.parseInt(M_trainingUMString);
		}
				
		int new_UM_trainingUM = 0;
		String new_UM_trainingUMString = request.getParameter("new_UM_trainingUM");
		if(new_UM_trainingUMString == "") {
			new_UM_trainingUM = 0;
		}
		else {
			new_UM_trainingUM = Integer.parseInt(new_UM_trainingUMString);
		}
				
		String newUMName = (String)session.getAttribute("newUMName");
		
		// if a new UM was hired last season it will be trained if M_trainingUM >= 1 && new_UM_trainingUM >= 2
		if(M_trainingUM >= 1 && new_UM_trainingUM >= 2) {
			// not enough UM training time, the new UM falls off
			System.out.println("M_trainingUM: " + M_trainingUM);
			System.out.println("new_UM_trainingUM: " + new_UM_trainingUM);
			umDbUtil.trainUM(newUMName, uname);
		}
		
		// delete all untrained UM
		umDbUtil.deleteNoTrainingUMs(uname);
		
		//****************************************************************
		//****************************************************************
		//* Recruit Agents
		List<String> A_hire_names = new ArrayList<>();

		
		boolean is_recruitingA = false;
		int numAgentsToRecruit = 0;
		int total_recruitingA = 0;
		
		//**** section to get total recruitingA points from three input areas ****//
		
		int M_recruitingA = myGetParameterInt(request, "M_recruitingA");
//		String M_recruitingAString = request.getParameter("M_recruitingA");
//		if(M_recruitingAString == "" || M_recruitingAString == null) {
//			M_recruitingA = 0;
//		}
//		else {
//			M_recruitingA = Integer.parseInt(M_recruitingAString);
//		}
		
		int new_UM_recruitingA = myGetParameterInt(request, "new_UM_recruitingA");
		
		int UM_recruitingA = myGetParameterInt(request, "UM_recruitingA");
		
		total_recruitingA = M_recruitingA + new_UM_recruitingA + UM_recruitingA;
		System.out.println("M_recruitingA: " + M_recruitingA);
		System.out.println("new_UM_recruitingA: " + new_UM_recruitingA);
		System.out.println("UM_recruitingA: " + UM_recruitingA);
		System.out.println("total_recruitingA: " + total_recruitingA);
		//****  ****//
		
		if(total_recruitingA == 0) {
			session.setAttribute("recruitingContinueScore", 0);
			session.setAttribute("recruitingAccumulativePoint", 0);
			is_recruitingA = false;
		}
		else {
			int recruitingAccumulativePoint = (int)session.getAttribute("recruitingAccumulativePoint");
			int recruitingContinueScore = (int)session.getAttribute("recruitingContinueScore");
			recruitingAccumulativePoint += total_recruitingA;
			recruitingContinueScore += 1;
			session.setAttribute("recruitingAccumulativePoint", recruitingAccumulativePoint);
			session.setAttribute("recruitingContinueScore", recruitingContinueScore);
			
			double mean = recruitingContinueScore - 1 + 
						  (double)(recruitingAccumulativePoint - 1.5*recruitingContinueScore) / 4.0;
			
			Random random = new Random();
			// generate a random number following normal distribution with mean=mean and standard deviation=1
			double mySample = random.nextGaussian() * 1 + mean;
			// down casting 1.94 --> 1
			numAgentsToRecruit = (int) mySample;
			if(mySample < 0) {
				is_recruitingA = false;
			}
			else if(numAgentsToRecruit <= 0) {
				is_recruitingA = false;
			}
			else {
				is_recruitingA = true;
			}
		}
		
		if(is_recruitingA == true) {
			int baseIndex = (int) session.getAttribute("ANameIndex");
			// this for loop will be executed at least once
			for (int i = 0; i < numAgentsToRecruit; i++) {
				A_hire_names.add(ANames[baseIndex+i]);
			}
			// at the end of loop reset ANameIndex to new base index
			session.setAttribute("ANameIndex", baseIndex+numAgentsToRecruit);
			
			for (int i = 0; i < A_hire_names.size(); i++) {
				// create new agent add to db
		        String name = A_hire_names.get(i);
		        Worker theWorker = new Worker(name, 1, 0, 0, uname);
		        workerDbUtil.addWorker(theWorker);
		    }
		}
		
		session.setAttribute("A_HIRE_NAMES", A_hire_names);
		request.setAttribute("A_HIRE_NUM", A_hire_names.size());
		
		//****************************************************************
		//****************************************************************
		// Recruit new UM for this season
		
		int UMask = Integer.parseInt(request.getParameter("UMask"));
		int M_recruitingUM = myGetParameterInt(request, "M_recruitingUM");
		boolean is_recruitingUM_Agent = false;
		boolean is_recruitingUM_New = false;
		int recruitingUMAccumulativePoint = 0;
		int recruitingUMContinueScore = 0;
		
		if(M_recruitingUM > 0) {
			recruitingUMAccumulativePoint = (int)session.getAttribute("recruitingUMAccumulativePoint");
			recruitingUMContinueScore = (int)session.getAttribute("recruitingUMContinueScore");
			recruitingUMAccumulativePoint += M_recruitingUM;
			recruitingUMContinueScore += 1;
			session.setAttribute("recruitingUMAccumulativePoint", recruitingUMAccumulativePoint);
			session.setAttribute("recruitingUMContinueScore", recruitingUMContinueScore);
			is_recruitingUM_New = true;
		}
		else {
			session.setAttribute("recruitingUMContinueScore", 0);
			session.setAttribute("recruitingUMAccumulativePoint", 0);
			is_recruitingUM_New = false;
		}

		
		if(UMask == 1) {
			int UMaskContinueScore = (int)session.getAttribute("UMaskContinueScore") + 1;
			Random rand = new Random(); 
			
			// a random number between (0, 1)
			double value = rand.nextDouble(); 
			if(value < UMaskprobability[UMaskContinueScore]) {
				is_recruitingUM_Agent = true;
				session.setAttribute("UMaskContinueScore", 0);
			}
			else {
				is_recruitingUM_Agent = false;
				session.setAttribute("UMaskContinueScore", UMaskContinueScore);
			}
		}
		
		
		// at most one UM is hired, with "promoting agent to become a UM" having the priority
		if(is_recruitingUM_Agent == true) {
			// returns the name of a random established(level 13) agent
			String agentToPromote = workerDbUtil.choosePromote(uname);
			if(agentToPromote != null) {
				UM theUM = new UM(agentToPromote, uname, 0);
				System.out.println("Before entering addUM: " + agentToPromote);
				umDbUtil.addUM(theUM);
				workerDbUtil.deleteWorker(agentToPromote, uname);
				
				session.setAttribute("newUMName", agentToPromote);
				
				// a new hired UM cost 1600 for this season
				reportDbUtil.addUMCost(seasonNum, uname);
				// set new added UM name
				session.setAttribute("UM_HIRE_NAME", agentToPromote);
			}
		}
		else if (is_recruitingUM_New == true){
			
			double mean = (double)recruitingUMContinueScore*0.5 - 1 + 
					  (double)(recruitingUMAccumulativePoint) * 0.5;
		
			Random random = new Random();
			// generate a random number following normal distribution with mean=mean and standard deviation=1
			double mySample = random.nextGaussian() * 1 + mean;
			// down casting 1.94 --> 1
			int numUMsToRecruit = (int) mySample;
			boolean confirm_recruitingUM_New;
			if(mySample < 0) {
				confirm_recruitingUM_New = false;
			}
			else if(numUMsToRecruit < 1) {
				confirm_recruitingUM_New = false;
			}
			else {
				confirm_recruitingUM_New = true;
			}
			
			if(confirm_recruitingUM_New == true) {
				int baseIndex = (int) session.getAttribute("UMNameIndex");
				String umToHire = UMNames[baseIndex];
				UM theUM = new UM(umToHire, uname, 0);
				umDbUtil.addUM(theUM);
				session.setAttribute("UMNameIndex", baseIndex+1);
			
				session.setAttribute("newUMName", umToHire);
			
				// a new hired UM cost 1600 for this season
				reportDbUtil.addUMCost(seasonNum, uname);
				// set new added UM name
				session.setAttribute("UM_HIRE_NAME", umToHire);
				
				// * reset recruitingUMAccumulativePoint to 0 if successfully recruited a UM
				session.setAttribute("recruitingUMAccumulativePoint", 0);
			}
		}
		else {
			session.setAttribute("newUMName", "None");
			session.setAttribute("UM_HIRE_NAME", "");
		}
		
		// *------------------------------------
		// *delete agents & UMs listed to discharge in the form
		
		for (int i = 0; i < A_discharge_names.length; i++) {
			// read discharge_names 
			String name = A_discharge_names[i];
			// delete worker from db
			workerDbUtil.deleteWorker(name, uname);
	    }
		
		for (int i = 0; i < UM_discharge_names.length; i++) {
			String name = UM_discharge_names[i];
			if(name != "" && name != null) {
				umDbUtil.deleteUM(name, uname);
			}
	    }
		
	}

	// generate report based on user's current situation and decision
	private void addReport(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		// read from form
		HttpSession session = request.getSession();
		String uname = (String)session.getAttribute("uname");
		int umNum = umDbUtil.countUMs(uname);
		int seasonNum = (int)session.getAttribute("seasonNum");
		double campaign = Double.parseDouble(request.getParameter("campaign"));
		
		
		int M_production;
		String MProductionString = request.getParameter("M_production");
		if (MProductionString == "") {
			M_production = 0;
		}
		else {
			M_production =  Integer.parseInt(MProductionString);
		}
		
		// ****   ****//
		int total_UM_production = 0;
		
		int UM_production;
		String UM_ProductionString = request.getParameter("UM_production");
		if (UM_ProductionString == "") {
			UM_production = 0;
		}
		else {
			UM_production = Integer.parseInt(UM_ProductionString);
		}
		
		int new_UM_production;
		String new_UM_ProductionString = request.getParameter("new_UM_production");
		if (new_UM_ProductionString == "") {
			new_UM_production = 0;
		}
		else {
			new_UM_production = Integer.parseInt(new_UM_ProductionString);
		}
		total_UM_production = UM_production + new_UM_production;
		// ****    ****//
		
		int M_promotion;
		String MPromotionString = request.getParameter("M_promotion");
		if (MPromotionString == "") {
			M_promotion = 0;
		}
		else {
			M_promotion = Integer.parseInt(MPromotionString);
		}
		
		int ATR = myGetParameterInt(request, "ATR");
		//**********//int ATR = Integer.parseInt(request.getParameter("ATR"));
		
		int YEM = 0;
		double YEP = 0;
		
		// record prediction on season 1 of each year
		if(seasonNum % 4 == 1) {
			YEM = Integer.parseInt(request.getParameter("YEM"));
			YEP = Double.parseDouble(request.getParameter("YEP"));
			session.setAttribute("season1_YEM", YEM);
			session.setAttribute("season1_YEP", YEP);
		}
		else {
			// use recorded season1 predictions
			YEM = (int)session.getAttribute("season1_YEM");
			YEP = (double)session.getAttribute("season1_YEP");
		}
				
		// add report to db, this function returns the number of agents(lv 1-12 + professional)
		int agentCount = reportDbUtil.addReport(uname, seasonNum, campaign, M_promotion, M_production, total_UM_production, 
								umNum, ATR, YEM, YEP, request);
		
		// actual_manpower = number of agents + number of UMs + 1 (Manager)
		int actualYEM = agentCount + umNum + 1;
		Boolean penalty = true;
		if(request.getParameter("M_admin") != "") {
			int M_admin = Integer.parseInt(request.getParameter("M_admin"));
			if(M_admin >= 1) {
				penalty = false;
			}
		}
		yearReportDbUtil.addYearReport(uname, seasonNum, ATR, YEM, actualYEM, YEP, penalty);
	}


	private void listReports(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");
		int seasonNum = (Integer) session.getAttribute("seasonNum");

		// get reports
		Report retReport = reportDbUtil.getReport(uname, seasonNum);
		List<Report> retReportGroup = reportDbUtil.getReportGroup(uname, seasonNum);
		yearReport retYearReport = yearReportDbUtil.getYearReport(uname, seasonNum);
		
		// clean "" entries in the two String arrays
		String[] A_discharge_names = (String []) session.getAttribute("A_discharge_names");
		String[] UM_discharge_names = (String []) session.getAttribute("UM_discharge_names");
		ArrayList<String> A_discharge_names_clean = toNonEmptyArray(A_discharge_names);
		ArrayList<String> UM_discharge_names_clean = toNonEmptyArray(UM_discharge_names);
		 
			
		// add to the request
		request.setAttribute("RET_REPORT", retReport);
		request.setAttribute("RET_REPORT_GROUP", retReportGroup);
		request.setAttribute("RET_YEAR_REPORT", retYearReport);
		
		request.setAttribute("A_DISCHARGE_NAMES", A_discharge_names_clean);
		request.setAttribute("UM_DISCHARGE_NAMES", UM_discharge_names_clean);
		
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-reports.jsp");
		dispatcher.forward(request, response);
	}



	private void listWorkers(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		HttpSession session = request.getSession();
		String uname = (String) session.getAttribute("uname");

		List<Worker> workersList = workerDbUtil.getWorkers(uname);
		List<UM> umsList = umDbUtil.getUMs(uname);
		
		request.setAttribute("WORKERS_LIST", workersList);
		request.setAttribute("UMS_LIST", umsList);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-workers.jsp");
		dispatcher.forward(request, response);
	}
	
	private void requestUserInput(HttpServletRequest request, HttpServletResponse response) 
	throws Exception {
		
		HttpSession session = request.getSession();
		int seasonNum = (Integer) session.getAttribute("seasonNum");
		String uname = (String) session.getAttribute("uname");
		
		if(seasonNum >= 12) {
			double final_score = yearReportDbUtil.calcFinalScore(uname);
			System.out.println("final_score: " + final_score);
			session.setAttribute("final_score", final_score);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/fin-page.jsp");
			dispatcher.forward(request, response);
		}
		
		// add seasonNumber right before redirecting to next user input form
		session.setAttribute("umNum", umDbUtil.countUMs(uname));
		session.setAttribute("seasonNum", seasonNum+1);

		List<Worker> workersList = workerDbUtil.getWorkers(uname);
		List<UM> umsList = umDbUtil.getUMs(uname);
		
		request.setAttribute("WORKERS_LIST", workersList);
		request.setAttribute("UMS_LIST", umsList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-input.jsp");
		dispatcher.forward(request, response);
		
	}
	
	////////// Helper Functions ///////////////
	private int myGetParameterInt(HttpServletRequest request, String attribute) {
		int ret = 0;
		String retString = request.getParameter(attribute);
		if(retString == null || retString == "") {
			ret = 0;
		}
		else {
			ret = Integer.parseInt(retString);
		}
		return ret;
	}
	
	public static <T> int getLength(T[] arr){
	    int count = 0;
	    for(T el : arr)
	        if (el != "")
	            ++count;
	    return count;
	}
	
	public ArrayList<String> toNonEmptyArray(String[] firstArray){
		ArrayList<String> secondArray = new ArrayList<String>();
		for (String s : firstArray) {
		    if (!s.equals("")) {
		    	secondArray.add(s);
		    }
		}
		return secondArray;
	}
	

}
