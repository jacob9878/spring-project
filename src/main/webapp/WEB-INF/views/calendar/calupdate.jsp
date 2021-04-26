<%@page import="bit.com.a.dto.CalendarDto"%>
<%@page import="java.util.Calendar"%>
<%@page import="bit.com.a.dto.CalendarParam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
CalendarDto jcal=(CalendarDto)request.getAttribute("cal");

String rd = jcal.getRdate();

String year = rd.substring(0,4);
String month = rd.substring(4,6);
String day = rd.substring(6,8);
String hour = rd.substring(8,10);
String min = rd.substring(10,12);


Calendar cal=Calendar.getInstance();
int tyear=cal.get(Calendar.YEAR); //년
int tmonth=cal.get(Calendar.MONTH )+1;//월
int tday=cal.get(Calendar.DATE);//일
int thour=cal.get(Calendar.HOUR_OF_DAY);//24시
int tmin=cal.get(Calendar.MINUTE);//분


%>     
    
    
<div align="center">
<form action="" method="post" id="_frm" name="frm">
<input type="hidden" name="seq" value="${cal.seq }">
<input type="hidden" name="rdate" id="_rdate" value="">
			
<table class="list_table">
<col width="200"><col width="500"> 

<tr>
	<th>아이디</th>
	<td>
	<input type="text" name="id" readonly="readonly" size="60"
			value="${cal.id}"> 
	</td>
</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" name="title" size="60" value="${cal.title}">
	</td>
</tr>
<tr>
	<th>일정</th>
	<td style="text-align: left">
		<select name='year' id="_year">
		<%
		for(int i=tyear-5; i<tyear+6; i++){ // 5년전부터 5년후까지 보여주기
			%>
			<option  <%= year.equals(i+"") ? "selected='selected'":"" %>
			    value="<%=i%>"><%=i%></option>
			<% 
		}
		%>
		</select>년
		
		<select name='month'id="_month">
		<%
		for(int i=1; i<=12; i++){
			%>
			<option    <%= month.equals(i+"") ? "selected='selected'":"" %>
			    value="<%=i%>"><%=i%></option>
			<% 
		}
		%>
		</select>월
		<select name='day' id="_day">
		<%
		for(int i=1; 
		i<=cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
			%>
			<option    <%= day.equals(i+"") ? "selected='selected'" : "" %>
			    value="<%=i%>"><%=i%></option>
			<% 
		}
		%>
		</select>일
		<select name='hour'id="_hour">
		<%
		for(int i=0; i<24; i++){
			%>
			<option   <%= (thour+"").equals(i+"")?"selected='selected'":"" %>
			 value="<%=i%>"><%=i%></option>
			<% 
		}
		%>
		</select>시

		<select name='min'id="_min">
		<%
		for(int i=0; i<60; i++){
			%>
			<option    <%= (tmin+"").equals(i+"")?"selected='selected'":"" %>
			value="<%=i%>"><%=i%></option>
			<% 
		}
		%>
		</select>분</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="60" name="content">${cal.content}</textarea>
	</td>
</tr>

		
</table>

<br><br>
<!-- <input type="submit" value="저장">	 -->
<input type="button" id="_updatecal" value="수정">
<input type="button" id="_updatecal" value="삭제">
</form> 


<br><br>
<a href="calendarlist.do">달력으로 돌아가기</a>

</div>
<script>
$("#_updatecal").click(function () {
	let rdate =$('#_year').val()+utilEx($('#_month').val())+utilEx($('#_day').val())+utilEx($('#_hour').val())+utilEx($('#_min').val());
	$("#_rdate").val(rdate);
	
	alert(rdate);
	$("#_frm").attr("action", "calupdateAf.do").submit();	
});

function utilEx( msg ){
	return msg.length<2?"0"+msg:msg;
}

</script>


