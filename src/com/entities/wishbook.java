package com.entities;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class wishbook {
	@Id
	@SequenceGenerator(name="Seq_My_ItemId", sequenceName="Seq_My_ItemId",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Seq_My_ItemId")
	@Column
	private Integer slno;
	private String fromuser;
	private String touser;
	private String content;
	public Integer getSlno() {
		return slno;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public void setSlno(Integer slno) {
		this.slno = slno;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
