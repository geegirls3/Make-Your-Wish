<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.entities.*" %>
<%@ page import="com.services.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.activation.*" %>
<%@page import="com.services.CalendarUtility"%>
<%@page import="com.services.wbservice"%>
<%@page import="com.entities.user_info"%>
<%@ page import="com.entities.wishbook" %>
<%@ page import="com.services.wbservice" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="css/slider-style.css" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/mocha.js"></script>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<style type="text/css">
#topLinks{position:relative;}
#topLinks li{margin:0;padding:0;list-style:none;position:absolute;top:0;}
#topLinks li, #topLinks a{height:25px;display:block;}

#twitterLink{left:0px;width:25px;}
#twitterLink{background:url('images/home/social_main.png') 0 0;}

#facebookLink{left:30px;width:25px;}
#facebookLink{background:url('images/home/social_main.png') -30px 0;}

#webLink{left:60px;width:25px;}
#webLink{background:url('images/home/social_main.png') -60px 0;}

#logoutLink{left:87px;width:25px;}
#logoutLink{background:url('images/home/social_main.png') -87px 0;}

#userLinks{position:relative;}
#userLinks li{margin:0;padding:0;list-style:none;position:absolute;top:0;}
#userLinks li, #userLinks a{height:25px;display:block;}

#userLinkedin{left:0px;width:25px;}
#userLinkedin{background:url('images/home/social_author.png') 0 0;}

#userTwitter{left:30px;width:25px;}
#userTwitter{background:url('images/home/social_author.png') -30px 0;}

#userFacebook{left:60px;width:25px;}
#userFacebook{background:url('images/home/social_author.png') -60px 0;}

#userEmail{left:87px;width:24px;}
#userEmail{background:url('images/home/social_author.png') -87px 0;}

#menuList{position:relative;}
#menuList li{margin:0;padding:0;list-style:none;position:absolute;top:0;}
#menuList li, #menuList a{height:36px;display:block;}

#leftOfMenu{left:0px;width:5px;}
#leftOfMenu{background:url('images/home/menu.png') 0 0;}

#home{left:5px;width:57px;}
#home{background:url('images/home/menu.png') -5px 0;}
#home a:hover{background: url('images/home/menu.png') -5px -36px;}

#inbox{left:62px;width:53px;}
#inbox{background:url('images/home/menu.png') -62px 0;}
#inbox a:hover{background: url('images/home/menu.png') -62px -36px;}

#wishbook{left:115px;width:55px;}
#wishbook{background:url('images/home/menu.png') -115px 0;}
#wishbook a:hover{background: url('images/home/menu.png') -115px -36px;}

#about{left:170px;width:66px;}
#about{background:url('images/home/menu.png') -176px 0;}
#about a:hover{background: url('images/home/menu.png') -176px -36px;}

#rightOfMenu{left:236px;width:364px;}
#rightOfMenu{background:url('images/home/menu.png') -236px 0;}

#menuLinks li{list-style:none;}
#menuLinks li, #menuLinks a{height:22px;display:block;margin-top: 10px;}

#addFriendsLink{left:0px;width:126px;}
#addFriendsLink{background:url('images/home/topics_right.png') 0 -5px;}

#wishlistLink{left:0px;width:126px;}
#wishlistLink{background:url('images/home/topics_right.png') 0 -35px;}

#seasonsLink{left:0px;width:126px;}
#seasonsLink{background:url('images/home/topics_right.png') 0 -65px;}

#customLink{left:0px;width:126px;}
#customLink{background:url('images/home/topics_right.png') 0 -95px;}

#faqLink{left:0px;width:126px;}
#faqLink{background:url('images/home/topics_right.png') 0 -125px;}

#faqPane {
	background: url('images/home/bg_featured.png');
	background-repeat: no-repeat;
	height: 220px;
	width: 600px;
	position: relative; 
	left: 7px;		
}

#top {
	margin-top: 5px;
	height: 52px;
	margin-left: 27px;
}

