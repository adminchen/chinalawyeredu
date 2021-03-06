
package com.changpeng.models;

// Generated 2008-5-4 12:08:05 by Hibernate Tools 3.2.0.CR1

import java.sql.Timestamp;

/**
 * Diaochaoption generated by hbm2java
 */
public class Diaochaoption implements java.io.Serializable {

	private int optionid;
	private Diaochawenti diaochawenti;
	private String title;
	private Integer logicwenti;
	private Timestamp createtime;
	private String remarks;
	
	private String options;
	
	private int replycount;
	
	private boolean others; //是否为其他选项
	public boolean getOthers() {
		return others;
	}

	public void setOthers(boolean others) {
		this.others = others;
	}

	public int getReplycount() {
		return replycount;
	}

	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getLogicwenti() {
		return logicwenti;
	}

	public Diaochaoption() {
		
	}

	public int getOptionid() {
		return this.optionid;
	}

	public void setOptionid(int optionid) {
		this.optionid = optionid;
	}

	public Diaochawenti getDiaochawenti() {
		return this.diaochawenti;
	}

	public void setDiaochawenti(Diaochawenti diaochawenti) {
		this.diaochawenti = diaochawenti;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer isLogicwenti() {
		return this.logicwenti;
	}

	public void setLogicwenti(Integer logicwenti) {
		this.logicwenti = logicwenti;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
