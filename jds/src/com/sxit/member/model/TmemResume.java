package com.sxit.member.model;
// Generated 2008-5-28 10:53:41 by Hibernate Tools 3.2.0.b9


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TmemResume generated by hbm2java
 */
public class TmemResume  implements java.io.Serializable {


     private int resumeid;
     private TmemMember tmemMember;
     private String username;
     private String mobile;
     private String email;
     private int age;
     private Date birthday;
     private String school;
     private String major;
     private String linkaddr;
     private int statusid;
     private String enname;
     private Long marragestat;
     private String englishlevel;
     private Timestamp createdate;
     private String resumelevel;
     private String remark;
     private Long workyears;
     private String hobbies;
     private String selfeval;
     private String skilldesc;
     private String idcard;
     private String phone;
     private int sex;
     private String health;
     private Date graduateddate;
     private String degree;
     private String educationallevel;
     private String birthplace;
     private String registplace;
     private String nation;
     private Long salary;
     private Set<TmemWorkexp> tmemWorkexps = new HashSet<TmemWorkexp>(0);
     private Set<TmemStudyexp> tmemStudyexps = new HashSet<TmemStudyexp>(0);
     private Set<TmemSkill> tmemSkills = new HashSet<TmemSkill>(0);

     private String rzdate;
     private String zzdate;
     private String lzdate;
     private String sbdate;
     
    public String getRzdate() {
		return rzdate;
	}


	public String getZzdate() {
		return zzdate;
	}


	public String getLzdate() {
		return lzdate;
	}


	public String getSbdate() {
		return sbdate;
	}


	public void setRzdate(String rzdate) {
		this.rzdate = rzdate;
	}


	public void setZzdate(String zzdate) {
		this.zzdate = zzdate;
	}


	public void setLzdate(String lzdate) {
		this.lzdate = lzdate;
	}


	public void setSbdate(String sbdate) {
		this.sbdate = sbdate;
	}


	public TmemResume() {
    }

	
    public TmemResume(int resumeid, TmemMember tmemMember) {
        this.resumeid = resumeid;
        this.tmemMember = tmemMember;
    }
    public TmemResume(int resumeid, TmemMember tmemMember, String username, String mobile, String email, int age, Date birthday, String school, String major, String linkaddr, int statusid, String enname, Long marragestat, String englishlevel, Timestamp createdate, String resumelevel, String remark, Long workyears, String hobbies, String selfeval, String skilldesc, String idcard, String phone, int sex, String health, Date graduateddate, String degree, String educationallevel, String birthplace, String registplace, String nation, Long salary, Set<TmemWorkexp> tmemWorkexps, Set<TmemStudyexp> tmemStudyexps, Set<TmemSkill> tmemSkills) {
       this.resumeid = resumeid;
       this.tmemMember = tmemMember;
       this.username = username;
       this.mobile = mobile;
       this.email = email;
       this.age = age;
       this.birthday = birthday;
       this.school = school;
       this.major = major;
       this.linkaddr = linkaddr;
       this.statusid = statusid;
       this.enname = enname;
       this.marragestat = marragestat;
       this.englishlevel = englishlevel;
       this.createdate = createdate;
       this.resumelevel = resumelevel;
       this.remark = remark;
       this.workyears = workyears;
       this.hobbies = hobbies;
       this.selfeval = selfeval;
       this.skilldesc = skilldesc;
       this.idcard = idcard;
       this.phone = phone;
       this.sex = sex;
       this.health = health;
       this.graduateddate = graduateddate;
       this.degree = degree;
       this.educationallevel = educationallevel;
       this.birthplace = birthplace;
       this.registplace = registplace;
       this.nation = nation;
       this.salary = salary;
       this.tmemWorkexps = tmemWorkexps;
       this.tmemStudyexps = tmemStudyexps;
       this.tmemSkills = tmemSkills;
    }
   
    public int getResumeid() {
        return this.resumeid;
    }
    
    public void setResumeid(int resumeid) {
        this.resumeid = resumeid;
    }
    public TmemMember getTmemMember() {
        return this.tmemMember;
    }
    
    public void setTmemMember(TmemMember tmemMember) {
        this.tmemMember = tmemMember;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return this.age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }
    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
    }
    public String getLinkaddr() {
        return this.linkaddr;
    }
    
    public void setLinkaddr(String linkaddr) {
        this.linkaddr = linkaddr;
    }
    public int getStatusid() {
        return this.statusid;
    }
    
    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
    public String getEnname() {
        return this.enname;
    }
    
    public void setEnname(String enname) {
        this.enname = enname;
    }
    public Long getMarragestat() {
        return this.marragestat;
    }
    
    public void setMarragestat(Long marragestat) {
        this.marragestat = marragestat;
    }
    public String getEnglishlevel() {
        return this.englishlevel;
    }
    
    public void setEnglishlevel(String englishlevel) {
        this.englishlevel = englishlevel;
    }
    public Timestamp getCreatedate() {
        return this.createdate;
    }
    
    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }
    public String getResumelevel() {
        return this.resumelevel;
    }
    
    public void setResumelevel(String resumelevel) {
        this.resumelevel = resumelevel;
    }
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getWorkyears() {
        return this.workyears;
    }
    
    public void setWorkyears(Long workyears) {
        this.workyears = workyears;
    }
    public String getHobbies() {
        return this.hobbies;
    }
    
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
    public String getSelfeval() {
        return this.selfeval;
    }
    
    public void setSelfeval(String selfeval) {
        this.selfeval = selfeval;
    }
    public String getSkilldesc() {
        return this.skilldesc;
    }
    
    public void setSkilldesc(String skilldesc) {
        this.skilldesc = skilldesc;
    }
    public String getIdcard() {
        return this.idcard;
    }
    
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getSex() {
        return this.sex;
    }
    
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getHealth() {
        return this.health;
    }
    
    public void setHealth(String health) {
        this.health = health;
    }
    public Date getGraduateddate() {
        return this.graduateddate;
    }
    
    public void setGraduateddate(Date graduateddate) {
        this.graduateddate = graduateddate;
    }
    public String getDegree() {
        return this.degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getEducationallevel() {
        return this.educationallevel;
    }
    
    public void setEducationallevel(String educationallevel) {
        this.educationallevel = educationallevel;
    }
    public String getBirthplace() {
        return this.birthplace;
    }
    
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
    public String getRegistplace() {
        return this.registplace;
    }
    
    public void setRegistplace(String registplace) {
        this.registplace = registplace;
    }
    public String getNation() {
        return this.nation;
    }
    
    public void setNation(String nation) {
        this.nation = nation;
    }
    public Long getSalary() {
        return this.salary;
    }
    
    public void setSalary(Long salary) {
        this.salary = salary;
    }
    public Set<TmemWorkexp> getTmemWorkexps() {
        return this.tmemWorkexps;
    }
    
    public void setTmemWorkexps(Set<TmemWorkexp> tmemWorkexps) {
        this.tmemWorkexps = tmemWorkexps;
    }
    public Set<TmemStudyexp> getTmemStudyexps() {
        return this.tmemStudyexps;
    }
    
    public void setTmemStudyexps(Set<TmemStudyexp> tmemStudyexps) {
        this.tmemStudyexps = tmemStudyexps;
    }
    public Set<TmemSkill> getTmemSkills() {
        return this.tmemSkills;
    }
    
    public void setTmemSkills(Set<TmemSkill> tmemSkills) {
        this.tmemSkills = tmemSkills;
    }




}

