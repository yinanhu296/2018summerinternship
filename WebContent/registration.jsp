<%@ page import ="java.sql.*" %>
<%
    String uname = request.getParameter("uname");    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1",
            "root", "Tytphlzy96");
    Statement st = con.createStatement();
    int i = st.executeUpdate("INSERT IGNORE INTO members(uname,regdate) VALUES ('" + uname + "'," + "CURDATE())");
    if (i > 0) {
        response.sendRedirect("welcome.jsp");
    } else {
        response.sendRedirect("index.jsp");
    }
%>