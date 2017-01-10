
function fn1()
{var reg2 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
var address = document.form_check.email.value;
	reg = document.form_check.dob.value;
reg1 = document.form_check.anniversary.value;
date= new Date();
month= date.getMonth();
day=date.getDay();

dt=reg.split('-');
day=parseInt(dt[0]);
   yr=parseInt(dt[2]);
   mn=dt[1];

	  if(document.form_check.firstname.value.length==0){
          alert("Enter name please !!!");
          return false;
    }
	  else if(document.form_check.lastname.value.length==0){
          alert("Enter lastname please !!!");
          return false;
    }
	  else if(document.form_check.password.value.length==0){
          alert("Enter password please !!!");
          return false;
    }
	  
	  
	  else if(document.form_check.streetaddress.value.length==0){
          alert("Enter street address please !!!");
          return false;
    }
	  else if(document.form_check.addressline2.value.length==0){
          alert("Enter addressline2 please !!!");
          return false;
    }
	  else if(document.form_check.city.value.length==0){
          alert("Enter city please !!!");
          return false;
    }
	  else if(document.form_check.state.value.length==0){
          alert("Enter state please !!!");
          return false;
    }
	  else if(document.form_check.pin.value.length==0){
          alert("Enter pin please !!!");
          return false;
    }
	  else if ( isNaN(document.form_check.pin.value)) 
      {alert("Enter correct pincode !!!");
      return false;
    	  
      }
	  else if(document.form_check.country.value.length==0){
          alert("Enter country please !!!");
          return false;
    }
	  else if(document.form_check.phoneno.value.length==0){
          alert("Enter phoneno please !!!");
          return false;
    }
	  else if ( isNaN(document.form_check.phoneno.value)) 
      {alert("Enter correct phoneno !!!");
      return false;
    	  
      }
	  
	  else if(document.form_check.email.value.length==0){
          alert("Enter email please !!!");
          return false;
    }
	  else if(reg2.test(address) == false ) {
    	  alert("Invalid Email Address");
    	  return false;
      }
	  else if(document.form_check.dob.value.length==0){
          alert("Enter date of birth please !!!");
          return false;
          
    }
	  else if((reg.indexOf("-")!=2) && (reg.indexOf("-")!=6)||(day>31)||(day<1)||(yr<1970)||(yr>2011)||(((mn.toLowerCase())!="jan")&&((mn.toLowerCase())!="feb")&&((mn.toLowerCase())!="mar")&&((mn.toLowerCase())!="apr")&&((mn.toLowerCase())!="may")&&((mn.toLowerCase())!="jun")&&((mn.toLowerCase())!="jul")&&((mn.toLowerCase())!="aug")&&((mn.toLowerCase())!="sep")&&((mn.toLowerCase())!="oct")&&((mn.toLowerCase())!="nov")&&((mn.toLowerCase())!="dec"))) {
          alert("Invalid date of birth!!!\n");
          return false;
    }
	  
	
	  
	  return true;
}