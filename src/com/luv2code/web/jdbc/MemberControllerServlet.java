package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class MemberControllerServlet
 */
@WebServlet("/MemberControllerServlet")
public class MemberControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberDbUtil memberDbUtil;
	
	@Resource(name="jdbc/project1")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create db util ... and pass in the conn pool / datasource
		try {
			memberDbUtil = new MemberDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			  
			  // read the command parameter
			  String theCommand = request.getParameter("command");
			  if(theCommand == null) {
				  theCommand = "LIST";
			  }
			  // route to the appropriate method
			  switch(theCommand) {
				case "LIST":
			  		listMembers(request, response);
			  		break;
				default:
					listMembers(request, response);
			  }
			 
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
				  theCommand = "DEL_NAME";
			  }
			  // route to the appropriate method
			  switch(theCommand) {
			  
			  	case "DEL_NAME":
			  		deleteMembersByName(request, response);
			  		break;
			  		
				case "DEL_DATE":
					deleteMembersByDate(request, response);
			  		break;
			  	
				case "DEL_ALL":
					deleteMembersAll(request, response);
			  }
			  
			}
			catch (Exception exc){
				throw new ServletException(exc);
			}
	}

	private void deleteMembersAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		memberDbUtil.deleteMembersAll();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}


	private void deleteMembersByDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String del_year = request.getParameter("del_members_date_year");
		String del_month = request.getParameter("del_members_date_month");
		String del_day = request.getParameter("del_members_date_day");
		
		String del_date = del_year + '-' + del_month + '-' + del_day;
		System.out.println("del_date: " + del_date);
		memberDbUtil.deleteMembersByDate(del_date);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}


	private void deleteMembersByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] del_members_names = request.getParameterValues("del_members_names");
		for (int i = 0; i < del_members_names.length; i++) {
			String uname = del_members_names[i];
			if(uname != "" && uname != null) {
				memberDbUtil.deleteMemberByName(uname);
			}
	    }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}


	private void listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Member> members = memberDbUtil.getMembers();
				
		request.setAttribute("MEMBERS_LIST", members);
						
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-members.jsp");
		dispatcher.forward(request, response);
	}
	
	
	

}
