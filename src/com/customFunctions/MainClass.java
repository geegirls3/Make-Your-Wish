package com.customFunctions;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;

import com.sun.mail.imap.protocol.FLAGS;

public class MainClass {

  public static void main(String[] args) throws Exception {

	  	
		Session session4 = Session.getInstance(new Properties());

		try
		{
			// Get a Store object
			Store store = session4.getStore("pop3");
			store.connect("10.122.13.10","Makeawish2@myssmtp.com", "infy@123");

		    // Get "INBOX"
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);
			int count = folder.getMessageCount();

			Message message[] = folder.getMessages();

    // Get the messages from the server
    Message[] messages = folder.getMessages();
    for (int i = 0; i < messages.length; i++) {
      // Get the headers
      System.out.println("From: " + InternetAddress.toString(messages[i].getFrom()));
      System.out.println("Reply-to: " + InternetAddress.toString(messages[i].getReplyTo()));
      System.out.println("To: " + InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO)));
      System.out.println("Cc: " + InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.CC)));
      System.out.println("Bcc: " + InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.BCC)));
      System.out.println("Subject: " + messages[i].getSubject());

      System.out.println("Sent: " + messages[i].getSentDate());
      System.out.println("Received: " + messages[i].getReceivedDate());

      if (messages[i].isSet(Flags.Flag.DELETED)) {
        System.out.println("Deleted");
      }
      if (messages[i].isSet(Flags.Flag.ANSWERED)) {
        System.out.println("Answered");
      }
      if (messages[i].isSet(Flags.Flag.DRAFT)) {
        System.out.println("Draft");
      }
      if (messages[i].isSet(Flags.Flag.FLAGGED)) {
        System.out.println("Marked");
      }
      if (messages[i].isSet(Flags.Flag.RECENT)) {
        System.out.println("Recent");
      }
      if (messages[i].isSet(FLAGS.Flag.SEEN)) {
        System.out.println("Read");
      }
      if (messages[i].isSet(Flags.Flag.USER)) {
        // We don't know what the user flags might be in advance
        // so they're returned as an array of strings
        String[] userFlags = messages[i].getFlags().getUserFlags();
        for (int j = 0; j < userFlags.length; j++) {
          System.out.println("User flag: " + userFlags[j]);
        }
      }
    }
    folder.close(false);
}
	finally {
		
	}
}
}

class MailAuthenticator extends Authenticator {

  public MailAuthenticator() {
  }

  public PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication("username", "password");
  }
}