package com.sxit.models.wait;

// Generated 2007-10-10 17:32:03 by Hibernate Tools 3.2.0.b9

import java.sql.Timestamp;

/**
 * TwatWait generated by hbm2java
 */
public class TwatWait implements java.io.Serializable {

	private int waitid;
	private String url;
	private String subject;
	private int fromUserid;
	private String fromUsername;
	private int status;
	private String flowstep;
	private int docstatus;
	private Timestamp createtime;
	private String target;
	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public TwatWait() {
	}

	public int getWaitid() {
		return this.waitid;
	}

	public void setWaitid(int waitid) {
		this.waitid = waitid;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFlowstep() {
		return this.flowstep;
	}

	public void setFlowstep(String flowstep) {
		this.flowstep = flowstep;
	}

	public int getDocstatus() {
		return this.docstatus;
	}

	public void setDocstatus(int docstatus) {
		this.docstatus = docstatus;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getFromUserid() {
		return fromUserid;
	}

	public void setFromUserid(int fromUserid) {
		this.fromUserid = fromUserid;
	}

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

}
