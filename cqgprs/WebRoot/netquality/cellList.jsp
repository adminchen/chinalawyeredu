<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-小区列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 var orderArray=["cellid","lac","bscid","updatetime"];
 var field="${orderfield}";
var ascdesc="${ascdesc}";
 Array.prototype.clear=function(){  
    this.length=0;  
}
  $(document).ready(function() {
			$("#checkAll").click(selectAll);
			$("[name=check]").click(selectOne);
  });
  var arrayall=new Array();
  var arrayadd=new Array();
	<s:iterator value="page.items">
       arrayall.push("${cellid }");
	</s:iterator>
  function selectAll() {
	var checked=$(this).attr('checked');
	var checkboxa = $("#checkForm :checkbox");
	$.each(checkboxa,function(){
	   if(!checked){
	    	$(this).attr('checked','');
		   	$(this).parent().parent().children().addClass("nomal");
			$(this).parent().parent().children().removeClass("current");
	   }else{
		    $(this).attr('checked','checked');
		    $(this).parent().parent().children().addClass("current");
		    $(this).parent().parent().children().removeClass("nomal");
	   }
	});
  }

		/*单选取值*/
		function selectOne(){
				if($(this).attr('checked')){
					$(this).parent().parent().children().addClass("current");
					$(this).parent().parent().children().removeClass("nomal");
				}else{
					$(this).parent().parent().children().addClass("nomal");
					$(this).parent().parent().children().removeClass("current");
					$("#checkAll").attr('checked','');
				}
      }
      function getChecked(){
         var checkbox = $("#checkForm :checkbox");
         arrayadd.clear();
	     $.each(checkbox,function(){
	      if($(this).attr('checked')){
		    arrayadd.push( $(this).attr("value"));
	       }
	    });
      }

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


function setit(){
  getChecked();
  $.getJSON('../netqualityajax/setCellFocus.action?all='+arrayall.join()+'&selected='+arrayadd.join(),function(data){
        alert(data.msg);
  });
}


</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>网络质量</a>＞<em>资源列表</em>＞<em>小区信息列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="cellList" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
								 <s:hidden name="orderfield" id="orderfieldid"/>
								      <s:hidden name="ascdesc" id="ascdescid"/>
                                 <s:hidden name="pageNo"/>
                                    <s:hidden name="resultType"/>
								 <td>小区编号：<s:textfield name="cellid" cssClass="txt" size="6"/>&nbsp;</td>
								  <td>LAC：<s:textfield name="lac" cssClass="txt" size="6"/>&nbsp;</td>
								 <td>BSCID：<s:textfield name="bscid" cssClass="txt" size="10"/>&nbsp;</td>
								 
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							 <td><input type="button" class="btnSubmit" value="设置为重点小区" onclick="setit()"/></td>
							
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>-->
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                         <th title="点中全选">
                          设置为重点小区
                          <input type="checkbox" class="checkbox" name="checkAll"  id="checkAll"/>
                          </th>
                       <th><a onclick="orderByThis(document.form1,this)" id="cellid" title="点击排序">小区编号</a></th>
                       
                       <th>小区名称</th>
                       <th><a onclick="orderByThis(document.form1,this)" id="bscid" title="点击排序">所属BSC/RNC</a></th>
                       <th>归属SGSN</th>
                       <th>所属城区</th>
                       <th><a onclick="orderByThis(document.form1,this)" id="updatetime" title="点击排序">最后更新时间</a></th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                     <td align="center">
                     <s:if test="focuslist.contains(cellkey)">
                      <input type="checkbox" class="checkbox" name="check" value="${lac }-${cellid }" checked/>
                     </s:if>
                     <s:else>
                      <input type="checkbox" class="checkbox" name="check" value="${lac }-${cellid }"/>
                     </s:else>
                    </td>
                         <td>
                          <a href="../stat/streamCellTime.action?lac=${lac }&cellid=${cellid }" title="点此查看此小区在昨天的分时流量">${cellkey}</a>
                      </td>
                          <td>${cellname}</td>
                          <td>${bscrncid }</td>
                          <td><s:property value="@com.sxit.netquality.service.BasicSetService@BSC_SGSN[bscrncid]"/></td>
                          <td>${subarea}</td>
                          <td><s:date name="lastupdate" format="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="7" class="fright">
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

