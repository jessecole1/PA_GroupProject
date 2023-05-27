<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${tweet.user.username}"/>'s Tweet</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<body>
	<div class="homePage">
		<div class="homeContent homeContent1">
			<div class="leftHome1">
				<a href="/home"><p class="link">Home</p></a>
				<a href="/user/notifications"><p class="link">Notifications</p></a>
				<a href="#"><p class="link">Profile</p></a>
			</div>
		</div>
		<div class="homeContent homeContent2">
			<div class="centerHome1">
				<h2><c:out value="${tweet.user.username}"/>'s Tweet</h2>
			</div>
			<div class="centerHome2">
 				<div class="centerHome2One">

				</div> 
				<div class="centerHome2Two">
				</div>
			</div>		
			<div>
				<div class="allTweets centerHome3Two oneTweetPage">
					<div>
						<p>Edit Your Tweet</p>
					</div>
					<div>
						<form:form action="/update/${tweet.id}/tweet" method="put" modelAttribute="newTweet">
							<form:input type="hidden" value="${user.id}" path="user"/>
							<form:input type="hidden" value="${tweet.commentNum}" path="commentNum"/>
							<form:input type="hidden" value="${tweet.likeNum}" path="likeNum"/>
							<%-- <form:input type="text" value="${tweet.content}" path="content"/> --%>
							<form:textarea value="${tweet.content}" class="newTweet" path="content"/>
							<button class="tweetButton">Update</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		<div class="homeContent homeContent3">
			<div class="rightSide">
				<a href="/logout"><button class="logoutButton">Logout</button></a>
			</div>
		</div>
	</div>
</body>
</body>
</html>