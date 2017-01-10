package com.services;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;


public class notifierschedule {
	public notifierschedule() throws Exception
	{
		SchedulerFactory sf=new StdSchedulerFactory();
		Scheduler sched=sf.getScheduler();
		sched.start();
		JobDetail jd=new JobDetail("myjob",sched.DEFAULT_GROUP,notifierjob.class);
		SimpleTrigger st=new SimpleTrigger("mytrigger",sched.DEFAULT_GROUP,new Date(),null,SimpleTrigger.REPEAT_INDEFINITELY,86400L*1000L);
		sched.scheduleJob(jd, st);
		
		
		
		
	}
	
}
