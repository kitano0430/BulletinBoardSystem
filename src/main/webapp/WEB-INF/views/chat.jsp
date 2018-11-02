<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>
<h2>掲示板アプリケーション</h2>
<c:forEach var="article" items="${articleList}" >
<c:out value="${article.name}"/>
<c:out value="${article.content}"/>
</c:forEach>
</body>
</html>