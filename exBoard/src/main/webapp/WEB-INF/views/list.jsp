<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 목록</title>
<%--css--%>
<style>

</style>

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
          </tr>
      </tbody>
  </table>

</body>
</html>
