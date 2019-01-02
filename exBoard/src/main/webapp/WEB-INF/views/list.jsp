<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 목록</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function page(idx){
		var pageNum = idx;
		location.href = '/list?pageNum='+pageNum+'&keyword=${page.keyword}';
	}
	function serch(){
		var keyword = document.getElementById("keyword").value;
		location.href = '/list?keyword='+keyword;
	}
</script>

</head>
<body>
<h2>목록</h2>
<table>
      <thead>
          <tr>
              <td>번호</td>
              <td>제목</td>
              <td>작성자</td>
              <td>조회수</td>
          </tr>
      </thead>
      <tbody>
        <c:forEach items="${boardList}" var="list">
          <tr>
            <td>${list.idx}</td>
            <td><a href="/content?${list.idx}">${list.title}</a></td>
            <td>${list.writer}</td>
            <td>${list.hit_cnt}</td>
          </tr>
          </c:forEach>
          <tr>
            <td><input type="button" name="" value="글작성" onclick="location.href='/writeform'"></td>
            <td><input type="text" id="keyword"><input type="button" value="검색" onclick="serch()"></td>
          </tr>
      </tbody>
      <tfoot>
      	<tr>
      		<td colspan="4">
      			<c:if test="${page.prev}">
      				<a href="javascript:page(${page.getStartPage()-1})">&laquo</a>
      			</c:if>
      			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
 					<a href="javascript:page(${idx});">${idx}</a>			
      			</c:forEach>
      			<c:if test="${page.next}">
      				<a href="javascript:page(${page.getEndPage()+1})">&raquo</a>
      			</c:if>
      		
      		</td>
      	</tr>
      </tfoot>
  </table>

</body>
</html>
