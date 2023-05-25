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
					<div class="commentDiv">
						<div class="commentContentPart3">
						
						</div>
						<div class="commentContentPart">
							<div>
								<c:out value="${tweet.user.username}"/>
							</div>
							<div>
								<p>
									<c:out value="${tweet.content}"/>
								</p>
							</div>
						</div>
					</div>
					<hr style="width: 90%; margin: 0 auto; border-color: rgb(47,51,54)">
					<div class="commentInfo">
						<p>
							&hearts;<c:out value="${tweet.likeNum}"/>
						</p>
						<p>
							Comments: <c:out value="${tweet.commentNum}"/>
						</p>
					</div>
					<hr style="width: 90%; margin: 0 auto; border-color: rgb(47,51,54)">
					<div>
						<form:form action="/tweet/comment/${tweet.id}" method="post" modelAttribute="newComment">
							<form:input type="hidden" value="${user.id}" path="user"/>
							<form:input type="hidden" value="${tweet.id}" path="tweet"/>
							<form:textarea placeholder="Leave a comment" class="newTweet" path="content"/>
							<button class="tweetButton">Comment</button>
						</form:form>
					</div>
					<div>
						<hr style="border-color: rgb(47,51,54)">
						<c:forEach var="comment" items="${tweet.comments}">
						<div class="commentScroll">
							<div class="commentLeft">
								
							</div>
							<div class="commentSection">
								<p><c:out value="${comment.user.username}"/> | <fmt:formatDate value="${tweet.createdAt}" pattern="yy-MMM-dd"/>
								<p><c:out value="${comment.content}"/></p>
							</div>
						</div>
							<hr style="border-color: rgb(47,51,54)">
						</c:forEach>
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