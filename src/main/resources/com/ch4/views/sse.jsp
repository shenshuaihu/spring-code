<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>SSE-Code</title>
    <link rel="stylesheet" type="text/css" value="">
</head>
<body>
<h2> sse.jsp </h2>

服务器推送  可以用于消息订阅
<br/>
解决长短轮询不是解决问题
<br/>
参考内容：
https://www.jianshu.com/p/bc5a9b4a1cd1

<div id="msgFromPush"></div>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<script>
    console.log("!!Window EventSource: " + !!Window.EventSource)
    if (!!window.EventSource) {
        var source = new EventSource('push');
        s = '';
        source.addEventListener('message', function (evt) {
            s += evt.data + "<br/>";
            $("#msgFromPush").html(s);
        });

        source.addEventListener('open', function (evt) {
            console.log("连接打开.")
        })

        // 添加SSE客户端监听，获取服务端推送的消息
        source.addEventListener('error', function (evt) {
            if (evt.readyState == EventSource.CLOSED) {
                console.log("连接关闭.")
            } else {
                console.log(evt.readyState)
            }
        }, false);

    } else {
        console.log("你的浏览器不支持SSE.")
    }


    /*if(window.EventSource){

        var eventSource = new EventSource("http://localhost:8080/push");

        //只要和服务器连接，就会触发open事件
        eventSource.addEventListener("open",function(){
            console.log("和服务器建立连接");
        });

        //处理服务器响应报文中的load事件
        eventSource.addEventListener("load",function(e){
            console.log("服务器发送给客户端的数据为:" + e.data);
        });

        //如果服务器响应报文中没有指明事件，默认触发message事件
        eventSource.addEventListener("message",function(e){
            console.log("服务器发送给客户端的数据为:" + e.data);
        });

        //发生错误，则会触发error事件
        eventSource.addEventListener("error",function(e){
            console.log("服务器发送给客户端的数据为:" + e.data);
        });

    }
    else{
        console.log("服务器不支持EvenSource对象");
    }*/

</script>
</body>
</html>
