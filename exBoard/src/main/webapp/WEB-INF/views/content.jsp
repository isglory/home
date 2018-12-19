<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <title>상세</title>
  </head>
  <body>
    <h2>상세내용</h2>
    <form class="" action="/update" method="post">
    <table>
      <thead>
        <tr>
          <td>글번호 <input type="text" name="idx" value="${board.idx}" size="4">
          작성자<input type="text" name="writer" value="${board.writer}" size="5">
          조회수 <input type="text" name="hit_cnt" value="${board.hit_cnt}" size="4">
          </td>
        </tr>
        <tr>
          제목 <input type="text" name="title" value="${board.title}" size="20">
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><textarea name="contents" rows="16" cols="50">${board.contents}</textarea></td>
        </tr>
        <tr>
          <td>
            <input type="button" name="" value="목록" onclick="location.href='/list'">
            <input type="submit" name="" value="수정">
            <input type="button" name="idx" value="삭제" onclick="location.href='/del?${board.idx}'">
          </td>
        </tr>
      </tbody>
    </table>
    </form>
  </body>
</html>
