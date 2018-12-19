<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr" dir="ltr">
  <head>
    <title>작성폼</title>

  </head>
  <body>
    <table>
      <thead>
      <h2>입력</h2>
      </thead>
      <tbody>
        <form class="" action="/writeform" method="post">
          <tr>
            <td>제목 <input type="text" name="title" value="" size="50"> </td>
          </tr>
          <tr>
            <td>작성자 <input type="text" name="writer" value="" size="10"> </td>
          </tr>
          <tr>
            <td>내용</td>
          </tr>
          <tr>
            <td><textarea name="contents" rows="13" cols="58"></textarea></td>
          </tr>
          <tr>
            <td>
              <button type="submit">작성</button>
              <input type="button" name="" value="리스트" onclick="location.href='/list'">  </td>
          </tr>
        </form>
      </tbody>
    </table>

  </body>
</html>
