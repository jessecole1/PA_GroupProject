<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="formBox">
		<form:form class="theForm" action="/login/user" method="post" modelAttribute="newLoginUser">
			<div class="form">
				<form:input class="inputFields" path="email" value="email"/>
				<form:input class="inputFields" path="password" value="password"/>
				<button class="button register">Log In</button>
			</div>
		</form:form>
	</div>
	<div class="backToLogin">
		<a href="/"><button class="button butTwo">Back</button></a>
		<a href="/register"><button class="button butTwo login">Create New Account</button></a>
	</div>
</body>
</html>