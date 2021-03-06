/**
 * LessonsService.java
 */

package com.changpeng.lessons.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lessons.action.LessonStatic;
import com.changpeng.lessons.dao.LessonsDAO;
import com.changpeng.lessons.util.Lessonstatics;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lessonscore;
import com.changpeng.models.Lessonshared;
import com.changpeng.models.SysGroup;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-5-16 上午10:55:55
 * 
 */
public class LessonsService extends BasicService {
	private static Log _LOG = LogFactory.getLog(LessonsService.class);
	private LessonsDAO lessonsDAO;

	// private PlatformTransactionManager transactionManager;

	// public void setTransactionManager(PlatformTransactionManager
	// transactionManager) {
	// this.transactionManager = transactionManager;
	// }

	/**
	 * @param lessonsDAO
	 *            the lessonsDAO to set
	 */
	public void setLessonsDAO(LessonsDAO lessonsDAO) {
		this.lessonsDAO = lessonsDAO;
	}

	public Lessonscore getScorebyLessonUser(long userid, int lessonid) throws ServiceException {
		try {
			return lessonsDAO.getScorebyLessonUser(userid, lessonid);
		} catch (Exception e) {

			throw new ServiceException(e);
		}
	}

	@Transactional
	public void saveLesson(Lessons lesson, List groupids, SysUser user) throws ServiceException {
		lessonsDAO.save(lesson);

		// 保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	/**
	 * 修改课程的共享情况
	 * 
	 * @param lesson
	 * @param user
	 * @throws ServiceException
	 */
	@Transactional
	public void updateLessonShared(Lessons lesson, List groupids, SysUser user) throws ServiceException {
		// 先清除掉原有的共享
		String hql = "delete from com.changpeng.models.Lessonshared share where share.lessons.lessonid=?";
		lessonsDAO.execute(hql, lesson.getLessonid());

		// 再保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	/**
	 * 建立课程的共享
	 * 
	 * @param lesson
	 * @param groupids
	 * @param user
	 */
	public void createLessonShare(Lessons lesson, List groupids, SysUser user) {
		// 保存共享的情况
		if (groupids != null && groupids.size() > 0) {
			for (int i = 0; i < groupids.size(); i++) {
				com.changpeng.models.Lessonshared share = new Lessonshared();
				share.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
				share.setCreateuserid(user.getUserid());
				share.setCreateusername(user.getUsername());
				share.setLessons(lesson);
				share.setGroupid(Integer.parseInt(groupids.get(i).toString()));
				share.setXuefen(lesson.getXuefen());

				lessonsDAO.save(share);
			}
		}
	}

	public Lessonstatics getFiledLessons(Timestamp _from, Timestamp _end, String field, int fieldvalue)
			throws ServiceException {

		String sql = "";

		if (field != null && !field.equals("")) {
			sql = "select lessonstyle,count(lessonstyle) from lessons where (UNIX_TIMESTAMP(lessondate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") and " + field + "=" + fieldvalue
					+ " group by learnmode";
		} else {
			sql = "select lessonstyle,count(lessonstyle) from lessons where (UNIX_TIMESTAMP(lessondate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") group by lessonstyle";

		}

		Lessonstatics statics = new Lessonstatics();

		List tongjilist = lessonsDAO.findBySqlQuery(sql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		// 1现场课程2在线培训课程3现场和在线的合并4文本课件5补登的积分',

		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			int style = Integer.parseInt(obj[0].toString());
			if (style == 1) {
				statics.setLocal(Integer.parseInt(obj[1].toString()));
			} else if (style == 2) {
				statics.setOnline(Integer.parseInt(obj[1].toString()));
			} else if (style == 3) {
				statics.setOnlineandlocal(Integer.parseInt(obj[1].toString()));
			} else if (style == 4) {
				statics.setWenbenkejian(Integer.parseInt(obj[1].toString()));
			}
		}

		statics.setLocal(statics.getLocal() + statics.getOnlineandlocal());
		statics.setOnline(statics.getOnline() + statics.getOnlineandlocal());

		return statics;
	}


	
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	public com.changpeng.common.PaginationSupport getPages(SysGroup mygroup, int groupid,int audioQuality,int videoQuality,int onlinetype,int lessonstyle,
			int lessontype, String title, String teachers, int pageSize, int pageNo,Timestamp start,Timestamp end,int viewstyle,int pxname,int pxtype) {

		// 现场课程的话，如果我是admin或者group>3，则显示所有的现场课程，否则的话，
		// 我是省的话，显示本省的所有，市的话，只显示市的
		// 1是显示本地的本地课程，100是显示外地的本地课程
		if (lessonstyle == 1||lessonstyle==100) {
		
			
			DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class).add(Restrictions.eq("deleteflag", false));
//			if (mygroup != null && mygroup.getGrouptype() <= 3) {
//				if (mygroup.getGrouptype() == 1) { // 无权限
//					dc.add(Restrictions.eq("provinceid", -1));
//				} else if (mygroup.getGrouptype() == 2) {
//					//自己干的和省律协干的
//					dc.add(Restrictions.or(Restrictions.eq("cityid", mygroup.getGroupid()), Restrictions.eq("groupid", mygroup.getParentid())));
//				} else {
//					dc.add(Restrictions.eq("provinceid", mygroup.getGroupid()));
//				}
//			}
			if (onlinetype != -1) {
				dc.add(Restrictions.eq("onlineType", onlinetype));
			}if (audioQuality != -1) {
				dc.add(Restrictions.eq("audioQuality", audioQuality));
			}if (videoQuality != -1) {
				dc.add(Restrictions.eq("videoQuality", videoQuality));
			}
			if (title != null && !"".equals(title)) {
				dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
			}
			if (teachers != null && !"".equals(teachers)) {
				dc.add(Restrictions.like("teachers", teachers, MatchMode.ANYWHERE));
			}
			if (lessontype != -1) {
				dc.add(Restrictions.eq("lessontype", lessontype));
			}
			if (groupid != -1) {
//				dc.add(Restrictions.eq("lessons.groupid", groupid));
				dc.add(Restrictions.or(Restrictions.eq("groupid", groupid), Restrictions.eq("provinceid", groupid)));
//				hql += " and a.lessons.groupid=" + groupid;
			}
			if(start!=null&&end!=null)
				dc.add(Restrictions.between("lessondate", start, end));
			dc.add(Restrictions.in("lessonstyle", new Integer[] { 1, 3 }));
			dc.addOrder(Order.desc("lessondate"));
			dc.addOrder(Order.desc("lessonid"));
			PaginationSupport page = lessonsDAO.findPageByCriteria(dc, pageSize, pageNo);
			return page;
		} else {
			
			System.out.println("ok lai 了");
			DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
//			DetachedCriteria dc = DetachedCriteria.forClass(Lessonshared.class);
//			dc.createAlias("lessons", "lessons");
//			String hql = "select distinct a.lessons from Lessonshared a where 1=1 ";

//			if (mygroup != null && mygroup.getGrouptype() <= 3) {// mygroup为null的话,能看到所有律协的
//				List<Integer> groupids = new ArrayList<Integer>();
//				String str = "";
//				if (mygroup.getGrouptype() == 1) {
//					groupids.add(mygroup.getGroupid());
//					groupids.add(mygroup.getParentid());
//					groupids.add(mygroup.getDirectgroup());
//					str = mygroup.getGroupid() + "," + mygroup.getParentid() + "," + mygroup.getDirectgroup();
//					dc.add(Restrictions.in("groupid", groupids));
//					hql += " and a.groupid in(" + str + ")";
//				} else if (mygroup.getGrouptype() == 2) {
//					groupids.add(mygroup.getGroupid());
//					groupids.add(mygroup.getParentid());
//					str = mygroup.getGroupid() + "," + mygroup.getParentid();
//					dc.add(Restrictions.in("groupid", groupids));
//					hql += " and a.groupid in(" + str + ")";
//				} else if (mygroup.getGrouptype() == 3) {//省律协的,我省id是这个就行了吧
//					groupids.add(mygroup.getGroupid());
//					str = mygroup.getGroupid() + "";
//					
//					dc.add(Restrictions.or(Restrictions.in("groupid", groupids), Restrictions.eq("lessons.provinceid", mygroup.getGroupid())));
//					hql += " and (a.groupid in(" + str + ") or a.lessons.provinceid =" +  mygroup.getGroupid() + ")";
//					
//				}
//			
//			}
			// 不显示删除的
			
			dc.add(Restrictions.eq("deleteflag", false));
//			dc.add(Restrictions.eq("isshare", 1));
//			hql += " and a.lessons.deleteflag=false";
			// 具体的来源
			if (groupid != -1) {
//				dc.add(Restrictions.eq("lessons.groupid", groupid));
				dc.add(Restrictions.or(Restrictions.eq("groupid", groupid), Restrictions.eq("provinceid", groupid)));
				
//				hql += " and (a.lessons.groupid=" + groupid+" or a.lessons.provinceid="+groupid+")";
			}

			if (title != null && !"".equals(title)) {
				dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
//				hql += " and a.lessons.title like '%" + title + "%'";
			}
			if (teachers != null && !"".equals(teachers)) {
				System.out.println("teachers::"+teachers);
				teachers=teachers.replaceAll(" ","");
				System.out.println("teachers::"+teachers);
				dc.add(Restrictions.like("teachers", teachers, MatchMode.ANYWHERE));
//				hql += " and a.lessons.teachers like '%" + teachers + "%'";
			}
			if (onlinetype != -1) {
//				dc.add(Restrictions.eq("onlinetype", onlinetype));
				dc.add(Restrictions.eq("onlineType",onlinetype ));
//				hql += " and a.lessons.onlineType = " + onlinetype ;
			}if (audioQuality != -1) {
				dc.add(Restrictions.eq("audioQuality",audioQuality ));
//				hql += " and a.lessons.audioQuality = " + audioQuality ;
			}if (videoQuality != -1) {
				dc.add(Restrictions.eq("videoQuality",videoQuality ));
//				hql += " and a.lessons.videoQuality = " + videoQuality ;
			}
			if (lessontype != -1) {
				dc.add(Restrictions.eq("lessontype", lessontype));
//				hql += " and a.lessons.lessontype =" + lessontype;
			}
			if (lessonstyle != 0) {
//				if (lessonstyle == 1 || lessonstyle == 2)
					dc.add(Restrictions.in("lessonstyle", new Object[] { lessonstyle, 3 }));
//				else
//					dc.add(Restrictions.eq("lessons.lessonstyle", lessonstyle));
//				hql += " and a.lessons.lessonstyle in(" + lessonstyle + ",3)";
			}
			
			if(start!=null&&end!=null){
//				String startStr=df.format(start)+" 00:00:00";
//				String endStr=df.format(end)+" 23:59:59";
				//这是按照课件的考核年度
				//dc.add(Restrictions.between("lessondate", start, end));
				//2012037 修改 按照授课时间的自然年
				System.out.println(start);
				System.out.println(end);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				
				
			     String auditdate = sdf.format(start);
			     String auditdate1=auditdate+"-12-30 23:59:59.0";
			     auditdate=auditdate+"-01-01 00:00:00.0";
			    
			     System.out.println(auditdate);
			     System.out.println(auditdate1);
			     start=Timestamp.valueOf(auditdate);
			     end=Timestamp.valueOf(auditdate1);
			     System.out.println(start);
					System.out.println(end);
			     //dc.add(Restrictions.like("lessondate", start+"%"));
				dc.add(Restrictions.between("lessondate", start, end));
				System.out.println(start);
				System.out.println(end);
//				dc.add(Restrictions.between("lessons.lessondate", start, end));
//				hql += " and a.lessons.lessondate between '"+startStr+"'and '"+endStr+"'";
			}
			System.out.println("viewstyle 0000::::"+viewstyle);
			if(viewstyle==1){
				System.out.println("viewstyle 1111::::"+viewstyle);
				dc.add(Restrictions.eq("price", Float.valueOf(0)));
			}else if(viewstyle==2){
				System.out.println("viewstyle 2222::::"+viewstyle);
				dc.add(Restrictions.not(Restrictions.eq("price",Float.valueOf(0))));
			}
			
			if(pxtype==0 ){//降序
				if(pxname==0){//按授课时间
					dc.addOrder(Order.desc("lessondate"));
				}else{
					dc.addOrder(Order.desc("xuefen"));
					dc.addOrder(Order.desc("lessondate"));
				}				
			}else{//升序
				if(pxname==0){//按授课时间
					dc.addOrder(Order.asc("lessondate"));
				}else{
					dc.addOrder(Order.asc("xuefen"));
					dc.addOrder(Order.desc("lessondate"));
				}					
			}
			
			dc.addOrder(Order.desc("lessonid"));
			//dc.addOrder(Order.desc("lessondate"));
			//dc.addOrder(Order.desc("lessonid"));
			return lessonsDAO.findPageByCriteria(dc, pageSize, pageNo);
//			hql += " order by a.lessons.lessondate desc,a.lessons.lessonid desc";
		
//			dc.setProjection(Projections.countDistinct("lessons"));
//			dc.addOrder(Order.desc("lessons.lessondate"));
//			dc.addOrder(Order.desc("lessons.lessonid"));
//			List list = lessonsDAO.findAllByCriteria(dc);
//			int len = list == null ? 0 : list.size();
//			int totalCount = len == 0 ? 0 : Integer.parseInt(list.get(0).toString());
			// 根据授课时间进行排序
			// detachedCriteria.addOrder(Order.desc("lessons.lessondate"));
//			PaginationSupport page = lessonsDAO.findByQuery(hql, pageSize, pageNo, totalCount);
//			return page;
		}
	}
	
