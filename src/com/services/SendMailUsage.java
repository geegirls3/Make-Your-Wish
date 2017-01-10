package com.services;


import java.util.*;
import java.io.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class SendMailUsage {
	
	private String from;
	private String to;
	private String subject;
	private String message;
	public String sendhtmlnormal(String from, String to, String subject, String message) throws IOException {

        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
  //      String to = "Makeawish3@myssmtp.com";        
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        String host = "10.122.13.10";

        // Create properties for the Session
        Properties props = new Properties();

        // If using static Transport.send(),
        // need to specify the mail server here
        props.put("mail.smtp.host", host);
        // To see what is going on behind the scene
        props.put("mail.debug", "true");

        // Get a session
        Session session = Session.getInstance(props);

        try {
            // Get a Transport object to send e-mail
            Transport bus = session.getTransport("smtp");

            // Connect only once here
            // Transport.send() disconnects after each send
            // Usually, no username and password is required for SMTP
            bus.connect();
            //bus.connect("smtpserver.yourisp.net", "username", "password");

            // Instantiate a message
            Message msg = new MimeMessage(session);

            // Set message attributes
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            // Parse a comma-separated list of email addresses. Be strict.
            msg.setRecipients(Message.RecipientType.CC,
                                InternetAddress.parse(to, true));
            // Parse comma/space-separated list. Cut some slack.
            msg.setRecipients(Message.RecipientType.BCC,
                                InternetAddress.parse(to, false));

            msg.setSubject(subject);
            msg.setText(message);
            msg.setSentDate(new Date());

           
            setHTMLContent(msg);
            msg.saveChanges();
            bus.sendMessage(msg, address);

            bus.close();

        }
        catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
            // How to access nested exceptions
            while (mex.getNextException() != null) {
                // Get next exception in chain
                Exception ex = mex.getNextException();
                ex.printStackTrace();
                if (!(ex instanceof MessagingException)) break;
                else mex = (MessagingException)ex;
            }
        }
        return "success";
    }



   

    // Set a single part html content.
    // Sending data of any type is similar.
    public static void setHTMLContent(Message msg) throws MessagingException, IOException {

        String html = "<html><head><title>" +
                        msg.getSubject() +
                        "</title></head><body><h1>" +
                        msg.getSubject() +
                        "</h1><p>" +msg.getContent()+"</p></body></html>";

        // HTMLDataSource is an inner class
        msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
    }



    public void setTo(String to) {
		this.to = to;
	}





	public String getTo() {
		return to;
	}



	



	public void setSubject(String subject) {
		this.subject = subject;
	}





	public String getSubject() {
		return subject;
	}



	public void setMessage(String message) {
		this.message = message;
	}





	public String getMessage() {
		return message;
	}



	/*
     * Inner class to act as a JAF datasource to send HTML e-mail content
     */
    static class HTMLDataSource implements DataSource {
        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        // Return html string in an InputStream.
        // A new stream must be returned each time.
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("Null HTML");
            return new ByteArrayInputStream(html.getBytes());
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        public String getContentType() {
            return "text/html";
        }

        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }

} //End of class
