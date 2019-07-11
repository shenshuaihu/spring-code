<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spting-Code</title>
    <link rel="stylesheet" type="text/css" value="">
</head>
<title>upload page</title>
<body>
<h2> upload.jsp </h2>

<div class="upload">
    <form action="upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file"/> <br/>
        <input type="submit" value="上传">
    </form>
</div>
</body>
</html>