//	public com.changpeng.common.PaginationSupport getPages(SysGroup mygroup, int groupid, int lessonstyle,
//			int lessontype, String title, String teachers, int pageSize, int pageNo, Timestamp start, Timestamp end) {
//
//		// 现场课程的话，如果我是admin或者group>3，则显示所有的现场课程，否则的话，
//		// 我是省的话，显示本省的所有，市的话，只显示市的
//		if (lessonstyle == 1 || lessonstyle == 100) {
//			DetachedCriteria dc = DetachedCriteria.forClass(Lessons.class);
//			// 这里的mygroup都是市律协
//			if (lessonstyle == 1)
//				dc.add(Restrictions.in("groupid", new Integer[] { mygroup.getGroupid(), mygroup.getParentid() }));
//			else {
//				dc.add(Restrictions.not(Restrictions.in("groupid", new Integer[] { mygroup.getGroupid(),
//						mygroup.getParentid() })));
//			}
//			if (groupid != -1) {
//				// dc.add(Restrictions.eq("lessons.groupid", groupid));
//				dc.add(Restrictions.or(Restrictions.eq("groupid", groupid), Restrictions.eq("provinceid", groupid)));
//				// hql += " and a.lessons.groupid=" + groupid;
//			}
//			if (title != null && !"".equals(title)) {
//				dc.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
//			}
//			if (teachers != null && !"".equals(teachers)) {
//				dc.add(Restrictions.like("teachers", teachers, MatchMode.ANYWHERE));
//			}
//			if (lessontype != 0) {
//				dc.add(Restrictions.eq("lessontype", lessontype));
//			}
//			if (start != null && end != null)
//				dc.add(Restrictions.between("lessondate", start, end));
//			dc.add(Restrictions.in("lessonstyle", new Integer[] { 1, 3 }));
//			dc.addOrder(Order.desc("lessondate"));
//			PaginationSupport page = lessonsDAO.findPageByCriteria(dc, pageSize, pageNo);
//			return page;
//		} else {
//
//			DetachedCriteria dc = DetachedCriteria.forClass(Lessonshared.class);
//			dc.createAlias("lessons", "lessons");
//			String hql = "select distinct a.lessons from Lessonshared a where 1=1 ";
//
//			if (mygroup != null && mygroup.getGrouptype() <= 3) {// mygroup为null的话,能看到所有律协的
//				List<Integer> groupids = new ArrayList<Integer>();
//				String str = "";
//				if (mygroup.getGrouptype() == 1) {
//					groupids.add(mygroup.getGroupid());
//					groupids.add(mygroup.getParentid());
//					groupids.add(mygroup.getDirectgroup());
//					str = mygroup.getGroupid() + "," + mygroup.getParentid() + "," + mygroup.getDirectgroup();
//
//				} else if (mygroup.getGrouptype() == 2) {
//					groupids.add(mygroup.getGroupid());
//					groupids.add(mygroup.getParentid());
//					str = mygroup.getGroupid() + "," + mygroup.getParentid();
//				} else if (mygroup.getGrouptype() == 3) {
//					groupids.add(mygroup.getGroupid());
//					str = mygroup.getGroupid() + "";
//				}
//				dc.add(Restrictions.in("groupid", groupids));
//				hql += " and a.groupid in(" + str + ")";
//			}
//			// 不显示删除的
//			dc.add(Restrictions.eq("lessons.deleteflag", false));
//			hql += " and a.lessons.deleteflag=false";
//			// 具体的来源
//			if (groupid != -1) {
//				// dc.add(Restrictions.eq("lessons.groupid", groupid));
//				dc.add(Restrictions.or(Restrictions.eq("lessons.groupid", groupid), Restrictions.eq(
//						"lessons.provinceid", groupid)));
//				// hql += " and a.lessons.groupid=" + groupid;
//				hql += " and (a.lessons.groupid=" + groupid + " or a.lessons.provinceid=" + groupid+")";
//			}
//
//			if (title != null && !"".equals(title)) {
//				dc.add(Restrictions.like("lessons.title", title, MatchMode.ANYWHERE));
//				hql += " and a.lessons.title like '%" + title + "%'";
//			}
//			if (teachers != null && !"".equals(teachers)) {
//				dc.add(Restrictions.like("lessons.teachers", teachers, MatchMode.ANYWHERE));
//				hql += " and a.lessons.teachers like '%" + teachers + "%'";
//			}
//			if (lessontype != 0) {
//				dc.add(Restrictions.eq("lessons.lessontype", lessontype));
//				hql += " and a.lessons.lessontype =" + lessontype;
//			}
//			if (lessonstyle != 0) {
//				if (lessonstyle == 1 || lessonstyle == 2)
//					dc.add(Restrictions.in("lessons.lessonstyle", new Object[] { lessonstyle, 3 }));
//				else
//					dc.add(Restrictions.eq("lessons.lessonstyle", lessonstyle));
//				hql += " and a.lessons.lessonstyle in(" + lessonstyle + ",3)";
//			}
//			if (start != null && end != null) {
//				String startStr = df.format(start) + " 00:00:00";
//				String endStr = df.format(end) + " 23:59:59";
//				dc.add(Restrictions.between("lessons.lessondate", start, end));
//				dc.add(Restrictions.between("lessons.lessondate", start, end));
//				hql += " and a.lessons.lessondate between '" + startStr + "'and '" + endStr + "'";
//			}
//
//			hql += " order by a.lessons.lessondate desc";
//
//			dc.setProjection(Projections.countDistinct("lessons"));
//			List list = lessonsDAO.findAllByCriteria(dc);
//			int len = list == null ? 0 : list.size();
//			int totalCount = len == 0 ? 0 : Integer.parseInt(list.get(0).toString());
//			// 根据授课时间进行排序
//			// detachedCriteria.addOrder(Order.desc("lessons.lessondate"));
//			PaginationSupport page = lessonsDAO.findByQuery(hql, pageSize, pageNo, totalCount);
//			return page;
//		}

