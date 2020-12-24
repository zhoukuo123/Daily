<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${applicationScope.channelList}" var="c">
    <a href="${c.url}">${c.channelName}</a> |
</c:forEach>
<hr>
</body>
</html>
