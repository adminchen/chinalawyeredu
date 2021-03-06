/**
 * 
 */
package com.sxit.useraction.models;

import com.sxit.system.util.NumberUtil;

/**
 * @author 华锋
 * Nov 9, 2009-11:01:03 PM
 *
 */
public class MobilesTop implements java.io.Serializable{

	private String mobile;
	private String apnni;
	private String date;
	private String datehour;
	private float upvolume;
	private float downvolume;
	private float allvolume;
	
	public String getDownvolumeStr(){
		if(downvolume<10)
			return downvolume+"（K）";
		double d = ((double) downvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}
	public String getUpvolumeStr(){
		if(upvolume<10)
			return upvolume+"（K）";
		double d = ((double) upvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
//		return Float.parseFloat(totalStreamStr);
		return totalStreamStr;
	}
	public String getAllvolumeStr(){
		if(allvolume<10)
			return allvolume+"（K）";
		double d = ((double) allvolume) / (1024);
		String totalStreamStr= NumberUtil.toMoney(d);
		return totalStreamStr;
	}
	
	
	public String getPeriodlenStr(){
		
		if(periodlen<60)
			return periodlen+"秒";
		else if(periodlen<3600){
			int min=periodlen/60;
			int mod=periodlen%60;
			
			if(mod==0)
				return min+"分";
			else
				return min+"分"+mod+"秒";
		}else{
			
			int hour=periodlen/3600;
			int remin=periodlen%3600;
			int min=remin/60;
			int sec=remin%60;
			
			if(remin==0)
				return hour+"小时";
			else{
				if(sec==0)
					return hour+"小时"+min+"分";
				else
					return hour+"小时"+min+"分"+sec+"秒";
			}
			
		}
		
	}
	
	public static void main(String args[]){
		MobilesTop t=new MobilesTop();
		t.periodlen=60;
		System.out.println(t.getPeriodlenStr());
		t.periodlen=1800;
		System.out.println(t.getPeriodlenStr());
		t.periodlen=3600;
		System.out.println(t.getPeriodlenStr());
		t.periodlen=20300;
		System.out.println(t.getPeriodlenStr());
	}
	
	private int periodlen;
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the apnni
	 */
	public String getApnni() {
		return apnni;
	}
	/**
	 * @param apnni the apnni to set
	 */
	public void setApnni(String apnni) {
		this.apnni = apnni;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the datehour
	 */
	public String getDatehour() {
		return datehour;
	}
	/**
	 * @param datehour the datehour to set
	 */
	public void setDatehour(String datehour) {
		this.datehour = datehour;
	}
	/**
	 * @return the upvolume
	 */
	public float getUpvolume() {
		return upvolume;
	}
	/**
	 * @param upvolume the upvolume to set
	 */
	public void setUpvolume(float upvolume) {
		this.upvolume = upvolume;
	}
	/**
	 * @return the downvolume
	 */
	public float getDownvolume() {
		return downvolume;
	}
	/**
	 * @param downvolume the downvolume to set
	 */
	public void setDownvolume(float downvolume) {
		this.downvolume = downvolume;
	}
	/**
	 * @return the periodlen
	 */
	public int getPeriodlen() {
		return periodlen;
	}
	/**
	 * @param periodlen the periodlen to set
	 */
	public void setPeriodlen(int periodlen) {
		this.periodlen = periodlen;
	}
	/**
	 * @return the allvolume
	 */
	public float getAllvolume() {
		return allvolume;
	}
	/**
	 * @param allvolume the allvolume to set
	 */
	public void setAllvolume(float allvolume) {
		this.allvolume = allvolume;
	}
}
