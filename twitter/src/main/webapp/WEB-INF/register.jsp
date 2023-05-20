<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="formBox">
		<form:form class="theForm" action="#" method="post" modelAttribute="newUser">
			<div class="form">
				<form:input class="inputFields" path="email" value="email"/>
				<form:input class="inputFields" path="username" value="username"/>
				<form:input class="inputFields" path="password" value="password"/>
				<form:input class="inputFields" path="confirm" value="confirm password"/>
				<button class="button register">Create Account</button>
			</div>
		</form:form>
	</div>
	<div class="backToLogin">
		<a href="/"><button class="button butTwo">Back</button></a>
		<a href="/login"><button class="button butTwo login">Have an account?</button></a>
	</div>
</body>
</html>