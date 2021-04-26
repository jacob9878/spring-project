<%@page import="bit.com.a.dto.CalendarPlugDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath() %>/fcalLib/main.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/fcalLib/main.js"></script>

<style>
.body {
  margin: 40px 10px;
  padding: 0;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 12px;
}

#calendar {
  max-width: 900px;
  margin: 0 auto;
} 
</style>

<%
List<CalendarPlugDto> list = (List<CalendarPlugDto>)request.getAttribute("callist");
/*
for(CalendarPlugDto c : list){
	System.out.println(c.toString());
}
*/

// JSONObject, JSONArray <-- java json

String events = "[";

for(CalendarPlugDto c : list){
	events += "{ id:'" + c.getId() + "', title:'" + c.getTitle() + "', start:'" + c.getStartdate() + "', end:'" + c.getEnddate() + "', constraint:'" + c.getContent() + "' },";	
}
events = events.substring(0, events.lastIndexOf(","));
events += "]";

System.out.println(events);

request.setAttribute("events", events);
%>




<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function () {
	
	let calendarEl = document.getElementById('calendar');
	
	let calendar = new FullCalendar.Calendar(calendarEl, {
		
		headerToolbar: {
			left: "prev,next today",
			center: "title",
			right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
		},
		initialDate: new Date(),     // '2021-05-01',	처음 실행시 기분이 되는 날짜
		locale: 'ko',				 // 한글설정
		navLinks: true,
		businessHours: true,
		events:<%=request.getAttribute("events") %>			
	});
	
	calendar.render();	// rendering	
	
	calendar.on("dateClick", function (info) {
		//alert('dateClick');
		$('#exampleModal').modal('show');
		$("#date").val(info.dateStr);
	});
		
});
</script>


<div class="body">
	<div id="calendar"></div>
</div>




<!-- 모달 창 테두리 -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <!-- 모달 대화창 본체-->
      <div class="modal-dialog" role="document">
        <!-- 모달 콘텐츠 부분 -->
        <div class="modal-content">
          <!-- 모달 헤더ー -->
          <div class="modal-header">
            <!-- 모달 제목 -->
            <h5 class="modal-title" id="exampleModalLabel">모달 제목</h5>
            <!-- 닫기 아이콘 -->
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
             <span aria-hidden="true">&times;</span>
            </button>
          </div>
          
          <!-- 모달 본문 -->
          <div class="modal-body">
          	<div class="form-group row">
	          <label for="name" class="col-md-3 col-form-label">날짜</label>
	          <div class="col-md-9">
	            <input type="text" class="form-control" id="date" required>
	          </div>
	        </div>
          </div>
          
          
          <!-- 모달 꼬리말 -->
          <div class="modal-footer">
          	<button type="button" class="btn btn-secondary">일정저장</button>
            <!-- 닫기 버튼 -->
            <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
          </div>
        </div>
      </div>
    </div>
  



