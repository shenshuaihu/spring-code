<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>async support-Code</title>
    <link rel="stylesheet" type="text/css" value="">
</head>
<body>
<h2> defer.jsp </h2>



<div id="defer"></div>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<script>

    deferred();

    function deferred() {
        $.get('defer', function (data) {
            console.log(data);
            s = '';
            s += data + "<br/>";
            $('#defer').html(s)
            // 完成后在向服务器请求
            deferred();
            }

        );
    }

</script>
</body>
</html>
