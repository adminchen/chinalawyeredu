/**
 * LessonCenterAction.java
 */
package com.changpeng.lessons.action;

import com.changpeng.common.action.AbstractAction;

/**
 * @author 华锋 Jan 7, 201111:47:40 AM
 * 
 */
public class MyOrderListAction extends AbstractAction {

	public MyOrderListAction() {
		this.moduleId = 1006;
	}

	/*
	 * 我的订单,点了支付动作了,就成为订单了
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub

		
		
		
		return SUCCESS;
	}

}
