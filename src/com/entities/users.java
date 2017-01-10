package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
public class users {
	@Id
	private String uid1;
	private String uname;
	
	
	
	public String getUid1() {
		return uid1;
	}
	public void setUid1(String uid1) {
		this.uid1 = uid1;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
	

}
