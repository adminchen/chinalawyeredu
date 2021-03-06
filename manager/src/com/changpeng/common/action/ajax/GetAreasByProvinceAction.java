/**
 * JSONExample.java
 */

package com.changpeng.common.action.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.models.SysGroup;
import com.changpeng.system.service.SysGroupService;

/**
 * 
 * 国家->省份->城市的ajax数据的获取 <br/> 根据rightcode得到下一级的数据
 * 
 * @author 华锋 2008-3-11 下午11:10:44
 * 
 */
public class GetAreasByProvinceAction extends AbstractAction {
	private static Log LOG = LogFactory.getLog(GetAreasByProvinceAction.class);
	private Map areas = new HashMap();

	public Map getAreas() {
		return this.areas;
	}

	@Override
	public String go() throws Exception {

		SysGroupService groups = (SysGroupService) getBean("sysGroupService");

		List list = groups.getChildGroup(groupid);
		if (list != null) {
			for (Object obj : list) {
				SysGroup group = (SysGroup) list;
				areas.put(group.getGroupid(), group.getGroupname());
			}
		}

		LOG.debug(groupid + "的下级信息:" + areas);
		// 现在就实现根据省份找下面的城市就行了，其他的先不管
		// 数据从内存里面取,加快速度. ^_^
		return SUCCESS;
	}

	private int groupid;

	public void setAreacode(int areacode) {
		this.groupid = areacode;
	}

}