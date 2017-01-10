package com.services;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;
public class notifierjob implements Job{

public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
	wbservice wb=new wbservice();
    try {
		wb.seasonsnotification();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		wb.birthdaynotifier();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
