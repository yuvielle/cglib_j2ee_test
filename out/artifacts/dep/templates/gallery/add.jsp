<%--
  Created by IntelliJ IDEA.
  User: Yuvielle
  Date: 14.08.13
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Добавить изображение</h2>

  <form action="" method="POST" contentType="multipart/form-data">
      <table>
          <tr>
             <td><label>Наименование </label><input type="text" name="name"/></td>
          </tr>
          <tr>
              <td><label>файл: </label><input type="file" name="file"/></td>
          </tr>
          <tr>
              <td style="text-align: right"><input type="submit" name="submit" value="Загрузить"></td>
          </tr>
      </table>
  </form>
</body>
</html>