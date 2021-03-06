/**
 * SysParameter.java
 */
package com.changpeng.models.system;

import java.sql.Timestamp;

/**
 * 
 * 系统参数管理
 * @author 华锋
 * 2008-5-5 下午05:38:24
 *
 */
public class SysParameter {

	private String paramname;
	private String paramvalue;
	private String comments;
	private Timestamp createtime;
	/**
	 * @return the paramname
	 */
	public String getParamname() {
		return paramname;
	}
	/**
	 * @param paramname the paramname to set
	 */
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	/**
	 * @return the paramvalue
	 */
	public String getParamvalue() {
		return paramvalue;
	}
	/**
	 * @param paramvalue the paramvalue to set
	 */
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	/**
	 * @return the remarks
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
	
	
}
