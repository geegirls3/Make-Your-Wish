package com.cron;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class InitializeServlet extends HttpServlet {

	public void init() throws ServletException {

	    try {
	        System.out.println("Initializing NewsLetter PlugIn");

	        @SuppressWarnings("unused")
			CronScheluder objPlugin = new CronScheluder();

	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }

	  }
	
}
