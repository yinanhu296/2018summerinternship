<%@ page import ="java.sql.*" %>
<%
    String uname = request.getParameter("uname");    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1",
            "root", "Tytphlzy96");
    Statement st1;
    ResultSet rs1;
    Statement st2;
    ResultSet rs2;
    st1 = con.createStatement();
    rs1 = st1.executeQuery("select * from members where uname='" + uname + "'");
    if (rs1.next()) {
    	
    	// record user(member, player) name
        session.setAttribute("uname", uname);
        
        // record which season to submit decision form, 1 = "year 1 season 1", 5 = "year 2 season 1"
        session.setAttribute("seasonNum", 0);
        
        // how many Unit Managers are available 
        session.setAttribute("umNum", 0);
        
        // count how many consecutive recruiting agent seasons
        session.setAttribute("recruitingContinueScore", 0);
        
        // sum of recruiting points through all consecutive recruiting agent seasons
        session.setAttribute("recruitingAccumulativePoint", 0);
        
        // count how many consecutive recruiting UM seasons
        session.setAttribute("recruitingUMContinueScore", 0);
        
        // sum of recruiting points through all consecutive recruiting UM seasons
        session.setAttribute("recruitingUMAccumulativePoint", 0);
        
        // index to initialize new recruit names
 		session.setAttribute("ANameIndex", 0);
 		session.setAttribute("UMNameIndex", 0);
 		
 		// count how many "ask established agents to become UM" before an
 		// agent is converted to UM
 		session.setAttribute("UMaskContinueScore", 0);
 		
 		// name of the new UM who is hired in last season
 		String newUMName = "None";
 		session.setAttribute("newUMName", newUMName);
 		
 		// name of the new UM who is newly hired but not trained enough, so it will fall off
 		session.setAttribute("newUMUntrained", "");
        
        
        st2 = con.createStatement();
        
        // agents given at the begining of the game
        st2.execute("INSERT IGNORE INTO workers (name, level, training, uname) VALUES ('A', 13, 0, '" + uname + "')");
        st2.execute("INSERT IGNORE INTO workers (name, level, training, uname) VALUES ('B', 13, 0, '" + uname + "')");
        st2.execute("INSERT IGNORE INTO workers (name, level, training, uname) VALUES ('C', 5, 0, '" + uname + "')");
        st2.execute("INSERT IGNORE INTO workers (name, level, training, uname) VALUES ('D', 8, 0, '" + uname + "')");
        st2.execute("INSERT IGNORE INTO workers (name, level, training, uname) VALUES ('E', 9, 0, '" + uname + "')");
        st2.execute("INSERT IGNORE INTO workers (name, level, training, uname) VALUES ('F', 10, 0, '" + uname + "')");
        
        //response.sendRedirect("user-input.jsp");
        response.sendRedirect("login-redirect.jsp");
    } else {
        out.println("Invalid User Name <a href='index.jsp'>try again</a>");
    }
%>