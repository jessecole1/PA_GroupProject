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
<title><c:out value="${profileUser.username}"/>'s Profile</title>
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
		<div class="homeContent homeContent2 homeProfileHead">
			<div class="profileUsersTopInfo">
				<div class="profileCenterHome1">
					<h2>Profile - <c:out value="${profileUser.username}"/></h2>
					<div>
						<p><a href="/user/follows/${profileUser.id}">Following: </a></p>
					</div>
					<div>
						<p>Bio: <c:out value="${profileUser.bio}"/></p>
						<img class="profileImg2" src="<c:url value='/img/user.svg' />" alt="Image" />
					</div>
				</div>
				<div class="followUnfollow">
					<c:choose>
						<c:when test="${profileUser.id != user.id}">
							<%-- <c:choose> --%>
								<%-- <c:when test="${isFollowing == true}"> --%>
							<%-- 		<form:form action="/user/unfollow/${profileUser.id}" method="post" modelAttribute="aUser">
										<button class="logoutButton">Unfollow</button>
									</form:form> --%>
									<form:form action="/user/follow/${profileUser.id}" method="post" modelAttribute="aUser">
										<button class="logoutButton">Follow</button>
									</form:form>						
								</c:when>
								<%-- <c:otherwise> --%>
								<%-- </c:otherwise> --%>
							<%-- </c:choose> --%>
						<%-- </c:when> --%>
					</c:choose>
				</div>
			</div>
			<div class="profileUserTweetsColumn">
				<c:forEach var="tweet" items="${profileUser.tweets}">
					<div class="centerHome3">
						<div class="centerHome3One">
							<a href="/user/profile/${tweet.user.id}"><c:out value="${tweet.user.username}"/></a>
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
							<p style="color: white"><c:out value="Likes: ${tweet.likeNum}"/>
							<c:out value="Comments: ${tweet.commentNum}"/></p>
						</div>
					</div>
				</c:forEach>
			</div>		
			<div>
				<div class="allTweets centerHome3Two">
				
				</div>
			</div>
		</div>
		<div class="homeContent homeContent3">
			<div class="rightSide">
				<a href="/logout"><button class="logoutButton">Logout</button></a>
				<c:choose>
				<c:when test="${profileUser.id == user.id}">
				<a href="/user/profile/update">Update Profile</a>
				</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>