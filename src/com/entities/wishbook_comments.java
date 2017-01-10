package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class wishbook_comments {

	@Id
	@SequenceGenerator(name="Seq_My_ReplyId", sequenceName="Seq_My_ReplyId",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Seq_My_ReplyId")
	private int slno;	
	private int sno;
	private String fromuser;
	private String touser;
	private String reply;
	
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}	
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTouser() {
		return touser;
	}

}
