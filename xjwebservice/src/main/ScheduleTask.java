package main;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import common.Globals;

/**
 * 
 * <pre>
 * </pre>
 * 
 * @修改：
 */
public class ScheduleTask extends TimerTask {
	private static Log LOG = LogFactory.getLog(ScheduleTask.class);

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	public ScheduleTask() {
	}

	// 8点整的时候执行
	private static int EXECUTE_HOUR = 8;
	private static int EXECUTE_MINUTE = 0;
	private static volatile int SCHEDULE_SEQ = 0;
	private static long LASTTIME = 0;

	private static Map<String, Boolean> HAS_BEEN = new HashMap<String, Boolean>();

	public void run() {

		Connection con = null;
		try {
			LOG.info("开始执行定时任务======================>" + (SCHEDULE_SEQ++));
			JdbcTemplate jdbcTemplate = (JdbcTemplate) Globals.getWebBean("jdbcTemplate");

			String sql = "select count(*) from user_order";
			int cc = jdbcTemplate.queryForInt(sql);
			LOG.info("订购用户数:" + cc);

			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			long now = System.currentTimeMillis();
			Date today = new Date();
//			if (hour == EXECUTE_HOUR && EXECUTE_MINUTE < 10) {
			if (hour == EXECUTE_HOUR && !HAS_BEEN.containsKey(df.format(today))) {
				Calendar cd = Calendar.getInstance();

				HAS_BEEN.put(df.format(today), true);

				LOG.info("现在时间:" + hour + ":" + minute + ",开始短信的下发");
				DxSendMain main = new DxSendMain();
				main.process();

				if (minute != 0) {
					EXECUTE_MINUTE = minute;
				} else
					EXECUTE_MINUTE = -1;

			} else {
				EXECUTE_MINUTE = 0;
				LOG.info("未到短信下发时间,忽略,本次完成>" + hour + ":" + minute);
			}

		} catch (Exception e) {
			LOG.error("执行定时任务错误:", e);
		}
	}

	public static void main(String[] args) {

	}
}