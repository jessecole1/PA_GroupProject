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
<title>Your Notifications</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="homePage">
		<div class="homeContent homeContent1">
			<div class="leftHome1">
				<a href="/home/from/notification"><p class="link">Home</p></a>
				<a href="#"><p class="link">Notifications</p></a>
				<a href="#"><p class="link">Profile</p></a>
			</div>
		</div>
		<div class="notiContent">
			<div class="centerHome1">
				<h2 style="padding-bottom: 60px">Notifications</h2>
			</div>
			<div class="notiCenter">
				<div class="notiCenterRight">
					<div>
						<c:forEach var="tweet" items="${userTweets}">
							<c:choose>
								<c:when test="${tweet.user.id == user.id}">
									<%-- <p style="color: white"><c:out value="${tweet.comments}"/></p>	 --%>
									<c:forEach var="comment" items="${tweet.comments}">
									<div class="notiCenter">
										<c:choose>
											<c:when test="${comment.notified == false}">
												<div class="notiCenterLeft">
													<p style="color: white">&#128308;</p>
												</div>											
											</c:when>
											<c:when test="${comment.notified == true}">
												<div class="notiCenterLeft">
													<p style="color: white"></p>
												</div>
											</c:when>
										</c:choose>
										<div class="notificationList notiCenterRight">
											<p class="notificationComments"><span style="text-decoration: underline"><c:out value="${comment.user.username}"/></span> commented: <c:out value="${comment.content}"/> on <fmt:formatDate value="${tweet.createdAt}" pattern="yy-MMM-dd"/></p>		
											<br>				
											<p style="font-size: 30px;" class="notificationComments">Your Tweet: <c:out value="${tweet.content}"/>
										</div>
									</div>
									</c:forEach>				
								</c:when>
							</c:choose>
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
</html>