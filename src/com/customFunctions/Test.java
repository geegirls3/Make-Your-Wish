package com.customFunctions;

import java.util.List;

public class Test {
	public static void main(String[] args) {

		String host = "10.122.13.10";
		String user = "Makeawish2@myssmtp.com";
		String pass = "infy@123";
		String downloadDir = "C:\\Users\\404048.ITLINFOSYS\\Desktop";
		try {
			List<Email> emails = IncomingMail.downloadPop3(host, user, pass, downloadDir);
			for (Email email : emails) {
				System.out.println(email.from);
				System.out.println(email.subject);
				System.out.println(email.body);
				List<EmailAttachment> attachments = email.attachments;
				for (EmailAttachment attachment : attachments) {
					System.out.println(attachment.path + " " + attachment.name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
