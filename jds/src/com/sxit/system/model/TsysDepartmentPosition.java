package com.sxit.system.model;
// Generated 2007-10-12 14:12:09 by Hibernate Tools 3.2.0.b9



/**
 * TsysDepartmentPosition generated by hbm2java
 */
public class TsysDepartmentPosition  implements java.io.Serializable {


     private TsysDepartmentPositionPK comp_id;
     private TsysUser tsysUser;

    public TsysDepartmentPosition() {
    }

	
    public TsysDepartmentPosition(TsysDepartmentPositionPK comp_id) {
        this.comp_id = comp_id;
    }
    public TsysDepartmentPosition(TsysDepartmentPositionPK comp_id, TsysUser tsysUser) {
       this.comp_id = comp_id;
       this.tsysUser = tsysUser;
    }
   
    public TsysDepartmentPositionPK getComp_id() {
        return this.comp_id;
    }
    
    public void setComp_id(TsysDepartmentPositionPK comp_id) {
        this.comp_id = comp_id;
    }
    public TsysUser getTsysUser() {
        return this.tsysUser;
    }
    
    public void setTsysUser(TsysUser tsysUser) {
        this.tsysUser = tsysUser;
    }




}