//	}
	
	
	/**
	 * 课程听课的统计情况
	 * @param teacherid
	 * @param title
	 * @param from
	 * @param to
	 * @return
	 */
	public com.changpeng.common.PaginationSupport getLessonStatic(int type,Timestamp from,Timestamp to,int pageNo,int pageSize) {
		int startIndex=(pageNo-1)*pageSize;
		String where=" where 1=1 ";		
		if(from!=null){
			where+=" and firsttime >'2011-11-24' "; 
		}if(to!=null){
			where+=" and firsttime <'2011-11-30' "; 
		}
		
		String sql="select count(lessonid) as cnt,lessonid from log_lesson_listen "+where+" group by lessonid";
		String totalsql="select count(a.lessonid) from ("+sql+") a inner join lessons b on a.lessonid=b.lessonid";
		String pagesql="select a.*,b.pic,b.title from ("+sql+") a inner join lessons b on a.lessonid=b.lessonid order by a.cnt desc limit "+startIndex+","+pageSize;
		_LOG.debug("pageSql:::"+pagesql);
		_LOG.debug("totalsql:::"+totalsql);
		int totalCount=lessonsDAO.getCountBySqlQuery(totalsql);
		
		List list=lessonsDAO.findBySqlQuery(pagesql);
		int len=list==null?0:list.size();
		List result=new ArrayList();
		for(int i=0;i<len;i++){
			Object[] obj=(Object[])list.get(i);

			LessonStatic lstatic=new LessonStatic();
			if(obj[2]!=null){
				System.out.println("PIC :::::"+obj[2].toString());
				lstatic.setPic(obj[2].toString());
			}else{
				lstatic.setPic(null);
			}
			lstatic.setTitle(obj[3].toString());
			lstatic.setLessonid(((Integer)obj[1]).intValue());			
			lstatic.setCount(((BigInteger)obj[0]).intValue());
			result.add(lstatic);
		}
		
		if(list!=null){
			list.clear();
			list=null;
		}
		PaginationSupport ps = new PaginationSupport(result, totalCount, pageSize, startIndex);
		return ps;
	}
	
	
	/**
	 * 一周课程听课的统计情况
	 * @param teacherid
	 * @param title
	 * @param from
	 * @param to
	 * @return
	 */


}
