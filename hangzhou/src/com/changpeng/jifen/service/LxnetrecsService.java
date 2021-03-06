/**
 * LxnetrecsService.java
 */

package com.changpeng.jifen.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.dao.LawyerlessonxfDAO;
import com.changpeng.jifen.dao.LxnetrecsDAO;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lxnetrecs;
import com.changpeng.models.system.SysUser;

/**
 * 
 * 刷卡情况
 * 
 * @author 华锋 2008-5-5 下午01:11:04
 * 
 */
public class LxnetrecsService extends BasicService {

	private static Log _LOG = LogFactory.getLog(LxnetrecsService.class);

	private LxnetrecsDAO lxnetrecsDAO;

	private LawyerlessonxfDAO lawyerlessonxfDAO;
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLawyerlessonxfDAO(LawyerlessonxfDAO lawyerlessonxfDAO) {
		this.lawyerlessonxfDAO = lawyerlessonxfDAO;
	}

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLxnetrecsDAO(LxnetrecsDAO lxnetrecsDAO) {
		this.lxnetrecsDAO = lxnetrecsDAO;
	}

	/**
	 * 这个课程这个人有没有看视频的记录情况
	 * 
	 * @param lessonid
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
	public Lxnetrecs getLxnetrecs(int lessonid, long userid) throws ServiceException {
		try {
			return lxnetrecsDAO.getLxnetrecs(lessonid, userid);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 新增视频记录情况，同时更新这个人的积分
	 * 
	 * @param netrecs
	 * @throws ServiceException
	 */
	public float saveLxnetrecs(final Lxnetrecs netrecs) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {

					lxnetrecsDAO.save(netrecs);
					Lawyerlessonxf xf = lawyerlessonxfDAO.getXuefen(netrecs.getLessonid(), netrecs.getUserid(), "在线视频");

					Lessons lesson = (Lessons) lxnetrecsDAO.get(Lessons.class, netrecs.getLessonid());
					SysUser lawer = (SysUser) lxnetrecsDAO.get(SysUser.class, netrecs.getUserid());

					float huodexuefen = Float.parseFloat(NumberUtil.toMoney(lesson.getXuefen()
							* (netrecs.getLookedminutes() / netrecs.getAllminutes())));

					if(lesson.getFenshuoff()!=null&&!"".equals(lesson.getFenshuoff())){//获得的学分要打折
						int zhekou=Integer.parseInt(lesson.getFenshuoff());
						huodexuefen=Float.parseFloat(NumberUtil.toMoney((huodexuefen*zhekou)/100));
					}
					
					if (xf == null) {
						xf = new Lawyerlessonxf();
						xf.setLawer(lawer);
						xf.setLearnmode("在线视频");
						// xf.setPxxf(budeng.getXuefen());
						xf.setPxxf(huodexuefen);
		
						
						xf.setRemarks("视频积分:"+xf.getPxxf());
						xf.setPxminutes(netrecs.getLookedminutes());
						xf.setPxreqminutes(netrecs.getAllminutes());
						xf.setLessonid(netrecs.getLessonid());

						xf.setTitle(lesson.getTitle());
						xf.setLastupdate(netrecs.getLasttime());
						// xf.setPxdate(budeng.getBudengdate());
						lxnetrecsDAO.save(xf);

					}
					else if (huodexuefen > xf.getPxxf()) {// 获得的学分大于现有的学分的时候，才更新
						xf.setPxxf(huodexuefen);
						xf.setRemarks((xf.getRemarks() != null ? xf.getRemarks() : "") + "|视频:"+huodexuefen);
						xf.setPxminutes(netrecs.getLookedminutes());
						xf.setPxreqminutes(netrecs.getAllminutes());
						lxnetrecsDAO.update(xf);
					}

					// 客户的话，先不分配，由主办律师自己去进行分配
					return huodexuefen;
				}
			});
			return (Float) object;
		}
		catch (Exception e) {
			_LOG.error("新增视频获得学分失败:::" + e);
			throw new ServiceException(e);
		}
	}

	public float updateLxnetrecs(final Lxnetrecs netrecs) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					// 更新这个课程
					lxnetrecsDAO.update(netrecs);

					Lawyerlessonxf xf = lawyerlessonxfDAO.getXuefen(netrecs.getLessonid(), netrecs.getUserid(), "在线视频");
					Lessons lesson = (Lessons) lxnetrecsDAO.get(Lessons.class, netrecs.getLessonid());
					// SysUser lawer = (SysUser) lxnetrecsDAO.get(SysUser.class, netrecs.getUserid());

					float huodexuefen = Float.parseFloat(NumberUtil.toMoney(lesson.getXuefen()
							* (netrecs.getLookedminutes() / netrecs.getAllminutes())));
					if(lesson.getFenshuoff()!=null&&!"".equals(lesson.getFenshuoff())){//获得的学分要打折
						int zhekou=Integer.parseInt(lesson.getFenshuoff());
						huodexuefen=Float.parseFloat(NumberUtil.toMoney((huodexuefen*zhekou)/100));
					}
					_LOG.debug("获得的学分::::::" + huodexuefen+",打了几折:::"+lesson.getFenshuoff());

					if (huodexuefen > xf.getPxxf().floatValue()) {//大于的时候才更新
						xf.setPxxf(huodexuefen);
						xf.setRemarks((xf.getRemarks() != null ? xf.getRemarks() : "") + "|视频:"+huodexuefen);

						xf.setTitle(lesson.getTitle());
						xf.setLastupdate(netrecs.getLasttime());
						xf.setPxminutes(netrecs.getLookedminutes());

						// xf.setPxdate(budeng.getBudengdate());
						lxnetrecsDAO.update(xf);
					}
					// 客户的话，先不分配，由主办律师自己去进行分配
					return huodexuefen;
				}
			});
			return (Float) object;
		}
		catch (Exception e) {
			_LOG.error("更新视频获得学分失败:::" + e);
			throw new ServiceException(e);
		}

	}
}
