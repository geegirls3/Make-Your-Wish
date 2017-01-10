<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="javax.mail.search.*" %>
<%@ page import="javax.activation.*" %>
<%@page import="com.services.wbservice"%>
<%@page import="com.entities.user_info"%>
<%@page import="com.services.*"%>


<%@page import="com.sun.mail.imap.protocol.FLAGS"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox</title>
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
	float: left;
}

#right {
	width: 350px;
	height: 768px;
	display: inline-block;
	float: right;
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

#mailPane {
	position: relative;
	left: 7px;
	font-size: 12px;
	margin-top: 15px;
}

#mailPaneTop {
	width: 600px;
	height: 20px;
	background-image: url('images/home/content_back_front.png');
	background-repeat: no-repeat;
	margin-top: 15px;
}

#mailPaneMain {
	width: 600px;
	background-image: url('images/home/content_back.png');
	background-repeat: repeat-y;
	margin-top: 0px;
	padding-top: 0px;
	font-family: sans-serif;
}

#mailPaneBottom {
	width: 600px;
	height: 20px;
	background-image: url('images/home/content_back_down.png');
	background-repeat: no-repeat;
}

.inboxHead {
	background-color: #0788d9; 
	color: white; 
	text-align: center; 
	vertical-align: middle; 
	font-weight: bold;
	border-color: #0788d9;
	height: 25px;
	vertical-align: middle;
}

