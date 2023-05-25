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
<title><c:out value="${user.username}"/>'s Profile</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<div class="homePage">
		<div class="homeContent homeContent1">
			<div class="leftHome1">
				<a href="/home"><p class="link">Home</p></a>
				<a href="/user/notifications"><p class="link">Notifications 
				<c:choose>
				<c:when test="${count == 0}">
					
				</c:when>
				<c:otherwise>
					<span class="notificationBox"><c:out value="${count}"/></span>
				</c:otherwise>
				</c:choose>
				</p></a>
				<a href="/user/profile/${user.id}"><p class="link">Profile</p></a>
			</div>
		</div>
		<div class="homeContent homeContent2">
			<div class="centerHome1">
			<form:form action="/profile/update" method="update" modelAttribute="user">
				<form:input type="hidden" path="id" value="${user.id}"/>
				<h2>Username: <form:input type="text" value="${user.username}" path="username"/></h2>
				<div>
					<form:input type="hidden" path="password"/>
					<p>Bio: <form:textarea value="${user.bio}" path="bio"/></p>
					<p>Email: <form:input type="text" value="${user.email}" path="email"/></p>
					<p><form:input type="text" path="confirm" placeholder="password"/>
				</div>
				<button>Update</button>
			</form:form>
			</div>
			<div class="centerHome2">
 				<div class="centerHome2One">
 				
				</div> 
				<div class="centerHome2Two">
					<c:forEach var="tweet" items="${profileUser.tweets}">
					<div class="profileUsersTweets">
						<div>
							<p style="color: white"><span class="tweetDate"><fmt:formatDate value="${tweet.createdAt}" pattern="yy-MMM-dd"/> | </span><span style="font-size: 150%;"><c:out value="${tweet.content}"/></span></p>
						</div>
						<div>
							<hr style="width: 85%; margin: 0 auto; border-bottom: 1px solid rgb(47,51,54); margin-top: 15px; margin-bottom: 15px;"/>
							<p style="display: flex; flex-direction: row; justify-content: space-evenly">
								<span class="heart">&hearts;<c:out value="${tweet.likeNum}"/></span>
								<span class="heart"><c:out value="Comments: ${tweet.commentNum}"/></span>
							</p>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>		
			<div>
				<div class="allTweets centerHome3Two">
				
				</div>
			</div>
		</div>
		<div class="homeContent homeContent3">
			<div class="rightSide">
				<a href="/logout"><button class="logoutButton">Logout</button></a>
				<a href="#">Update Profile</a>
			</div>
		</div>
	</div>
</body>
</html>