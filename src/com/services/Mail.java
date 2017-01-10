package com.services;


	import java.util.Properties;
	import javax.mail.Authenticator;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;

	/*
	This program allows users to send E-Mails via a SMTP server through their JAVA application.
	The program uses the JavaMail API to send mails to a single recipient or multiple recipients.

	To use this API in any application, the following parameters need to be set:-

	hostName 		-	 	The Host Name of the SMTP server being used
	authUser	               - 		UserName for SMTP Authentication
	authPwd 		 -		 Password for SMTP Authentication 
	emailMessage		 - 		Content of the message being sent
	emailSubject 		- 		Subject of the mail
	emailFromAddress 	-	 	'From' Address
	emailToList		 - 		List of recipients
	*/

	public class Mail
	{

	 /* SMTP server and Authentication Details */
	 private static String hostName = "10.122.13.10";  
	//Infosys SMTP server is 172.21.5.10

	 private static String authUser = "Makeawish1@myssmtp.com";
	 private static String authPwd  = "infy@123";

	 /* Email details */
	 private static final String emailMessage = "testing 1..... :)";
	 private static final String emailSubject  = "Java Alert";
	 private static final String emailFromAddress = "Makeawish1@myssmtp.com";
	 private static final String[] emailToList = {"Makeawish3@myssmtp.com"};	
	/* Function	: 		postMail()
	   	  * Description	:		Sends mail using SMTP server.  
	   	  * Params	:		String recipients[], String subject, String message, String from
	   	  * Returns	: 		void
	   	  */
	   
	 	 public void postMail(String recipients[ ], String subject, String message , String from) 
	  	throws MessagingException
	  	{
	   	  	/* Set Debug trace ON to track the process flow */
	     		boolean debug = true;

	    	 	/* Conf the Host SMTP address as a property and indicate that Authentication is reqd.*/
	     		Properties props = new Properties();
	     		props.put("mail.smtp.host", hostName);
	     		props.put("mail.smtp.auth", "true");

	/* Create an object pointing to the SMTPAuthenticator Class, which performs simple Authentication */
	     		Authenticator auth = new SMTPAuthenticator();
	     		Session session = Session.getDefaultInstance(props, auth);
	    
	    	 	session.setDebug(debug);

	     		/* Create a message */
	     		Message msg = new MimeMessage(session);

	    	 	/* Set the sender and recipient */
	    	 	InternetAddress addressFrom = new InternetAddress(from);
	     		msg.setFrom(addressFrom);

	     		/* Read recipients from Recipient List */
	    	 	InternetAddress[] addressTo = new InternetAddress[recipients.length];
	    	 	for (int i = 0; i < recipients.length; i++)
	     		{
	        			 addressTo[i] = new InternetAddress(recipients[i]);
	  	 	}
	    	 	msg.setRecipients(Message.RecipientType.TO, addressTo);


	     		/* Set the Subject and the Content Type */
	     		msg.setSubject(subject);
	   		msg.setContent(message, "text/plain");
	          		/* Finally, send the message (generates trace if Debug is true) */
	   	 	Transport.send(msg);
	  	}

	 
	 /* Class 	:		SMTPAuthenticator
	  * Description 	:		Class which contains function to perform simple authentication
	  */
	  
	  	private class SMTPAuthenticator extends javax.mail.Authenticator
	 	 {
	   	  	/* Function	 : 		getPasswordAuthentication
	                               * Description	 : 		Function to perform userName and Password     
	                                                                                       Authentication      
	     	   	  * Params 	: 		none
	      	   	  * Returns 	:		PasswordAuthentication
	     	   	  */
	       
	      		public PasswordAuthentication getPasswordAuthentication()
	     		{
	       	   		String username = authUser;
	          	   		String password = authPwd;
	          	   		return new PasswordAuthentication(username, password);
	     	 	}
	 	 }


	/**
	  	  * @return Returns the authUser.
	  	  */
	  	public static String getAuthUser() 
	  	{
	     		 return authUser;
	 	 }

	    
	 /**
	   	   * @param smtp_auth_user
	   	   * The authUser to set.
	  	   */
	  	public static void setAuthUser(String smtpAuthUser) 
	  	{
	      		authUser = smtpAuthUser;
	  	}
	    	/**
	   	  * @return Returns the hostName.
	   	  */
	  	public static String getHostName() 
	  	{
	      		return hostName;
	  	}

	    
	  	/**
	   	* @param smtp_host_name
	   	*  The hostName to set.
	   	*/
	  	public static void setHostName(String smtpHostName) 
	  	{
	      		hostName = smtpHostName;
	  	}
	  	

	/**
	   	* @return Returns the authPwd.
	   	*/
	  	public static String getAuthPwd() 
	  	{
	      		return authPwd;
	  	}

	    
	  	/**
	   	* @param smtp_auth_pwd The authPwd to set.
	   	*/
	  	public static void setAuthPwd(String smtpAuthPwd) 
	  	{
	      		authPwd = smtpAuthPwd;
	  	}



	  	/* Function	 : 		main
	   	  * Description	 : 		Sample main function to test the application
	   	  * Params	 : 		String [] args
	   	  * Returns 	: 		void
	   	  */
	  


	  	public static void main(String args[]) throws Exception
	  	{
	    		Mail smtpMailSender = new Mail();
	    
	smtpMailSender.postMail(emailToList, emailSubject, emailMessage, emailFromAddress);
	    
	    		System.out.println("Sucessfully Sent mail to All Users");
	  	}
	 }

	/* End of program */



