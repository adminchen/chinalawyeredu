/**
 * IndexPageAction.java
 */

package com.changpeng.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessontype;

/**
 * 
 * userlogin成功后,redirect到这个页面。防止刷新，重复登录
 * 
 * @author 华锋 2008-4-22 下午06:02:52
 * 
 */
public class IndexPageAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(IndexPageAction.class);

	
	public IndexPageAction(){
		
	}
	
	private Lessontype notfenlei;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		this.moduleId=1000;
		
		this.lawyer = this.getLoginUser();
		
		com.changpeng.lessons.util.CommonDatas.getAlllessons();
		com.changpeng.lessons.util.CommonDatas.updateLessonTypes();
		LOG.info(lawyer.getLawyerenname()+ "进入首页成功......");
		
		BasicService service=(BasicService)getBean("basicService");
		notfenlei=(Lessontype)service.get(Lessontype.class, 0);
		
		
		return SUCCESS;
	}

	public String top() throws Exception {

	    this.lawyer = getLoginUser();
//		if(this.lawyer.getProvinceunion()==22){ //广西的不能修改密码
//			this.canChangePass=false;
//		}
		return "top";
	}

	public String left() throws Exception {

		this.lawyer = this.getLoginUser();
//		if(this.lawyer.getProvinceunion()==22){ //广西的不能修改密码
//			this.canChangePass=false;
//		}
		return "left";
	}
	private Lawyers lawyer;
//	public Lawyers getLawyers() {
//		return this.lawyer;
//	}
	
	public String getTopbarpic(){
//		return com.changpeng.common.Constants.TOP_BAR_PIC;
		return webinfo.getTopbarbic();
	}
//	private boolean canChangePass=true;
//	public boolean getCanChangePass(){
//		return this.canChangePass;
//	}

	/**
	 * @return the lawyer
	 */
	public Lawyers getLawyer() {
		return lawyer;
	}

	/**
	 * @return the notfenlei
	 */
	public Lessontype getNotfenlei() {
		return notfenlei;
	}
}
