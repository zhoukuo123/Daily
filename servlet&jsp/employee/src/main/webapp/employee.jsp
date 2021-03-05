<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>员工列表</title>
</head>
<body>

<table>
    <tr>
        <th>序号</th>
        <th>员工编号</th>
        <th>姓名</th>
        <th>部门</th>
        <th>职务</th>
        <th>薪资</th>
    </tr>
    <c:forEach items="${applicationScope.employees}" var="emp" varStatus="idx">
        <tr>
            <td>${idx.index + 1}</td>
            <td>${emp.empno}</td>
            <td>${emp.ename}</td>
            <td>${emp.department}</td>
            <td>${emp.job}</td>
            <td style="color: red;font-weight: bold">￥<fmt:formatNumber value="${emp.salary}" pattern="0,000.00"/></td>
        </tr>
    </c:forEach>
</table>

<form action="/employee/create" method="post">
    <div>
        <label>员工编号</label>
        <input type="text" name="empno">
    </div>
    <div>
        <label>员工姓名</label>
        <input type="text" name="ename">
    </div>
    <div>
        <label>部门</label>
        <select name="department" id="dname">
            <option selected="selected">请选择部门</option>
            <option value="市场部">市场部</option>
            <option value="研发部">研发部</option>
            <option value="后勤部">后勤部</option>
        </select>
    </div>
    <div>
        <label>职务</label>
        <input type="text" name="job">
    </div>
    <div>
        <label>工资</label>
        <input type="text" name="salary">
    </div>
    <div>
        <input type="submit" value="提交">
    </div>
</form>

</body>
</html>
