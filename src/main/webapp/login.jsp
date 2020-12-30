<%--
  Created by IntelliJ IDEA.
  User: Angular
  Date: 2020/12/22
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>登陆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
</head>
<body>
<div class="main-wthree">
    <div class="container">
        <div class="sin-w3-agile">
            <h2>Sign In</h2>
            <%-- <form action="${base }/login" method="post"> --%>
            <form id="form" action="#" method="post">
                <div class="username">
                    <span class="username">账号:</span>
                    <input type="text" name="userName" class="name" placeholder="" required="">
                    <div class="clearfix"></div>
                </div>
                <div class="password-agileits">
                    <span class="username">密码:</span>
                    <input type="password" name="userPwd" class="password" placeholder="" required="">
                    <div class="clearfix"></div>
                </div>
                <div class="rem-for-agile">
                    <input type="checkbox" name="remember" class="remember">记住我<br>
                    <!-- <a href="#">忘记密码</a><br> -->
                </div>
                <div class="login-w3">
                    <!-- <input type="submit" class="login" value="登陆"> -->
                    <input type="button" class="login" value="登陆" onclick="login()">
                </div>
                <div class="clearfix"></div>
            </form>
            <div class="back">
                <a href="https://blog.csdn.net/caiqing116" target="_blank">去主人博客</a>
            </div>
            <div class="footer">
                <p>&copy; 2018 Design by <a href="https://blog.csdn.net/caiqing116" target="_blank">https://blog.csdn.net/caiqing116</a></p>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src=<%=basePath%>"/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src=<%=basePath%>"/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src=<%=basePath%>"/js/security.js"></script>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">
    function login(){
        $.ajax({
            url: "http://localhost:9527/SSMARC/ssmarc/shirologin",
            type: "post",
            data: $("#form").serialize(),
            dataType: "json",
            cache: false,
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function () {
            },
            complete: function () {
            },
            success: function (result) {
                if (result.code == 200) {
                    window.location.href = "index.jsp";
                } else {
                    alert(result.msg);
                }
            }, error: function (result) {
                alert("网络连接失败！"+result.resultCode);
            }
        });
    }
</script>
</html>

