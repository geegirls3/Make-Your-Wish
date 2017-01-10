function rem()
{
	var reg=document.at.dated.value;
	var currentTime = new Date();


	
	dt=reg.split('-');
	day=parseInt(dt[0]);
	   yr=parseInt(dt[2]);
	   mn=dt[1];

	
	   reg = document.at.dated.value;
    dt=reg.split('-');
    day=parseInt(dt[0]);
    yr=parseInt(dt[2]);
    mn=dt[1];
	count=0;

	    
	    if(document.at.dated.value.length==0){
	          alert("Enter date  !!!");
	          return false;
	          
	    }
	    else if((reg.indexOf("-")!=2) && (reg.indexOf("-")!=6)||(day>31)||(day<1)||(yr<1970)||(yr>2011)||(((mn.toLowerCase())!="jan")&&((mn.toLowerCase())!="feb")&&((mn.toLowerCase())!="mar")&&((mn.toLowerCase())!="apr")&&((mn.toLowerCase())!="may")&&((mn.toLowerCase())!="jun")&&((mn.toLowerCase())!="jul")&&((mn.toLowerCase())!="aug")&&((mn.toLowerCase())!="sep")&&((mn.toLowerCase())!="oct")&&((mn.toLowerCase())!="nov")&&((mn.toLowerCase())!="dec"))) {
	          alert("Invalid date !!!\n");
	          return false;
	    }
		 
	    else if(document.at.subject.value.length==0){
	          alert("Enter subject please !!!");
	          return false;
	    }
		  else if(document.at.message.value.length==0){
	          alert("Enter message please !!!");
	          return false;
	    }
		 
		    	return true;	
		    
	 
}