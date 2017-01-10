
function check()
{  var reg1 = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
var address = document.form11.Field9.value;
var reg=document.form11.dob.value;
var currentTime = new Date();
var address1 = document.form11.pe.value;
var d;
var month = currentTime.getMonth() + 1;
var day1 = currentTime.getDate();
var year = currentTime.getFullYear();
var month1;
var mn1;


dt=reg.split('-');
day=parseInt(dt[0]);
yr=parseInt(dt[2]);
mn=dt[1];
count=0;

	  if(document.form11.Field15.value.length==0){
          alert("Enter Firstname please !!!");
          return false;
    }
	  else if(document.form11.Field16.value.length==0){
          alert("Enter Lastname please !!!");
          return false;
    }
	  else if(document.form11.uname.value.length==0){
          alert("Enter Username please !!!");
          return false;
    }
	  else if((document.form11.password.value.length==0) ||(document.form11.password.value.length<6) ){
          alert("Enter password/Entered password is <6 characters");
          return false;
    }
	  else if(document.form11.Field3.value.length==0){
          alert("Enter Street address !!!");
          return false;
	  }
          else if(document.form11.Field4.value.length==0){
              alert("Enter address line2 !!!");
              return false;
          }
              else if(document.form11.Field5.value.length==0){
                  alert("Enter City !!!");
                  return false;
              }
              else if(document.form11.Field6.value.length==0){
                  alert("Enter state!!!");
                  return false;
              }
              else if((document.form11.Field7.value.length==0)){
                  alert("Enter zipcode !!!");
                  return false;
              } 
              else if ( (isNaN(document.form11.Field7.value))||(document.form11.Field7.value.length>9)) 
              {alert("Enter correct zipcode !!!");
              return false;
            	  
              }

              else if(document.form11.Field8.value.length==0){
                  alert("Choose country !!!");
                  return false;
              }
              else if(document.form11.Field10.value.length==0){
                  alert("Enter phoneno !!!");
                  return false;
              }
              else if ( isNaN(document.form11.Field10.value)) 
              {alert("Enter correct phoneno !!!");
              return false;
            	  
              }
              else if(document.form11.Field9.value.length==0){
                  alert("Enter email !!!");
                  return false;
              }
              else if(reg1.test(address) == false ) {
            	  alert("Invalid Email Address");
            	  return false;
              }
              else if(document.form11.fb.value.length==0){
                  alert("Enter facebook page !!!");
                  return false;
              }
              else if(document.form11.tw.value.length==0){
                  alert("Enter twitter page !!!");
                  return false;
              }
              else if(document.form11.li.value.length==0){
                  alert("Enter linkedIn page !!!");
                  return false;
              }
              else if(document.form11.pe.value.length==0){
                  alert("Enter Personal email !!!");
                  return false;
              }
              else if(reg1.test(address1) == false ) {
            	  alert("Invalid Personal Email Address");
            	  return false;
              }
              else if(document.form11.dob.value.length==0){
                  alert("Enter date of birth !!!");
                  return false;
              }
              else if((reg.indexOf("-")!=2) && (reg.indexOf("-")!=6)||(day>31)||(day<1)||(yr<1970)||(yr>2011)||(((mn.toLowerCase())!="jan")&&((mn.toLowerCase())!="feb")&&((mn.toLowerCase())!="mar")&&((mn.toLowerCase())!="apr")&&((mn.toLowerCase())!="may")&&((mn.toLowerCase())!="jun")&&((mn.toLowerCase())!="jul")&&((mn.toLowerCase())!="aug")&&((mn.toLowerCase())!="sep")&&((mn.toLowerCase())!="oct")&&((mn.toLowerCase())!="nov")&&((mn.toLowerCase())!="dec"))) {
    	          alert("Invalid date !!!\n");
    	          return false;
    	    }
             
  		    
             return true;
	  
	
}