<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
    
<form name="frmForm" id="_frmForm" method="post" action="pdsupdate.do">

<input type="hidden" name="seq" value="${pds.seq}"/>

<table class="list_table" style="width:85%;">

<colgroup>
<col style="width:200px;" />
<col style="width:auto;" />
</colgroup>

<tbody>	
	<tr class="id">
		<th>아이디</th>
		<td style="text-align: left">${pds.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td style="text-align: left">${pds.title}</td>
	</tr>
	<tr>
		<th>사진</th>
			<img alt="" src = "upload/${pds.newfilename}">
		</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td style="text-align: left">${pds.readcount}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td style="text-align: left"><textarea rows="20px" cols="80px" 
		name='content' id="_content">${pds.content}</textarea></td>
	</tr>
	<tr>
		<td colspan="2" style="height:50px; text-align:center;">
		<span>
			<c:if test="${pds.id eq login.id}">
			<a href="#none" id="_btnUpdate" title="글수정하기"><img src="image/bupdate.png" alt="수정하기" /></a>
			<a href="#none" id="_btnDel" title="삭제하기"><img src="image/del.png" alt="삭제하기" /></a>
			</c:if>
			<a href="#none" id="_btnReply" title="답글달기"><img src="image/breply.png" alt="답글달기" /></a>
		</span>
		</td>
	</tr>
</tbody>
</table>

</form>


<script type="text/javascript">

$("#_btnUpdate").click(function() {	
	alert('글수정하기');		
	$("#_frmForm").attr({ "target":"_self", "action":"pdsupdate.do" }).submit();
});
$("#_btnReply").click(function() {	
	alert('답글달기');	
	$("#_frmForm").attr({ "target":"_self", "action":"answer.do" }).submit();
});
$("#_btnDel").click(function() {			
	$("#_frmForm").attr({ "target":"_self", "action":"pdsdelete.do" }).submit();
});

</script>

    
    