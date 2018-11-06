<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>

<h2>掲示板アプリケーション</h2>

<form action="/Article/addArticle" method="POST">
投稿者名：<input type="text" name="name"><br>
投稿内容：<textarea rows="5" cols="30" name="content"></textarea><br>
<input type="submit" value="記事投稿"><br>
</form>

<c:forEach var="article" items="${articleList}" ><br><hr>
<c:out value="${article.name}"/>
<c:out value="${article.content}"/>

<form action="${pageContext.request.contextPath}/Article/deleteComment">
<input type="hidden" value="${article.id}" name="articlelId"  >
<input type="submit" value="記事削除"> 
</form>

<c:forEach var="comment" items="${article.commentList}"><br>
<c:out value="${comment.name}"/>
<c:out value="${comment.content}"/>

</c:forEach>

<form:form action="${pageContext.request.contextPath}/Article/addComment" modelAttribute="commentForm" >
名前：<form:input path="name"/><br>
コメント<form:input path="content"/>
<input type="hidden" value="${article.id}" name="articlelId">
<input type="submit" value="コメント投稿">
</form:form>

</c:forEach>

</body>
</html>