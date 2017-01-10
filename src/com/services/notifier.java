package com.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;



@SuppressWarnings("serial")
public class notifier extends HttpServlet 
{
	public void init() throws ServletException {

	    try {
	        System.out.println("Initializing NewsLetter PlugIn");
	        notifierschedule ns=new notifierschedule();
	      
	       }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
	}
}
