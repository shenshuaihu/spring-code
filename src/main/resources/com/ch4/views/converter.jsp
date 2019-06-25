<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spting-Code</title>
    <link rel="stylesheet" type="text/css" value="">
</head>
<title>converter page</title>
<body>
<h2> converter.jsp </h2>

<div id="resp"></div>
<input type="button" onclick="req();" value="请求"/> <br/>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<script>
    function req() {
        $.ajax({
            url: "convert",
            data: "1-ssh",
            type: "POST",
            contentType: "application/x-wisely",
            success: function (data) {
                console.log("data :" + data)
                $("#resp").html(data);
            }
        })
    }
</script>
</body>
</html>