#mainHeadLogo {
	display: inline; 
	float: left;
}

#topLinks {
	width: 113px;
	display: inline;
	float: right;
	margin-top: 23px;
	padding-right: 18px;
}

#left {
	width: 650px;
	display: inline-block;
}

#menu {
	background-color: silver;
	margin-top: 5px;
	position: relative;
	left: 32px
}

#search {
	margin-top: 4px;
	height: 52px;
}

#searchText {
	width: 296px;
	margin-top: 15px;
	margin-left: 0px;
	background: url('images/home/searchtext.png');
	background-color: #ebebeb;
	background-repeat: no-repeat;
	float: left;
	display: inline-block;
}

#sideMenu {
	width: 350px;
	height: 638px;
	background: url('images/home/bg_right.png');
	background-repeat: no-repeat;
}

#searchInput {
	width: 296px; 
	height: 32px;  
	padding-top: 5px; 
	padding-left: 10px;
	background-image: url('images/home/searchtext.png');
	background-repeat: no-repeat;
	background-color: #ebebeb;
	font-size: 17px;
	color: gray;
	text-align: left;
}

#searchButton {
	height: 33px;
	width: 25px;
	float: right;
	display: inline-block;
	margin-right: 20px;
	margin-top: 18px;
}

#sideMenu {
	margin-top: 4px;
}

#addFriendPane {
	position: relative;
	left: 7px;
	font-size: 12px;
	margin-top: 15px;
	
}

#addFriendPaneTop {
	width: 600px;
	height: 20px;
	background-image: url('images/home/content_back_front.png');
	background-repeat: no-repeat;
	margin-top: 15px;
}

#addFriendPaneMain {
	width: 600px;
	background-image: url('images/home/content_back.png');
	background-repeat: repeat-y;
	margin-top: 0px;
	padding-top: 0px;
	font-family: sans-serif;
	text-align: left;
	padding-left: 15px;
	margin-left: 15px;
}

#addFriendPaneBottom {
	width: 600px;
	height: 20px;
	background-image: url('images/home/content_back_down.png');
	background-repeat: no-repeat;
}

#menuBottom {
	position: relative;
	left: 7px;
	margin-top: 41px;
	height: 67px;
	width: 600px;
	background-image: url('images/home/topMenuBottom.png');
	background-repeat: no-repeat;
	font-family: sans-serif;
	font-size: 11px;
	text-align: left;
	padding-left: 15px;
	margin-left: 15px;
	padding-top: 5px;
	color: white;
}

#menuBottomFAQLinkImage {
	float: left; 
	padding-top: 2px;
}

#menuBottomFAQLink {
	text-decoration: none; 
	color: white; 
	float: left; 
	padding-left: 5px;
}

#newFriendEntryList {
	
}

#addFriendBullet {
	float: left;
}

#addFriendName {
	float: left;
	text-decoration: none;
	color: gray;
}

#msgText {
	width: 565px; 
	height: 75px; 
	margin-top: 15px; 
	padding-top: 15px; 
	border-style: solid;
	border-color: #cbcacf; 
	border-width: 2px;
}

#postMsg {
	height: 30px; 
	width: 88px; 
	margin-top: 15px; 
	cursor: hand; 
	background-image: url('images/home/button_back.png'); 
	background-repeat: no-repeat; 
	font-family: sans-serif; 
	font-weight: bold; 
	color: gray; 
	padding-right: 10px; 
	font-size: 12px; 
	padding-bottom: 2px
}

.topicStyle {
	font-weight: bold; 
	color: gray;
}

.buttonStyle {
	height: 30px; 
	width: 88px; 
	margin-top: 15px; 
	cursor: hand; 
	background-image: url('images/home/button_back.png'); 
	background-repeat: no-repeat; 
	font-family: sans-serif; 
	font-weight: bold; 
	color: gray; 
	padding-right: 10px; 
	font-size: 12px; 
	padding-bottom: 2px;
}

