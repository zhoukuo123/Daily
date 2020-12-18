<%@ page import="com.zk.el.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Student stu = (Student) request.getAttribute("student");
    String grade = (String) request.getAttribute("grade");
    out.println("<h1>姓名: " + stu.getName() + "</h1>");
    out.println("<h2>手机: " + stu.getMobile() + "</h2>");
    out.println("<h2>教师评级: " + grade + "</h2>");
%>
</body>
</html>
