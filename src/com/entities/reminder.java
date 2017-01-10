package com.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class reminder {

	@Id
	@SequenceGenerator(name="seq_userqw",sequenceName="seq_userqw",initialValue=1000,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_userqw")
	private Integer slno;
	@Column(nullable=false)
	private String fromuser;
	@Column(nullable=false)
	private String touser;
	@Column(nullable=false)
	private String subject;
	public Integer getSlno() {
		return slno;
	}
	public void setSlno(Integer slno) {
		this.slno = slno;
	}
	
	public String getTouser() {
		return touser;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDateofreminder() {
		return dateofreminder;
	}
	public void setDateofreminder(Date dateofreminder) {
		this.dateofreminder = dateofreminder;
	}
	@Column(nullable=false)
	private String message;
	@Temporal(TemporalType.DATE)
	private Date dateofreminder;
	
}
