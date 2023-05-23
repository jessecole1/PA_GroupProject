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
<title>Twitter</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="homePage">
		<div class="homeContent homeContent1">
			<div class="leftHome1">
				<a href="/home"><p class="link">Home</p></a>
				<a href="#"><p class="link">Notifications</p></a>
				<a href="#"><p class="link">Profile</p></a>
			</div>
		</div>
		<div class="homeContent homeContent2">
			<div class="centerHome1">
				<h2>Home</h2>
			</div>
			<div class="centerHome2">
 				<div class="centerHome2One">

				</div> 
				<div class="centerHome2Two">
					<form:form action="/tweet/new" method="post" modelAttribute="newTweet">
						<form:input type="hidden" path="user" value="${user.id}"/>
						<form:textarea placeholder="What's Happening?" class="newTweet" path="content"/>
						<button class="tweetButton">Tweet</button>
					</form:form>
				</div>
			</div>		
			<div>
				<div class="allTweets centerHome3Two">
					<c:forEach var="tweet" items="${tweets}">
					<div class="centerHome3">
						<div class="centerHome3One">
							
						</div>
						<div class="oneTweet">
							<p><c:out value="${tweet.user.username}"/> | <span class="tweetDate"><fmt:formatDate value="${tweet.createdAt}" pattern="yy-MMM-dd"/></span></p>
							<p><a href="/tweet/${tweet.id}"><span class="link"><c:out value="${tweet.content}"/></span></a></p>
							<a href="/tweet/like/${tweet.id}"><span class="heart">&hearts;</span></a>
							<c:out value="Likes: ${tweet.likeNum}"/>
							<c:out value="Comments: ${tweet.commentNum}"/>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="homeContent homeContent3">
		
		</div>
	</div>
</body>
</html>