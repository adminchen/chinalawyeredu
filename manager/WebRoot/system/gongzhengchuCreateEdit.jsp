<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${sysName }-公证处新增修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"time":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
  }
}
function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"gongzhengchu":"gongzhengchu","time":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}
function deletephoto(groupid){
if(confirm("您确实要删除这个LOGO吗?")){
var url="../systemajax/photoDelete.pl";
  $.getJSON(url, { "groupid":groupid,"now":new Date().getTime()}, function(json){
     if(json.success == "true"){
   		$("#imgdiv").empty();
      }else{
	   alert("照片删除失败");
      }
   });
}
else{
return;
}
}
function checkLoginname(loginname){	

	if((loginname == null) || (loginname.length == 0)){
	    $("#checkloginname").html("不为空且长度不超过15个字符");
		return;
	}
	var now=new Date().getTime();
	var url="../systemajax/checkLoginname.pl";
   $.getJSON(url, { "loginname": loginname,"now":now}, function(json){

     if(json.isrepeat == true){
   		$("#checkloginname").html("<font color='red'>对不起，您输入的执业证号【"+json.loginname+"】已经被其他公证处使用，请选择其他名字后再试。</font>");
   		$("#save").attr("disabled",true);
   }else{
	    $("#save").attr("disabled",false);
   }
});
}
</script>

</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
				公证处新增修改
				</td>
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>		
    <td>
    <s:form name="form1" action="gongzhengchuCreateEdit" method="post" validate="true" enctype="multipart/form-data">
     <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
         注意：登录名设置将设置为执业证号，登录密码默认为123456
         </b></font>
         
         </td>
        </tr>
		 <tr>
     
      <tr>
          <td align="right" width="20%" class="tab_content1">
             所属机构:
          </td>
          <td class="tab_content1">
          <s:if test="!isedit">

  <s:if test="datavisible.provinceview">
             <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             </s:if>
            <s:else>
              <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.provinceid]"/>-
             
              <s:hidden name="datavisible.provinceid"/>
            </s:else>
                  <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getOffices(this.value)"/>
            </s:if>
           <s:else>
             <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>
             
              <s:hidden name="datavisible.cityid"/>
            </s:else>
               
               </s:if>
               <s:else><!-- 不能修改所属律协 -->
                  <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.provinceid]"/>-
              <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>
            <s:hidden name="datavisible.provinceid"/>
              <s:hidden name="datavisible.cityid"/>
               </s:else>
            <s:hidden name="isedit"/>
             <s:hidden name="propertiesedit"/>
             <s:hidden name="properties.filename"/>
            
          </td>
        </tr>
       
        <tr>
          <td align="right" class="tab_content">
       公证处名称：
          </td>
          <td class="tab_content">
            <s:textfield name="sysGroup.groupname" size="30" maxlength="50" cssClass="text1"/>
            <span class="hint">必须为有效字符并长度不超过25个汉字</span>
          </td>
        </tr>
       
          <tr>
          <td align="right" class="tab_content1">
             公证处执业证号:
          </td>
          <td class="tab_content1">
            <s:hidden name="oldloginname"/>
            <s:textfield name="sysGroup.groupenname" size="20" maxlength="20" cssClass="text1"  onblur="checkLoginname(this.value)"/>
             <span class="hint" id="checkloginname">不为空且长度不超过20个字符</span>
          </td>
        </tr>
         <tr> 
          <td align="right" class="tab_content1">公证处LOGO: </td>
          <td class="tab_content">
          <s:if test="propertiesedit&&properties.photo!=null&&!properties.photo.equals(\"\")">
          <div id="imgdiv">
          <img src="${logopath}${properties.photo}" width="150"/>
          <a href="#" onclick="deletephoto('${properties.groupid}')"/>删除照片</a>
          </div>
          </s:if>
          
           <s:file name="upload" cssClass="text1"/>
              <br><font color="#FF0000">
        上传照片大小不能超过1M
           </font>
            </td>
        </tr>
           
     
        <tr>
          <td align="right" class="tab_content1">
             联系人:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.contacter" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             联系电话:
          </td>
          <td class="tab_content">
            <s:textfield name="sysGroup.phone" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
                <tr>
          <td align="right" class="tab_content1">
             传真:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.fax" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
          <tr>
          <td align="right" class="tab_content1">
             地址:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.address" size="35" maxlength="30" cssClass="text1"/>
          </td>
        </tr>
        <tr>
          <td align="right"   class="tab_content">
           备注信息:
          </td>
          <td class="tab_content">
             <s:textarea name="sysGroup.comments" cssClass="textarea1" cols="45" rows="4"/>
          
          </td>
        </tr>
        <tr>
          <td height="5" colspan="2">
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<input type="submit" value=" 保 存 " id="save" class="button">&nbsp;
           	<input type="reset" value=" 重 置 " class="button">&nbsp;
          	<input type="button" value=" 返 回 " onClick="javascript:history.back(-1)" class="button">
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>