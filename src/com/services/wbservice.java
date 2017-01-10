package com.services;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import com.sun.mail.imap.protocol.FLAGS;


import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;


import com.entities.*;




public class wbservice {
	EntityManager em =null;
	public static String authUser;
	 public static String authPwd  = "infy@123";
	 public static String[] fromuser=new String[1000];
	 public static String[] touser=new String[1000];
	 public static String[] subjectfor=new String[1000];
	 public static String[] mess=new String[1000];
	 public static Date[] dateofevents=new Date[1000];
	 public static int index=0;
	 public static int i2=0;
	 public static int[] indexofevents=new int[1000];

	public int addmessage(wishbook wb) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(wb);
			em.getTransaction().commit();
			return wb.getSlno();
		}
		finally
		{
			em.close();
		}
	}
	
	/*--------------------------------------------------------------REPLY THREAD--------------------------------------------------------------*/
	
	//Add wishbook reply threads to wishbook entries
	public int addToReplyThread(wishbook_comments reply) throws Exception
	{
		try {
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(reply);
			em.getTransaction().commit();
			return reply.getSlno();
		}
		finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<wishbook_comments> displayReplyThread(int sno) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from wishbook_comments x where x.sno=?1 order by x.slno desc");
			query.setParameter(1,sno);
			List<wishbook_comments> list =query.getResultList();
			if(list.size()==0){
				throw new Exception("No items found");
			}			
			return list;
		}
		finally
		{
			em.close();
		}
	}
	
	public wishbook displayMainThread(int slno) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from wishbook x where x.slno = ?1");
			query.setParameter(1,slno);
			List<wishbook> list =query.getResultList();
			if(list.size()==0){
				throw new Exception("No items found");
			}			
			else {
				wishbook c=(wishbook)list.get(0);
				return c;
			}
		}
		finally
		{
			em.close();
		}
	}
	
	public int returnThreadReplyCount(int slno) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from wishbook_comments x where x.sno = ?1");
			query.setParameter(1,slno);
			List<wishbook> list =query.getResultList();
			return list.size();
		}
		finally
		{
			em.close();
		}
	}
	
	public void deleteThreadReply(int s) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("delete from wishbook_comments x where x.slno=?1");			
			query.setParameter(1,s);
			query.executeUpdate();
			
			em.getTransaction().commit();
			//return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	
	/*---------------------------------------------------------REPLY THREAD END--------------------------------------------------------------*/
	
	@SuppressWarnings("unchecked")
	public List<wishbook> displayItems(String to) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from wishbook x where x.touser=?1 order by x.slno");
			query.setParameter(1,to);
			List<wishbook> list =query.getResultList();
			if(list.size()==0){
				throw new Exception("No items found");
			}			
			return list;
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<user_info_temp> retrieveuser(String uname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			if(uname==null)
			{
				Query query=em.createQuery("select x from user_info_temp x");
				List<user_info_temp> list =query.getResultList();
				if(list.size()==0){
					throw new Exception("No items found");
				}	
				return list;
			}
			else
			{
			Query query=em.createQuery("select x from user_info_temp x where x.uname=?1");
			query.setParameter(1,uname);
			List<user_info_temp> list =query.getResultList();
			if(list.size()==0){
				throw new Exception("No items found");
			}			
			return list;
			}
		}
		finally
		{
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<user_info> retrieveProfileInfo(String uname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from user_info x where x.uname=?1");
			query.setParameter(1,uname);
			List<user_info> list =query.getResultList();
			if(list.size()==0){
				throw new Exception("No items found");
			}			
			return list;
		}
		finally
		{
			em.close();
		}
	}
	
	public void wishalert(String from ,String to,String subject,String message) throws MessagingException
	{
		  String hostName = "10.122.13.10";  
		boolean debug = true;
		authUser=from;
	 	/* Conf the Host SMTP address as a property and indicate that Authentication is reqd.*/
 		Properties props = new Properties();
 		props.put("mail.smtp.host", hostName);
 		props.put("mail.smtp.auth", "true");

/* Create an object pointing to the SMTPAuthenticator Class, which performs simple Authentication */
 		Authenticator auth = new SMTPAuthenticator();
 		Session session = Session.getInstance(props, auth);

	 	session.setDebug(debug);

 		/* Create a message */
 		Message msg = new MimeMessage(session);
 		msg.setFlag(FLAGS.Flag.SEEN, false);

	 	/* Set the sender and recipient */
	 	InternetAddress addressFrom = new InternetAddress(from);
 		msg.setFrom(addressFrom);

 		/* Read recipients from Recipient List */
	 	InternetAddress[] addressTo = new InternetAddress[1];
	 	for (int i = 0; i <1; i++)
 		{
    			 addressTo[i] = new InternetAddress(to);
	 	}
	 	msg.setRecipients(Message.RecipientType.TO, addressTo);


 		/* Set the Subject and the Content Type */
 		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
      		/* Finally, send the message (generates trace if Debug is true) */
	 	Transport.send(msg);
		System.out.println("Alert has been send");
		
		
		
		
	}
	
	
	private class SMTPAuthenticator extends javax.mail.Authenticator
	 {
  	  	/* Function	 : 		getPasswordAuthentication
                              * Description	 : 		Function to perform userName and Password     
                                                                                      Authentication      
    	   	  * Params 	: 		none
     	   	  * Returns 	:		PasswordAuthentication
    	   	  */
      
     		public PasswordAuthentication getPasswordAuthentication()
    		{
      	   		//String authUser=auth;
				String username = authUser;
         	   		//String authPwd=pwd;
					String password = authPwd;
         	   		return new PasswordAuthentication(username, password);
    	 	}
	 }
	public int adduser(user_info_temp ui) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(ui);
			em.getTransaction().commit();
			return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	
	public int adduser_main(user_info ui,int i) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			if(i==1)
			{
				em.persist(ui);
			String uname=ui.getUname();
			Query query=em.createQuery("delete from user_info_temp x where x.uname=?1");			
			query.setParameter(1,uname);
			query.executeUpdate();
			}
			else
			{
				em.merge(ui);
			}
			
			em.getTransaction().commit();
			return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	
	public int addseasons(seasons s) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(s);
			em.getTransaction().commit();
			return s.getSlno();
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void seasonsnotification() throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			//Query query=em.createQuery("select u.uid1 from users u where u.uid1 not in (select " +
				//	"w.fname from wishlistpk w where w.uname in (?1)) and u.uid1 not in (?2)");
			Query query1=em.createQuery("select u from seasons u");
			
			//query.setParameter(2,from1);
			List<seasons> list =query1.getResultList();
			if(list==null)
			{
				System.out.println("hey....");
			}
			for(int a=0;a<list.size();a++)
			{
				seasons s=list.get(a);
				Date d_curr=new Date();
				Date d1_user=s.getDateoffest();
				int i=(d_curr.getDate());
				int j=d_curr.getMonth();
				int k=d1_user.getDate();
				int l=d1_user.getMonth();
				if(i==k && j==l)
				{
					System.out.println("success");
					String from=s.getUsername();
					String to=s.getFname();
					String subj=s.getFestivalname();
					String mess="Happy....."+subj;
					wishalert(from,to,subj,mess);
					deleteseason(s.getSlno());
				}
			}
			//if(list.size()==0){
			//	throw new Exception("No items found");
			//}			
			
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public void birthdaynotifier() throws Exception
	{	
		EntityManager em1=null;
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			  em1 = emf.createEntityManager();
			Query query2=em1.createQuery("select u.email from user_info u ");
			
			List list1 =query2.getResultList();
			int size=list1.size();
			 for(int p=0;p<size;p++){
			    			        
			        String user=(String)list1.get(p);
			        System.out.println(user);
			        birthdaynotification(user);
			        
			 }
			
			
		}
		finally
		{
			em1.close();
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void birthdaynotification(String from1) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			//Query query=em.createQuery("select u.uid1 from users u where u.uid1 not in (select " +
				//	"w.fname from wishlistpk w where w.uname in (?1)) and u.uid1 not in (?2)");
			Query query1=em.createQuery("select u from user_info u where u.email in (?1)");
			query1.setParameter(1,from1);
			//query.setParameter(2,from1);
			List<user_info> list =query1.getResultList();
			if(list==null)
			{
				System.out.println("hey....");
			}
			user_info ui=list.get(0);
			
			Date d_curr=new Date();
			Date d1_user=ui.getDob();
			int i=(d_curr.getDate()+1);
			int j=d_curr.getMonth();
			int k=d1_user.getDate();
			int l=d1_user.getMonth();
			System.out.println(d_curr+"\n");
			System.out.println(d1_user+"\n");
			System.out.println(i);
			
			if(i==k && j==l)
			{
				System.out.println("success");
				
				//findfriends
				Query query2=em.createQuery("select w.fname from wishlistpk w where w.uname in (?2)");
				query2.setParameter(2,from1);
				List list1 =query2.getResultList();
				int size=list1.size();
				 for(int p=0;p<size;p++){
				    			        
				        String to=(String)list1.get(p);
				        String subject="My Birthday Notification";
				        String message="Wish me happy Birthday.....just kidding  :)";
				        wishalert(from1,to,subject,message);
				 }
			}
			
			
			
			//if(list.size()==0){
			//	throw new Exception("No items found");
			//}			
			
		}
		finally
		{
			em.close();
		}
	}
	
	public void invitation(wishlistpk wpk) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			wishlistpk wpk1=new wishlistpk();
			wpk1.setFname(wpk.getUname());
			wpk1.setUname(wpk.getFname());
			
			em.persist(wpk);
			
			em.persist(wpk1);
			em.getTransaction().commit();
			System.out.println("Persisted");
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List findoldfriends(String from1,String festivalname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select w.fname from wishlistpk w where w.fname not in (select "+
					 "s.fname from seasons s where s.festivalname in (?1) and s.username in (?2)) and " +
					 "w.uname in (?3)");
			query.setParameter(2,from1);
			query.setParameter(3,from1);
			query.setParameter(1,festivalname);
			List list =query.getResultList();						
			return list;
		}
		finally
		{
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List findfriends(String from1) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select u.email from user_info u where u.email not in (select " +
					"w.fname from wishlistpk w where w.uname in (?1)) and u.email not in (?2)");
			query.setParameter(1,from1);
			query.setParameter(2,from1);
			List list =query.getResultList();
			if(list.size()==0){
				throw new Exception("No items found");
			}			
			return list;
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public int checkfriends(String user1,String user2) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from wishlistpk x where x.uname=?1 and x.fname=?2");
			query.setParameter(1,user1);
			query.setParameter(2,user2);
			List list =query.getResultList();
			if(list.size()!=0)
			{
				return 1;
			}
			return 0;
		}
		finally
		{
			em.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List listExistingfriends(String from1) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select w.fname from wishlistpk w where w.uname in (?1)");
			query.setParameter(1,from1);
			List list =query.getResultList();
			if(list.size()==0){
				throw new Exception("No items found");
			}			
			return list;
		}
		finally
		{
			em.close();
		}
	}
	
	public void friendrequest(String from ,String to) throws Exception
	{
		  String hostName = "10.122.13.10";  
		boolean debug = true;
		authUser=from;
	 	/* Conf the Host SMTP address as a property and indicate that Authentication is reqd.*/
 		Properties props = new Properties();
 		props.put("mail.smtp.host", hostName);
 		props.put("mail.smtp.auth", "true");

/* Create an object pointing to the SMTPAuthenticator Class, which performs simple Authentication */
 		Authenticator auth = new SMTPAuthenticator();
 		Session session = Session.getInstance(props, auth);

	 	session.setDebug(debug);

 		/* Create a message */
 		Message msg = new MimeMessage(session);

	 	/* Set the sender and recipient */
	 	InternetAddress addressFrom = new InternetAddress(from);
 		msg.setFrom(addressFrom);

 		/* Read recipients from Recipient List */
	 	InternetAddress[] addressTo = new InternetAddress[1];
	 	for (int i = 0; i <1; i++)
 		{
    			 addressTo[i] = new InternetAddress(to);
	 	}
	 	msg.setRecipients(Message.RecipientType.TO, addressTo);


 		/* Set the Subject and the Content Type */
 		msg.setSubject("friend request");
		msg.setContent("<html><body><h4>" + returnName(from) +" wants to be your friend"+
				"</h4>" +
				"" +
				"<img id=\"menuBottomFAQLinkImage\" src=\"images/home/bullet.png\" height=\"12px\" width=\"9px\"/><a id=\"menuBottomFAQLink\" style=\"text-decoration:none; color:gray\" href=\"acceptfriend.jsp?from="+from+"&to="+to+"\">Add as friend</a><br/>"+
				"<img id=\"menuBottomFAQLinkImage\" src=\"images/home/bullet.png\" height=\"12px\" width=\"9px\"/><a id=\"menuBottomFAQLink\" style=\"text-decoration:none;color:gray\" href=\"rejectfriend.jsp?from="+from+"&to="+to+"\">Reject friend request</a><br/>"+
				"</body></html>", "text/html");
      		/* Finally, send the message (generates trace if Debug is true) */
	 	Transport.send(msg);
		System.out.println("Alert has been send");
		
		
		
		
	}
	
	public void sendGreeting(String from ,String to, String path) throws MessagingException
	{
		  String hostName = "10.122.13.10";  
		boolean debug = true;
		authUser=from;
	 	/* Conf the Host SMTP address as a property and indicate that Authentication is reqd.*/
 		Properties props = new Properties();
 		props.put("mail.smtp.host", hostName);
 		props.put("mail.smtp.auth", "true");

/* Create an object pointing to the SMTPAuthenticator Class, which performs simple Authentication */
 		Authenticator auth = new SMTPAuthenticator();
 		Session session = Session.getDefaultInstance(props, auth);

	 	session.setDebug(debug);

 		/* Create a message */
 		Message msg = new MimeMessage(session);

	 	/* Set the sender and recipient */
	 	InternetAddress addressFrom = new InternetAddress(from);
 		msg.setFrom(addressFrom);

 		/* Read recipients from Recipient List */
	 	InternetAddress[] addressTo = new InternetAddress[1];
	 	for (int i = 0; i <1; i++)
 		{
    			 addressTo[i] = new InternetAddress(to);
	 	}
	 	msg.setRecipients(Message.RecipientType.TO, addressTo);


 		/* Set the Subject and the Content Type */
 		msg.setSubject("E-Greetings");
		msg.setContent("<html><body>"+ "<img src='" + path +"' title='Greeting'>" + 
				"</body></html>", "text/html");
      		/* Finally, send the message (generates trace if Debug is true) */
	 	Transport.send(msg);
		System.out.println("Alert has been send");
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public int checkuser(String uname,String pwd) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			if(uname.equals("admin")&&pwd.equals("admin"))
			{
				return 2;
			}
			Query query=em.createQuery("select x from user_info x where x.uname=?1 and x.password=?2");
			query.setParameter(1,uname);
			query.setParameter(2,pwd);
			List<user_info_temp> list =query.getResultList();
			if(list.size()==0){
				return 0;
			}			
			return 1;
			
		}
		finally
		{
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public String returnEmail(String uname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			Query query=em.createQuery("select x.email from user_info x where x.uname=?1");
			query.setParameter(1,uname);
			List<String> list =query.getResultList();
			String email = list.get(0);			
			return email;	
		}
		finally
		{
			em.close();
		}
	}
	
	public String returnFirstName(String uname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			Query query=em.createQuery("select x.firstName from user_info x where x.uname=?1");
			query.setParameter(1,uname);
			List<String> list =query.getResultList();
			String email = list.get(0);			
			return email;	
		}
		finally
		{
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public String returnUsername(String email) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			Query query=em.createQuery("select x.uname from user_info x where x.email=?1");
			query.setParameter(1,email);
			List<String> list =query.getResultList();
			String username = list.get(0);			
			return username;	
		}
		finally
		{
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public String returnName(String email) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			Query query=em.createQuery("select x.firstName,x.lastName from user_info x where x.email=?1");
			query.setParameter(1,email);
			List<Object []> list =query.getResultList();
			Object[] temp = list.get(0);
			String firstname = (String)temp[0];
			String lastname = (String)temp[1];
			String username = firstname+" "+lastname;
			return username;	
		}
		finally
		{
			em.close();
		}
	}
	public void HelloSchedule(Date d,String from,String to,String subject,String message)throws Exception
	{
		try{
			//System.out.println(from);
			//System.out.println(index);
		fromuser[index]=from;
		//System.out.println(fromuser[index]);
		touser[index]=to;
		
		subjectfor[index]=subject;
		mess[index]=message;
		dateofevents[index]=d;
		indexofevents[index]=index;
		index++;
		reminder r=new reminder();
		r.setFromuser(from);
		r.setTouser(to);
		r.setDateofreminder(d);
		r.setMessage(message);
		r.setSubject(subject);
		@SuppressWarnings("unused")
		int p=addreminder(r);
		SchedulerFactory sf=new StdSchedulerFactory();
		Scheduler sched=sf.getScheduler();
		sched.start();
		JobDetail jd=new JobDetail(subject,sched.DEFAULT_GROUP,HelloJob.class);
		//SimpleTrigger st=new SimpleTrigger("mytrigger",sched.DEFAULT_GROUP,new Date(),null,SimpleTrigger.REPEAT_INDEFINITELY,10L*1000L);
		SimpleTrigger se=new SimpleTrigger(subject,sched.DEFAULT_GROUP,d);
		sched.scheduleJob(jd, se);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		}
	public static void sorting(Date d[],int low,int n)
	{
		int lo = low;
		int hi = n;
		if (lo >= n) {
			return;
		}
		Date mid = d[(lo + hi) / 2];
		while (lo < hi) 
		{
			while (lo<hi && d[lo].before(mid))
			{
				lo++;
			}
			while (lo<hi && d[hi].after(mid))
			{
				hi--;
			}
			if (lo < hi)
			{
				Date T = d[lo];
				d[lo] = d[hi];
				d[hi] = T;
				int t=indexofevents[lo];
				indexofevents[lo]=indexofevents[hi];
				indexofevents[hi]=t;
				                                
			}
		}
		if (hi < lo) 
		{
			int T = hi;
			hi = lo;
			lo = T;
		}
		sorting(d, low, lo);
		sorting(d, lo == low ? lo+1 : lo, n);
	}
	public int addreminder(reminder r) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(r);
			em.getTransaction().commit();
			return r.getSlno();
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<reminder> retrievereminder(String uname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from reminder x where x.fromuser=?1");
			query.setParameter(1,uname);
			List<reminder> list =query.getResultList();
			//if(list.size()==0){
			//	throw new Exception("No items found");
			//}			
			return list;
		}
		finally
		{
			em.close();
		}
	}
	public void deletereminder(Date d,String fromuser,String touser,String subject,String message) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("delete from reminder x where x.dateofreminder=?1 and x.fromuser=?2 and x.touser=?3 and x.subject=?4 and x.message=?5");			
			query.setParameter(1,d);
			query.setParameter(2,fromuser);
			query.setParameter(3,touser);
			query.setParameter(4,subject);
			query.setParameter(5,message);
			
			query.executeUpdate();
			
			em.getTransaction().commit();
			//return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	public void deletereminder_usingindex(int slno) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("delete from reminder x where x.slno=?1");			
			query.setParameter(1,slno);
			
			query.executeUpdate();
			
			em.getTransaction().commit();
			//return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	public void deleteseason(int s) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("delete from season x where x.slno=?1");		
			query.setParameter(1,s);
			query.executeUpdate();
			
			em.getTransaction().commit();
			//return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<seasons> retrieveseason(String uname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			Query query=em.createQuery("select x from seasons x where x.username=?1");
			query.setParameter(1,uname);
			List<seasons> list =query.getResultList();
			//if(list.size()==0){
			//	throw new Exception("No items found");
			//}			
			return list;
		}
		finally
		{
			em.close();
		}
	}
		
	@SuppressWarnings("unchecked")
	public int checkusername(String uname,String email) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			
			Query query=em.createQuery("select x from user_info x where x.uname=?1 or x.email=?2");
			query.setParameter(1,uname);
			query.setParameter(2,email);
			List<user_info> list =query.getResultList();
			if(list.size()==0){
				return 0;
			}			
			return 1;
			
		}
		finally
		{
			em.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<user_info> search(String uname) throws Exception
	{	
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			uname=uname+"%";
			//Query query=em.createQuery("select x from user_info x where x.uname like :pattern ESCAPE :esc ");
			Query query=em.createQuery("select x from user_info x where x.uname like :pattern");
			
			query.setParameter("pattern",uname);
	
			List<user_info> list =query.getResultList();
					
			return list;
			
		}
		finally
		{
			em.close();
		}
	}
	
	public void deletewish(int s) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("delete from wishbook x where x.slno=?1");			
			query.setParameter(1,s);
			query.executeUpdate();
			
			em.getTransaction().commit();
			//return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	public void deleteuser_temp(String s) throws Exception
	{		
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createQuery("delete from user_info_temp x where x.email=?1");			
			query.setParameter(1,s);
			query.executeUpdate();
			
			em.getTransaction().commit();
			//return ui.getUserId();
		}
		finally
		{
			em.close();
		}
	}
	
	public void deleteuser_main(String s) throws Exception
    {           
          try
          {
                EntityManagerFactory emf=Persistence.createEntityManagerFactory("MakeYourWish");
                em = emf.createEntityManager();
                em.getTransaction().begin();
                Query query=em.createQuery("delete from user_info x where x.email=?1"); 
                Query query1=em.createQuery("delete from wishbook x where x.fromuser=?1 or x.touser=?1");
                Query query2=em.createQuery("delete from wishlistpk x where x.uname=?1 or x.fname=?1");
                Query query3=em.createQuery("delete from reminder x where x.fromuser=?1");
                Query query4=em.createQuery("delete from seasons x where x.username=?1");
                
                query.setParameter(1,s);
                query1.setParameter(1,s);
                query2.setParameter(1,s);
                query3.setParameter(1,s);
                query4.setParameter(1,s);
                query.executeUpdate();
                query1.executeUpdate();
                query2.executeUpdate();
                query3.executeUpdate();
                query4.executeUpdate();
                
                em.getTransaction().commit();
                //return ui.getUserId();
          }
          finally
          {
                em.close();
          }
    }
    
	public void sendCustomGreeting (String from, String to, String subject, String file) throws Exception
	{
		    String host = "10.122.13.10";		    
		    // Setup properties
		    Properties props = System.getProperties();
		    props.put("mail.smtp.host", host);

		    // Setup authentication, get session
		    Authenticator auth = new SMTPAuthenticator();
		    Session session = Session.getInstance(props, auth);    


		    // Define message
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from));
		    message.addRecipient(Message.RecipientType.TO, 
		      new InternetAddress(to));
		    message.setSubject("Custom Greeting");

		    // Create the message part 
		    BodyPart messageBodyPart = new MimeBodyPart();

		    // Fill the message
		    messageBodyPart.setText("You have been delivered a custom greeting");

		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);

		    // Part two is attachment
		    messageBodyPart = new MimeBodyPart();
		    DataSource source = new FileDataSource(file);
		    messageBodyPart.setDataHandler(new DataHandler(source));
		    messageBodyPart.setFileName("greeting.png");
		    multipart.addBodyPart(messageBodyPart);

		    // Put parts in message
		    message.setContent(multipart);

		    // Send the message
		    Transport.send(message);

	}

	
}
	
