package com.services;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;

import javax.mail.MessagingException;
public class HelloJob implements Job{
	 public static int index1=0;
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			
			wbservice.sorting(wbservice.dateofevents,0,wbservice.index-1);
			String fromuser=wbservice.fromuser[wbservice.indexofevents[index1]];
			String touser=wbservice.touser[wbservice.indexofevents[index1]];
			String subjectfor=wbservice.subjectfor[wbservice.indexofevents[index1]];
			String mess=wbservice.mess[wbservice.indexofevents[index1]];
			Date d=wbservice.dateofevents[wbservice.indexofevents[index1]];
			index1++;
			System.out.println("heyyyyy");
			wbservice wb=new wbservice();
			try {
				wb.deletereminder(d,fromuser,touser,subjectfor,mess);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wb.wishalert(fromuser,touser,subjectfor,mess);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Hello World Quartz Scheduler: " + new Date());
	}

}
