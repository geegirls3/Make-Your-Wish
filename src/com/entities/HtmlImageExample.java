package com.entities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class HtmlImageExample {
  public static void main (String args[]) throws Exception {
    String host = "10.122.13.10";
    String from = "Makeawish3@myssmtp.com";
    String to = "Makeawish2@myssmtp.com";
    String file = "C:\\Users\\404048.ITLINFOSYS\\Desktop\\greeting.png";

    // Get system properties
    Properties props = System.getProperties();

    // Setup mail server
    props.put("mail.smtp.host", host);

    // Get session
    Session session = Session.getDefaultInstance(props, null);

    // Create the message
    Message message = new MimeMessage(session);

    // Fill its headers
    message.setSubject("Embedded Image");
    message.setFrom(new InternetAddress(from));
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

    // Create your new message part
    BodyPart messageBodyPart = new MimeBodyPart();

    // Set the HTML content, be sure it references the attachment
    String htmlText = "<H1>Hello</H1>" + 
      "<img src=\"cid:memememe\">";

    // Set the content of the body part
    messageBodyPart.setContent(htmlText, "text/html");

    // Create a related multi-part to combine the parts
    MimeMultipart multipart = new MimeMultipart("related");

    // Add body part to multipart
    multipart.addBodyPart(messageBodyPart);

    // Create part for the image
    messageBodyPart = new MimeBodyPart();

    // Fetch the image and associate to part
    DataSource fds = new FileDataSource(file);
    messageBodyPart.setDataHandler(new DataHandler(fds));

    // Add a header to connect to the HTML
    messageBodyPart.setHeader("Content-ID","<memememe>");

    // Add part to multi-part
    multipart.addBodyPart(messageBodyPart);

    // Associate multi-part with message
    message.setContent(multipart);

    // Send message
    Transport.send(message);
  }
}

