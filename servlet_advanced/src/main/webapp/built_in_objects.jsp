<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String url = request.getRequestURL().toString(); // HttpServletRequest
    response.getWriter().println(url); // HttpServletResponse
%>
<%
    out.println("<br>啦啦");
    session.setAttribute("user", "张三");
    out.println((String) session.getAttribute("user"));
%>
<%
    String copyright = application.getInitParameter("copyright"); // ServletContext
    out.println("<hr/>");
    out.println(copyright);
    pageContext.getRequest();
    pageContext.getResponse();
    pageContext.getSession();
    pageContext.getServletContext();

%>
</body>
</html>
