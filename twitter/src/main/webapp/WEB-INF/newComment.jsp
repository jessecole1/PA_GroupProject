<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leave a Comment</title>
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
						<c:out value="${tweet.user.username}"/>
					</div>
					<div>
						<p>
							<c:out value="${tweet.content}"/>
						</p>
					</div>
					<hr style="width: 90%; margin: 0 auto;">
					<div>
						<p>
							&hearts;<c:out value="${tweet.likeNum}"/>
						</p>
						<p>
							<c:out value="${tweet.commentNum}"/>
						</p>
					</div>
					<hr style="width: 90%; margin: 0 auto;">
					<div>
						<a href="/tweet/comment">Leave a comment</a>
					</div>
					<div>
						<c:forEach var="comment" items="${tweet.comments}">
							<c:out value="${comment.content}"/>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="homeContent homeContent3">
		
		</div>
	</div>
</body>
</html>