#inboxMailList {
	border-width: 0px;
	width: 550px;
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
			<div id="menu">
				<ul id="menuList">
					<li id="leftOfMenu"><a href="default.asp"></a></li>
  					<li id="home"><a href="home.jsp"></a></li>
  					<li id="inbox"><a href="inbox.jsp"></a></li>
  					<li id="wishbook"><a href="wishbook_external.jsp"></a></li>
  					<li id="about"><a href="about.jsp"></a></li>
  					<li id="rightOfMenu"></li>
				</ul>
			</div>
			<div id="menuBottom">
				<%
						String host = "10.122.13.10";
						String username = (String)session.getAttribute("user_curr");
						//String username = "deepakkrishnan";
						wbservice service = new wbservice();
						List<user_info> currUserInfo = service.retrieveProfileInfo(username);
						int size1=currUserInfo.size();
						String user = service.returnEmail(username);
						String password = "infy@123";						
						Session session4 = Session.getInstance(new Properties());

						try
						{
	    					// Get a Store object
    						Store store = session4.getStore("pop3");
    						store.connect(host, user, password);

						    // Get "INBOX"
    						Folder fldr = store.getFolder("INBOX");
    						fldr.open(Folder.READ_WRITE);
    						int count = fldr.getMessageCount();
    	
 							Message message[] = fldr.getMessages(); 							

					%>
				<b style="font-size: 12px;">Hi <%= service.returnName(service.returnEmail(username)) %>!</b><br/>
				There are <%= count %> messages in your inbox...<br/>
				<img id="menuBottomFAQLinkImage" src="images/home/bullet.png" height="12px" width="9px"/><a id="menuBottomFAQLink" href="faq.jsp">Go to FAQs</a>
			</div>
			<div id="mailPane">
				<div id="mailPaneTop">
				</div>
				<div id="mailPaneMain">
							<table id="inboxMailList">								
<%
								if(message.length!=0)
								{
								int fromno=message.length-1;
    							for (int i=fromno; i>=0 && i>(fromno-10); i--)
    							{
        							Message m = message[i];
        							// Get some headers
        							Date date = m.getSentDate();
        							Calendar temp = Calendar.getInstance();
        							temp.setTime(date);
        							Address [] from = m.getFrom();
        							String fromMail = from[0].toString();
        							String subj = m.getSubject();
        							String mimeType = m.getContentType();
        							Object o = m.getContent();
        							        							
        							       								
%>
									<tr style="color: gray;">
										<td>
											<a href="open_mail.jsp?fromCurr=<%= fromMail %>&subjectCurr=<%= subj %>&dateCurr=<%= date.toString() %>" class="openMail">											
												<img src="images/Message.png" width="35px" height="35px"/>
											</a>
										</td>
										<td style="text-align: left;">
											<%= subj %>
										</td>
										<td style="text-align: left;">
											<%= service.returnName(fromMail) %>
										</td> 
										<td>
<%
											Calendar c = Calendar.getInstance();
											Date d = c.getTime();
											Calendar c1=Calendar.getInstance();
	    									c.setTime(date);
	    									String dob=CalendarUtilityDefault.getStringFromCalendar(c);
											if(date.getYear()<d.getYear())
											{
												//out.println(date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getYear());
												out.println(dob);
											}
											else if(date.getMonth()<d.getMonth())
											{
												out.println(dob);
											}
											else if(date.getDate()<d.getDate())
											{
												out.println(dob);
											}
											else
											{		int hrs;
													int mins;
												if(date.getMinutes()>=30)
												{
													 hrs=date.getHours()+6;
													mins=date.getMinutes()-30;
													
												}
												else
												{
													hrs=date.getHours()+5;
													mins=date.getMinutes()+30;
												}
												if(hrs>=24)
												{
													hrs=hrs-24;
												}
												out.println(hrs+":"+mins);	
											}
%>
										</td>
										<td>
											<a href="deleteMail.jsp?index=<%= i %>"><img src="images/delete.png" width="20px" height="20px" style="padding-top: 5px;"></a>
										</td>
									</tr>						
<%
	    						} //End of for
	    						
%>
							<tr height="10px"></tr>
							<tr><td colspan="4">
							<b style="float: left; padding-top: 5px; color: gray;">Read rest of the messages: </b>
<%
						double b=Math.ceil(message.length/(double)10);
						int j=(int)b;						
						for(int k=1;k<=j;k++)
						{
%>
							<a style="text-decoration: none; float: left;" href="inbox_next.jsp?fromno=<%= k %>"><img src="images/plus.png" width="25px" height="25px" title="Go to page <%= k %>"></a>
<%
						}
						}
						else {
%>
							<table id="inboxMailList">		
							<tr><td colspan="4">
								<img alt="Wishbook" src="images/error.png" width="35px" height="35px" style="float: left; margin-left: 125px">
								<div style="font-size: 15px; color: #0b92d6; float: left; margin-top: 8px">Your have no messages in your inbox</div>
							</td></tr>						
							</table>
<%					
						}
%>
						</td></tr>
								</table>
<%
    							// "true" actually deletes flagged messages from folder
    							fldr.close(true);
    							store.close();
							}
							catch (MessagingException mex) 
							{
    							// Prints all nested (chained) exceptions as well
    							mex.printStackTrace();
							}
						%>
				</div>
				<div id="mailPaneBottom">
				</div>
			</div>
		</div>	
		<div id="right">
			<div id="search">
				<form action="retrievesearch.jsp">
				<div id="searchText">
					<input id="searchInput" name="keyword" border="0px" maxlength="31"/>
				</div>
				<div id="searchButton">
					<input type="submit" style="background-image: url('images/home/search.png'); height: 33px; width: 25px; background-color: #ebebeb" value=""/>
				</div>
				</form>
			</div>
			<div id="sideMenu">
			
				<table id="currUserDetails" style="margin-top: 15px; margin-left: 12px; float: left;">
					<tr>
						<td width="100px">
							<center>
							<img src="<%= currUserInfo.get(0).getPropic() %>" width="80px" height="80px"/>
							</center>
						</td>
						<td width="200px" style="overflow: auto; text-align: left; color: #a1a0a8; padding-left: 5px; font-family: sans-serif; font-size: 12px;">
							<%
								for(int i=0;i<size1;i++){
	    							user_info s=(user_info)currUserInfo.get(i);
	    							out.println("<b style=\"font-size:15px;\">" + s.getFirstName()+ " " + s.getLastName() + "</b><br/>");
	    							Calendar c=Calendar.getInstance();
									c.setTime(s.getDob());
									String dob=CalendarUtilityDefault.getStringFromCalendar(c);
	    							out.println(dob + "<br/>");
	    							out.println(s.getCity() + "<br/>");
	    							out.println(s.getCountry() + "<br/>");
	    							out.println(s.getPhoneNo() + "<br/>");
	    							out.println(s.getEmail() + "<br/>");
	    						}
							%>
						</td>
					</tr>
					<tr style="height: 30px">
						<td>
						</td>
						<td style="text-align: left;">
							<div id="currUserLinks">
								<ul id="userLinks">
									<li id="userLinkedin"><a href="<%= currUserInfo.get(0).getLi() %>"></a></li>
									<li id="userTwitter"><a href="<%= currUserInfo.get(0).getTw() %>"></a></li>
									<li id="userFacebook"><a href="<%= currUserInfo.get(0).getFb() %>"></a></li>
									<li id="userEmail"><a href="mailto:<%= currUserInfo.get(0).getPe() %>"></a></li>
								</ul>
							</div>
						</td>
					</tr>
					<tr style="height: 60px;">
						<td>
						</td>
						<td style="text-align: left; ">
							<div id="editCurrUser">
								<a href="edit_profile.jsp"><img src="images/home/edit_profile.png" width="86px" height="30px"/></a>
							</div>
						</td>
					</tr>
				</table>
				<table width="240px" height="153px" style="text-align: right; padding-right: 40px; margin-right: 40px;">
				<tr>
				<td style="padding-top: 10px; margin-top: 10px;">
					<img src="images/home/topics_left.png" width="109px" height="153px"/>
				</td>
				<td>
				<div id="sideMenuLinks" style="margin-top: 0px; padding-top: 0px">
					<ul id="menuLinks">
						<li id="addFriendsLink"><a href="findfriend.jsp"></a></li>
						<li id="wishlistLink"><a href="listFriends.jsp"></a></li>
						<li id="seasonsLink"><a href="seasons.jsp"></a></li>
						<li id="customLink"><a href="paint.jsp"></a></li>
						<li id="faqLink"><a href="faq.jsp"></a></li>
					</ul>
				</div>
				</td>
				</tr>
				</table>
				<table width="240px" style="text-align: right; padding-right: 40px; margin-right: 40px; margin-top: 25px">
					<tr height="75px">
						<td style="font-size: 20px; float: none;color: #0b92d6;">
							<img alt="Custom Functions" src="images/wand.png" width="50px" height="50px" style="float: left;">
							<div style="float: left; margin-top: 15px; font-family: sans-serif">Apps</div>
						</td>
					</tr>
					<tr>
						<td style="float: none;">
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="sendMail.jsp"><img alt="Compose Mail" src="images/Edit.png" height="40px" width="40px" title="Compose Mail"></a>
							</div>
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="automated.jsp"><img alt="Schedule Events" src="images/add_schedule.png" height="40px" width="40px" title="Schedule Wishes"></a>
							</div>
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="reminder.jsp"><img alt="Reminders" src="images/add_reminder.png" height="40px" width="40px" title="Set Reminders"></a>
							</div>
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="greetings_misc.jsp"><img alt="Personal Occasions Greeting" src="images/Present.png" height="40px" width="40px" title="Greetings for personal occasions"></a>
							</div>
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="greeting.jsp"><img alt="Seasonal and Festive Greetings" src="images/Snowman.png" height="40px" width="40px" title="Seasonal and Festive Greetings"></a>
							</div>
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="viewseasons.jsp"><img alt="View Scheduled Events" src="images/seasons_list.png" height="40px" width="40px" title="View Scheduled Events"></a>
							</div>
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="viewreminders.jsp"><img alt="View Reminders" src="images/reminder_list.png" height="40px" width="40px" title="View Reminders"></a>
							</div>
							<div style="float: left; margin-top: 5px; margin-left: 5px;">
								<a href="deleteaccount.jsp"><img alt="Delete Account" src="images/delete.png" height="40px" width="40px" title="Delete Account"></a>
							</div>
						</td>						
					</tr>
				</table>
			</div>
		</div>
	</div>
	</center>
</f:view>
</body>
</html>