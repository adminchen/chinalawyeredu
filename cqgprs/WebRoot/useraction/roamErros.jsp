<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName}-漫游错误详表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 $("#tableOrder").tablesorter();
 });
 

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
  document.form1.resultType.value="excel";
  document.form1.submit();
}
function queryit(){
  document.form1.resultType.value="list";
  document.form1.submit();
}


</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>用户行为</a>＞<em>漫游错误详表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="roamErros" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                  <s:hidden name="resultType"/>
								 <td>选择日期：<jscalendar:jscalendar name="date" cssClass="txt"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
								<td><input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/></td>
							
							
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>-->
				<div class="tablist"> 
						<table class="tableBox"> 
							<thead> 
								<tr> 
									<th>漫游错误总数</th> 
									<th>漫游错误用户数</th> 
								</tr> 
							</thead> 
							<tbody> 
								<tr> 
									<td>${codestat.errorcount }</td> 
									<td>${codestat.usercount }</td> 
								</tr> 
							</tbody> 
						</table> 
					</div> 
				
				
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="tableOrder">
                      <thead>
                        <tr>
                       
                          <th>用户IMSI</th>
                          <th>请求APN</th>
                          <th>PDP失败次数	</th>
                    
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="codestat.detailist" status="status">
                        <tr>
                         <td>${imsi}</td>
                          <td>${apn}</td>
                          <td>${pdperrorcnt }</td>
                      
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    
                    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
		
                    </table>
			  </div>

			 <div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>
				</div>
			</div>
		</div>
</s:form>
</body>
</html>

