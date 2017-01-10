<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make Your Wish</title>
<script type="text/javascript" src="jquery.js"></script>

<script type="text/javascript">

</script>
<link href="style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 7]>
<style>
.gradient-ie h1 span {
    background: none;
    filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/gradient-white.png', sizingMethod='scale');
}
</style>
<![endif]-->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/layout1.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/dock-example1.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/stack-example3.css">

<noscript>
<style type="text/css">
#dock {
	top: -32px;
}

a.dock-item {
	position: relative;
	float: left;
	margin-right: 10px;
}

.dock-item span {
	display: block;
}

.stack {
	top: 0;
}

.stack ul li {
	position: relative;
}
</style>
</noscript>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="js/fisheye-iutil.min.js"></script>
<script type="text/javascript" src="js/dock-example1.js"></script>
<script type="text/javascript" src="js/stack-1.js"></script>

</head>
<body background="images/hbw.jpg">
<f:view>
	<div id="user_info" align="right" style="height: 40px">
	<div style="font-size: 15px;padding-top: 3px">
	<form method="post" action="loginSuccess.jsp">
		<i>
		<%
			String res = request.getParameter("result");
			if(res == null || res.length()==0) {
				
			}
			else {
				out.println(res);
			}
		%>
		</i>
		<img id="login_icon" src="images/User-128.png" style="width: 35px; height: 35px; vertical-align: middle"/>
		<label style="padding-right:5px; vertical-align: middle">Username</label>
		<input id="username" name="username" type="text" style="height: 18px;padding-right:10px;vertical-align: middle"/>
		<label style="padding-right:5px;vertical-align: middle">Password</label>
		<input id="password" name="password" type="password" style="height: 18px;padding-right:10px;vertical-align: middle"/>
		<input style="height: 25px; width: 99px;margin-left:10px;margin-right:10px;vertical-align: middle;" value="Login" type="submit">
	</form>
	</div>
	</div>
	<div align="center" style="width: 85%">
	<div class="gradient4" align="right">
	<h1 align="right" style="width: 50%"><span></span>Make Your Wish</h1>
	<h2>Infosys Ltd.</h2>
	</div>
	
	<br>
	</div>	

	<!-- BEGIN STACK ============================================================ -->
	<div class="stack"><img src="images/stacks/stack.png" alt="stack" />
	<ul id="stack">	
		<li><a href="http://sparsh"><span>Infosys Official Site</span><img
			src="images/stacks/infy.png" alt="Infosys" /></a></li>		
		<li><a href="http://sparsh"><span>Sparsh</span><img
			src="images/stacks/sparsh.png" alt="Sparsh" /></a></li>		
		<li><p><a href="signup.html" rel="facebox"><span>Sign&nbsp;Up</span><img
			src="images/stacks/add-user.png" alt="Sign Up" /></a></p></li>		
	</ul>
	</div>
	<!-- end div .stack -->
	<!-- END STACK ============================================================ -->
</f:view>
</body>
</html>