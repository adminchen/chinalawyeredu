package com.changpeng.operation.action;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sxit.common.action.AbstractAction;
import com.changpeng.operation.model.*;

/**
 *
 * <p>功能： 删除多个催收日志</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-14</p>
 * @版本： V1.0
 * @修改：
 */

public class CreditlogDeletesAction extends AbstractAction {
  private Long[] check;
  private long credittaskid;


public CreditlogDeletesAction() {
    rights="opr4,8";
   
  }

  protected String go() throws HibernateException {
	 nextpage="creditlogList.action?credittaskid="+credittaskid;
    try {
      int num = this.getDelete(check);
      message = "删除" + num + "个催收日志成功！";
      return "message";
    }
    catch (HibernateException e) {
      e.printStackTrace();
      message = "删除多个催收日志失败！";
      return "message";
    }
  }

  private int getDelete(Long[] check) throws HibernateException {
    Session session = getSession();
    String hqlDelete = "delete from ToprCreditlog where logid in (:creditlogids)";
    int deletedEntities = session.createQuery(hqlDelete)
        .setParameterList("creditlogids", check)
        .executeUpdate();
    return deletedEntities;
  }

  public Long[] getCheck() {
    return check;
  }

  public void setCheck(Long[] check) {
    this.check = check;
  }

public long getCredittaskid() {
	return credittaskid;
}

public void setCredittaskid(long credittaskid) {
	this.credittaskid = credittaskid;
}

}