</style>
</head>
<body background="images/home/bg_gradient.png" style="background-repeat: repeat-x; background-color: #fafafa">
<f:view>
	<center>
	<div id="main" style="width: 1024px;">
		<div id="left">
			<div id="top">
				<img id="mainHeadLogo" alt="Make Your Wish" src="images/home/logo_sitehead.png" height="52px" width="179px" align="left">
				<ul id="topLinks">
					<li id="twitterLink"><a href="http://www.twitter.com"></a></li>
					<li id="facebookLink"><a href="http://www.facebook.com"></a></li>
					<li id="webLink"><a href="http://sparsh"></a></li>
					<li id="logoutLink"><a href="logout.jsp"></a></li>
				</ul>
			</div>			
			<div id="addFriendPane">
				<div id="addFriendPaneTop">
				</div>
				<div id="addFriendPaneMain">
					<table width="550px" style="color: gray;">
<%
						String uname=request.getParameter("uname");
						wbservice wb=new wbservice();
						List<user_info_temp> rs=wb.retrieveuser(uname);
						int size=rs.size();
						String mail=new String();
%>
						<tr>
    						<td colspan="2" style="font-size: 25px; color: #0b92d6; float: none;">
    							<img alt="Profile" src="images/Member Card.png" width="50px" height="50px" style="float: left;"/>
    							<div style="float: left; margin-top: 10px;">User Details</div>
    						</td>    							
    					</tr>
<%
						for(int i=0;i<size;i++)
						{
							user_info_temp s=(user_info_temp)rs.get(i);
							mail=s.getEmail();
							Date anni=s.getAnniversary();
							String ann=new String();
							if(anni!=null && anni.toString().length()!=0 )
							{
							Calendar c1=Calendar.getInstance();
							c1.setTime(s.getAnniversary());
							ann=CalendarUtilityDefault.getStringFromCalendar(c1);
							}
							Calendar c2=Calendar.getInstance();
							c2.setTime(s.getDob());
							String dob=CalendarUtilityDefault.getStringFromCalendar(c2);
							
							
%>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle" width="100px">User Id</td>
    							<td width="450px"><%= s.getUserId()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">First Name</td>
    							<td><%= s.getFirstName()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Last Name</td>
    							<td><%= s.getLastName()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Username</td>
    							<td><%= s.getUname()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Street Address</td>
    							<td><%=s.getStreetAddress() %><br/><%= s.getAddressLine2()%></td>
    						</tr>    						
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">City</td>
    							<td><%= s.getCity()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">State</td>
    							<td><%= s.getState()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Pin</td>
    							<td><%= s.getPin()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Country</td>
    							<td><%= s.getCountry()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Phone No</td>
    							<td><%= s.getPhoneNo()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Email</td>
    							<td><%= s.getEmail()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">DOB</td>
    							<td><%=dob%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<%if(anni!=null && anni.toString().length()!=0 ){ %>
    						<tr>
    							<td class="topicStyle">Anniversary</td>
    							<td><%=ann%></td>
    						</tr>
    						<tr height="10px"></tr><%} %>
    						<tr>
    							<td class="topicStyle">Facebook page</td>
    							<td><%= s.getFb()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Twitter Page</td>
    							<td><%= s.getTw()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Linkedin</td>
    							<td><%= s.getLi()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						<tr>
    							<td class="topicStyle">Personal Email</td>
    							<td><%= s.getPe()%></td>
    						</tr>
    						<tr height="10px"></tr>
    						
<%
							session.setAttribute("user_info",s);
						}
%>
					</table>
					<form action="sample.jsp">
						<input type="submit" value="Accept" name="asd" class="buttonStyle"/>
					</form>
					<form action="rejectadmin.jsp">
						<input type="hidden" value=<%= mail%> name="mail"/>
						<input type="submit" value="Reject" name="asd" class="buttonStyle"/>
					</form>
				</div>
				<div id="addFriendPaneBottom">
				</div>
			</div>
		</div>		
	</div>
	</center>
</f:view>
</body>
</html>