<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Talkative</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
	<div class="homePage">
		<div class="homeContent homeContent1">
			<div class="leftHome1">
				<a href="/home"><p class="link">Home <img class="profileImg" src="<c:url value='/img/twitter.svg' />" alt="Image" /></p></a>
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
				<h2>Home</h2>
			</div>
			<div class="centerHome2">
 				<div class="centerHome2One commentContentPart3">
					<img class="profileImg" src="<c:url value='/img/user.svg' />" alt="Image" />
				</div> 
				<div class="centerHome2Two">
					<form:form action="/tweet/new" method="POST" modelAttribute="newTweet">
						<form:input type="hidden" path="user" value="${user.id}"/>
						<form:textarea placeholder="What's Happening?" class="newTweet" path="content"/>
							<form:errors style="color: red;" path="content" class="errors"/>
						<button class="tweetButton">Talk</button>
						<div>
						</div>
					</form:form>
				</div>
			</div>		
			<div>
				<div class="allTweets centerHome3Two">
					<c:forEach var="tweet" items="${tweets}">
					<div class="centerHome3">
						<div class="centerHome3One">
							<a href="/user/profile/${tweet.user.id}"><span class="link"><c:out value="${tweet.user.username}"/></span></a>
							<p><img class="profileImg" src="<c:url value='/img/user.svg' />" alt="Image" /></p>
						</div>
						<div class="oneTweet">
							<div class="tweetUserInfo">
								<p><c:out value="${tweet.user.username}"/> | <span class="tweetDate"><fmt:formatDate value="${tweet.createdAt}" pattern="yy-MMM-dd"/></span></p>	
<%-- 							<c:choose>
							<c:when test="${tweet.user.id == user.id}">
								<form:form action="/tweet/delete/${tweet.id}" method="post" modelAttribute="newTweet">
									<button><i class="material-icons" style="color:white; border: none;">delete</i></button>
								</form:form>					
							</c:when>
							</c:choose> --%>
							</div>
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
			<div class="rightSide">
				<a href="/logout"><button class="logoutButton">Logout</button></a>
			</div>
		</div>
	</div>
</body>
</html>