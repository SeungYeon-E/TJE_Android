<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");

	String url_mysql = "jdbc:mysql://localhost/phonebook?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";

  	String A = "select uname, uphone, uaddress, uemail uimage from user where uid = ?";
  	int result = 0; // 입력 확인 
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

         ps = conn_mysql.prepareStatement(A);
	    ps.setString(1, userid);
		
		result = ps.executeUpdate();
%>
		{
			"result" : "<%=result%>",
			"name" : "<%=rs.getString(1) %>", 
			"phone" : "<%=rs.getString(2) %>",   
			"address" : "<%=rs.getString(3) %>",  
			"email" : "<%=rs.getString(4) %>",
			"image" : "<%=rs.getString(5) %>"
		}

<%		
	    conn_mysql.close();
	} 
	catch (Exception e){
%>
		{
			"result" : "<%=result%>"
		}
<%		
	    e.printStackTrace();
	} 
	
%>