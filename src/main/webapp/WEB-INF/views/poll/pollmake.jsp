<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Calendar cal = Calendar.getInstance();
int tyear = cal.get(Calendar.YEAR);
int tmonth = cal.get(Calendar.MONTH);
int tday = cal.get(Calendar.DATE);
%>

<form action="pollmakeAf.do" method="post">

	<table class="list_table" style="width: 85%">
	<colgroup width="200px">
		<col width="500px">
	</colgroup>
		<tr>
			<th>아이디</th>
			<td style="text-align: left;">
				${login.id}<input type="hidden" name="id" value="${login.id}">
			</td>
		</tr>
		<tr>
			<th>투표기한</th>
			<td style="text-align: left;">
				<select name="syear" id="_year">
					<%
					for(int i = tyear; i < tyear + 6; i++) {
						%>	
						<option <%=(tyear+"").equals(i+"")?"selected='selected'":"" %>value="<%=i %>"><%=i %></option>	
						<%
					}
					%>
				</select>년
				
				<select name="smonth" id="_month">
					<%
					for(int i = 1; i <= 12; i++) {
						%>	
						<option <%=(tmonth+"").equals(i+"")?"selected='selected'":"" %>value="<%=i %>"><%=i %></option>	
						<%
					}
					%>
				</select>월
				
				<select name="sday" id="_day">
					<%
					for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
						%>	
						<option <%=(tday+"").equals(i+"")?"selected='selected'":"" %>value="<%=i %>"><%=i %></option>	
						<%
					}
					%>
				</select>일
				
				~
				
				<select name="eyear" id="_eyear">
					<%
					for(int i = tyear; i < tyear + 6; i++) {
						%>	
						<option <%=(tyear+"").equals(i+"")?"selected='selected'":"" %>value="<%=i %>"><%=i %></option>	
						<%
					}
					%>
				</select>년
				
				<select name="emonth" id="_emonth">
					<%
					for(int i = 1; i <= 12; i++) {
						%>	
						<option <%=(tmonth+"").equals(i+"")?"selected='selected'":"" %>value="<%=i %>"><%=i %></option>	
						<%
					}
					%>
				</select>월
				
				<select name="eday" id="_eday">
					<%
					for(int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
						%>	
						<option <%=(tday+"").equals(i+"")?"selected='selected'":"" %>value="<%=i %>"><%=i %></option>	
						<%
					}
					%>
				</select>일
				
				
			</td>
		</tr>
		
		<tr>
			<th>투표 내용</th>
			<td style="text-align: left;">
				<textarea rows="10" cols="50" name="question"></textarea>
			</td>
		</tr>
		
		<tr>
			<th>투표 문항수</th>
			<td style="text-align: left;">
				<select name="itemcount" onchange="pollchange(this)">
					<%
					for(int i = 2; i <= 20; i++ ){
					%>
						<option <%=(4+"").equals(i+"")?"selected='selected'":"" %> value="<%=i %>" ><%=i %></option>
					<%
					}
					%>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>투표 상세 문항</th>
			<td style="text-align: left;">
				<%
				for(int i = 1; i<=20; i++){
				%>
					<div id='poll<%=i%>'>
						<%=(i+"") %>번:<input type="text" name="poll<%=i%>" size="60">
					</div>
				<%
				}
				%>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="투표만들기">
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
 
$("#_eyear, #_emonth").on("change", function () {
	   let year = $("#_eyear").val(); 
	   let month = $("#_emonth").val();
	   let day = $("#_eday").val();
	   
	   
	   $("#_eday").empty();
	   let lastday = new Date(year, month, 0).getDate();
	   
	   for (var i = 0; i < lastday; i++) {
	      $("#_eday").append("<option value=" + (i+1) + ">" + (i+1) + "</option>");
	   }
	});
	
$("#_year, #_month").on("change", function () {
	   let year = $("#_year").val(); 
	   let month = $("#_month").val();
	   let day = $("#_day").val();
	   
	   
	   $("#_day").empty();
	   let lastday = new Date(year, month, 0).getDate();
	   
	   for (var i = 0; i < lastday; i++) {
	      $("#_day").append("<option value=" + (i+1) + ">" + (i+1) + "</option>");
	   }
	});	
 
 $(document).ready(function () {
	
	 // 보기 항복초기화
	 for(i = 5; i<=20; i++) {
		 $("#poll" + i).hide();
	 }
	 
});

function pollchange( sel ) {
	
	let val = sel.options[sel.selectedIndex].value;
	alert(val);
	
	// 초기화
	for(i = 1; i<=20; i++){
		$("#poll" + i).val("");
		$("#poll" + i).hide();
		
	}
	
	// 설정 값만큼 보여준다.
	for(i = 1; i <= val; i++){
		$("#poll" + i).show();
	}
	
} 

</script>
