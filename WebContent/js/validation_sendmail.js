function auto()
{
	 var reg1 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	 var address = document.at.txtTo.value;
		 
	    if(document.at.txtTo.value.length==0){
	          alert("Enter recipient Email id please !!!");
	          return false;
	    }
	    else if(reg1.test(address) == false ) {
      	  alert("Invalid Email Address");
      	  return false;
        }
		  else if(document.at.txtSubject.value.length==0){
	          alert("Enter Subject please !!!");
	          return false;
	    }
		  else if(document.at.txtMsg.value.length==0){
	          alert("Enter text message please !!!");
	          return false;
	    }
		 
		    	return true;	
		    
	 
}