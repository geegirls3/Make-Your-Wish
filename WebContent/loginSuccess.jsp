<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.services.wbservice"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	out.println(username);
	out.println(password);
	wbservice service = new wbservice();
	int ret = service.checkuser(username,password);
	if(ret==2)
	{
%>
  		<jsp:forward page="Admin_Approve.jsp">
			<jsp:param value="success" name="result"/>
		</jsp:forward>
<%	
	}
	else if(ret == 1) {
		session.setAttribute("user_curr",username);
		String email = service.returnEmail(username);
		session.setAttribute("email_curr",email);
%>
  		<jsp:forward page="home.jsp">
			<jsp:param value="success" name="result"/>
		</jsp:forward>
<%		
	}
	else {
%>
  		<jsp:forward page="login.jsp">
			<jsp:param value="Invalid Username/Password entered!!! Please re-enter your credentials" name="result"/>
		</jsp:forward>
<%
	}
%>
</body>
</html>