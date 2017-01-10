package com.services;
import java.io.*;
import java.util.*;
import javax.mail.*;

public class ReadAttachment {

	public static void main(String args[]) throws Exception {
		String host = "10.122.13.10";
		String user = "Makeawish2@myssmtp.com";
		String password = "infy@123";

		// Get system properties
		Properties properties = System.getProperties();

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		// Get a Store object that implements the specified protocol.
		Store store = session.getStore("pop3");

		// Connect to the current host using the specified username and
		// password.
		store.connect(host, user, password);

		// Create a Folder object corresponding to the given name.
		Folder folder = store.getFolder("inbox");

		// Open the Folder.
		folder.open(Folder.READ_WRITE);

		Message[] message = folder.getMessages();

		for (int a = 0; a < message.length; a++) {
			System.out.println("-------------" + (a + 1) + "-----------");
			System.out.println(message[a].getSentDate());

			Multipart multipart = (Multipart)message[a].getContent();
			// System.out.println(multipart.getCount());

			for (int i = 0; i < multipart.getCount(); i++) {
				// System.out.println(i);
				// System.out.println(multipart.getContentType());
				BodyPart bodyPart = multipart.getBodyPart(i);
				InputStream stream = bodyPart.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(stream));

				while (br.ready()) {
					System.out.println(br.readLine());
				}
				System.out.println();
			}
			System.out.println();
		}
		folder.close(true);
		store.close();
	}
}
