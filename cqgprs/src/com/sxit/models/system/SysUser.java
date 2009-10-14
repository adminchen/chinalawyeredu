package com.sxit.models.system;

// Generated 2008-2-21 9:22:49 by Hibernate Tools 3.2.0.CR1

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TSysUser generated by hbm2java
 * 
 * <pre>
 * 1.加入passkey字段,对输入的password和key进行组合后再md5进行组合
 * </pre>
 */
public class SysUser implements java.io.Serializable {

	private int userid;
	private int groupid;
	// private int locationid;
	private SysGroup sysGroup;
	// private String department;
	private String loginname;
	private String username;
	private String style;
	private String gender;
	private String employeeno;
	// private String birthday;
	// private String passkey;
	private String password;
	private byte status;
	private String email;
	private boolean delflag;
	private String officephone;
	private String mobile;
	private String comments;
	private String createuser;
	private Timestamp createtime;
	private Set<SysRight> sysRights = new HashSet<SysRight>(0);
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	// 同步的时候，同步方的userid;

	/**
	 * 用户的角色，1律师，2事务所管理员，3管理员
	 */
	// private String roleid;
	/**
	 * 绑定这个用户的此次登录的id
	 */
	private int loginId;

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getLoginId() {
		return this.loginId;
	}

	/**
	 * 记录最近一次的登录时间
	 */
	private Timestamp lastLoginTime;

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * 总计登录次数
	 */
	private int loginCount;

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getLoginCount() {
		return this.loginCount;
	}

	/**
	 * 此人最终的的权限列表
	 */
	private Set<String> rightList;

	public void setRightList(Set<String> rightList) {
		this.rightList = rightList;
	}

	public Set<String> getRightList() {
		return this.rightList;
	}

	/**
	 * 此人的菜单显示
	 */
	private List<SysRight> userMenus;

	private List<SysRight> topMenus=new ArrayList<SysRight>();

	public List<SysRight> getTopMenus() {
		return this.topMenus;
	}

	public void setUserMenus(List<SysRight> userMenus) {
		for (SysRight right : userMenus) {
			if (right.getParentcode().equals("0") || right.getGrade() == 1)
				topMenus.add(right);
		}
		this.userMenus = userMenus;
	}

	public List<SysRight> getUserMenus() {
		return this.userMenus;
	}

	/**
	 * 判断这个人是否有权限
	 * 
	 * @param rightCode
	 * @return
	 */
	public boolean hasRight(String rightCode) {
		if (rightCode == null || rightCode.equals(""))
			return true;
		return rightList.contains(rightCode);
	}

	public SysUser() {

	}

	public SysUser(int userid, String loginname, String password, byte status) {
		this.userid = userid;
		this.loginname = loginname;
		this.password = password;
		this.status = status;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	// public SysGroup getSysGroup() {
	// return this.sysGroup;
	// }
	//
	// public void setSysGroup(SysGroup sysGroup) {
	// this.sysGroup = sysGroup;
	// }

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmployeeno() {
		return this.employeeno;
	}

	public void setEmployeeno(String employeeno) {
		this.employeeno = employeeno;
	}

	//
	// public String getBirthday() {
	// return this.birthday;
	// }
	//
	// public void setBirthday(String birthday) {
	// this.birthday = birthday;
	// }

	// public String getPasskey() {
	// return this.passkey;
	// }
	//
	// public void setPasskey(String passkey) {
	// this.passkey = passkey;
	// }

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getDelflag() {
		return this.delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}

	public String getOfficephone() {
		return this.officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Set<SysRight> getSysRights() {
		return this.sysRights;
	}

	public void setSysRights(Set<SysRight> SysRights) {
		this.sysRights = SysRights;
	}

	public Set<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(Set<SysRole> SysRoles) {
		this.sysRoles = SysRoles;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	// public int getLocationid() {
	// return locationid;
	// }
	//
	// public void setLocationid(int locationid) {
	// this.locationid = locationid;
	// }

	// 将locationid拆成provinceid,cityid,districtid

	// private int provinceid;
	// private int cityid;
	// private int districtid;

	// public int getProvinceid() {
	// return provinceid;
	// }
	//
	// public void setProvinceid(int provinceid) {
	// this.provinceid = provinceid;
	// }
	//
	// public int getCityid() {
	// return cityid;
	// }
	//
	// public void setCityid(int cityid) {
	// this.cityid = cityid;
	// }
	//
	// public int getDistrictid() {
	// return districtid;
	// }
	//
	// public void setDistrictid(int districtid) {
	// this.districtid = districtid;
	// }

	/**
	 * 可以考虑通过代理来实现
	 * 
	 * @param field
	 * @return
	 */
	// public int getFieldvalue(String field) {
	// if (field.equalsIgnoreCase("provinceid"))
	// return this.provinceid;
	// if (field.equalsIgnoreCase("cityid"))
	// return this.cityid;
	// if (field.equalsIgnoreCase("districtid"))
	// return this.districtid;
	// return 0;
	// }
	public SysGroup getSysGroup() {
		return sysGroup;
	}

	public void setSysGroup(SysGroup sysGroup) {
		this.sysGroup = sysGroup;
	}
}
