/**
 * JifenbudengAction.java
 */

package com.changpeng.jifen.action;

import java.util.List;

import com.changpeng.common.BasicService;
import com.changpeng.common.DataVisible;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.jifen.service.JifenbudengService;
import com.changpeng.jifen.util.JifenTime;
import com.changpeng.lawyers.service.LawyersService;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysRole;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-5-4 下午10:46:27
 * 
 */
public class JifenbudengAction extends AbstractAction {

	private Jifenbudeng budeng;

	private List locallessonlist;

	/**
	 * @return the locallessonlist
	 */
	public List getLocallessonlist() {
		return locallessonlist;
	}

	public Jifenbudeng getBudeng() {
		if (budeng == null)
			this.budeng = (Jifenbudeng) get("budeng");
		return this.budeng;
	}

	public JifenbudengAction() {
		this.datavisible = new DataVisible();
	}

	@Override
	public String go() throws Exception {
		// 积分的补登，同时应计入积分表里面，考虑事务来处理
		JifenbudengService budengservice = (JifenbudengService) getBean("jifenbudengService");
		LawyersService lawyersService = (LawyersService) getBean("lawyersService");
		SysUser user = (SysUser) this.getLoginUser();

		Lawyers lawyer = null;
		if (get("budengexist") != null && "0".equals(get("budengexist"))) {

			// if (!beupload) {
			lawyer = (Lawyers) lawyersService.getLawyerbyLawyerno(budeng.getLawyerno(), datavisible.getProvinceid(),
					datavisible.getCityid());
			if (lawyer == null) {
				this.message = "执业资格证号:" + budeng.getLawyerno() + "对应的律师资料已不存在,请返回";
				this.nextPage = "jifenbudeng!input.pl?budengid=" + this.budengid;
				return "message";
			}
			budeng.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
			budeng.setCreateuser(user.getUsername());
			budeng.setLawyerid(lawyer.getLawyerid());
			budeng.setProvinceid(lawyer.getProvinceunion());
			budeng.setCityid(lawyer.getDirectunion());
			budeng.setOfficeid(lawyer.getTheoffice());
			budeng.setLawyername(lawyer.getLawyername());
			budeng.setBudengfrom(0);
			int result = budengservice.saveJifenbudeng(budeng);
			if (result == 0) {
				this.opResult = "为" + budeng.getLawyerno() + "新增补登积分成功";
				this.message = "积分补登成功";
			} else {
				this.opResult = "为" + budeng.getLawyerno() + "新增补登积分不成功,该课程已经获得过积分";
				this.message = "积分补登失败:该律师已经获得该现场培训课程的积分";
			}
		} else {
			Float oldxuefen = (Float) get("oldbudeng");

			debug("补登前后的积分差异为:::" + (budeng.getXuefen().floatValue() - oldxuefen.floatValue()));

			budengservice.updateJifenbudeng(budeng, oldxuefen);
			this.opResult = "为" + budeng.getLawyerno() + "修改补登积分成功";
			
			this.message="积分补登修改成功";
		}

		this.nextPage = "jifenbudengList.pl";
		return SUCCESS;
	}
	private boolean gongzheng;
	public boolean getgongzheng(){
		return gongzheng;
	}
	@Override
	public String input() throws Exception {
		SysRole role = this.getLoginUser().getSysRole();
		if(role!=null&&(role.getRoleid()==11||role.getRoleid()==12)){
			gongzheng=true;
		}
		jifentime = com.changpeng.jifen.util.CommonDatas.getJifenTime(0, "12-31");

		BasicService basic = (BasicService) getBean("basicService");
		this.budeng = (Jifenbudeng) basic.get(Jifenbudeng.class, budengid);

		if (this.budeng == null) {
			set("budengexist", "0");
			this.budeng = new Jifenbudeng();
			budeng.setTheyear(jifentime.getNianshenyear());
		} else {

			this.datavisible.setCityid(budeng.getCityid());
			this.datavisible.setProvinceid(budeng.getProvinceid());

			set("oldbudeng", this.budeng.getXuefen());
			set("budengexist", "1");
		}

		this.datavisible.getVisibleDatas(this.getLoginUser(), false);
		set("budeng", budeng);

		return INPUT;
	}

	private JifenTime jifentime;
	private int budengid;

	public void setBudengid(int budengid) {
		this.budengid = budengid;
	}

	public int getBudengid() {
		return this.budengid;
	}

	/**
	 * @return the jifentime
	 */
	public JifenTime getJifentime() {
		return jifentime;
	}
}