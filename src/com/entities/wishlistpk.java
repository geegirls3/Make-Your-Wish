package com.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
public class wishlistpk {
	@Id
	@SequenceGenerator(name="Seq_My_ItemI", sequenceName="Seq_My_ItemI",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Seq_My_ItemI")
	@Column
	private Integer slno;
	private String uname;
	private String fname;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setSlno(Integer slno) {
		this.slno = slno;
	}
	public Integer getSlno() {
		return slno;
	}
	
	
	

}
