package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class seasons {
	@Id
	@SequenceGenerator(name="Seq_My_It", sequenceName="Seq_My_It",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Seq_My_It")
	@Column
	private Integer slno;
	private String username;
	private String festivalname;
	private String fname;
	@Temporal(TemporalType.DATE)
	private Date dateoffest;
	public Integer getSlno() {
		return slno;
	}
	public void setSlno(Integer slno) {
		this.slno = slno;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFestivalname() {
		return festivalname;
	}
	public void setFestivalname(String festivalname) {
		this.festivalname = festivalname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Date getDateoffest() {
		return dateoffest;
	}
	public void setDateoffest(Date dateoffest) {
		this.dateoffest = dateoffest;
	}
	
	
	
}
