<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<h1><%= "Hello World!" %>
</h1>

<br/>

<a href="hello-servlet">Hello Servlet</a>

<fmt:formatNumber value="123456789.54321" pattern="0,000.00"/>

</body>
</html>