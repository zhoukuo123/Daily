<%@page contentType="text/html;charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>油画列表</title>
    <script src="../../js/jquery.min.js" type="text/javascript"></script>
    <script src="../../js/sweetalert2.all.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="../../css/list.css">
    <script type="text/javascript">
        function showPreview(previewObj) {
            var preview = $(previewObj).attr("data-preview");
            var pname = $(previewObj).attr("data-pname");
            Swal.fire({
                title: pname,
                html: "<img src='" + preview + "' style='width: 361px; height: 240px'>",
                showCloseButton: true,
                showConfirmButton: false
            })
        }


        function dealDelete(deleteObj) {
            var id = $(deleteObj).attr("data-id");
            var pname = $(deleteObj).attr("data-pname");
            var preview = $(deleteObj).attr("data-preview");

            Swal.fire({
                title: "确认要删除[" + pname + "]作品吗?",
                html: "<img src='" + preview + "' style='width: 361px; height: 240px'>",
                icon: "warning",
                showCancelButton: true,
                focusCancel: true,
            }).then((willDelete) => {
                if (willDelete.value) {
                    // 要删除作品
                    // 1. 创建XmlHttpRequest对象
                    var xmlhttp;
                    if (window.XMLHttpRequest) {
                        xmlhttp = new XMLHttpRequest();
                    } else {
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    console.log(xmlhttp);

                    // 2. 发送Ajax请求
                    xmlhttp.open("GET", "/management?method=delete&id=" + id, true);
                    xmlhttp.send();

                    // 3. 处理服务器响应
                    // 在XMLHttpRequest状态发生改变的时候触发事件
                    xmlhttp.onreadystatechange = function () {
                        // XMLHttpRequest的状态为4即代表响应被接受
                        // XMLHttpRequest.status == 200 代表服务器处理成功
                        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                            // responseText属性保存了服务器返回的响应体的文本
                            var text = xmlhttp.responseText;
                            var json = JSON.parse(text);
                            if (json.result === "ok") {
                                // 删除成功
                                Swal.fire({
                                    title: "[" + pname + "]作品已被删除",
                                    icon: "success",
                                });
                            } else {
                                Swal.fire("删除失败", "该作品不存在!", "error");
                            }
                        }
                    }
                }
            })
        }

    </script>
</head>
<body>
<div class="container">
    <fieldset id="fieldset">
        <legend>油画列表</legend>
        <div style="height: 40px">
            <a href="/management?method=show_create" class="btn-button">新增</a>
        </div>
        <!-- 油画列表 -->
        <table cellspacing="0px">
            <thead>
            <tr style="width: 150px;">
                <th style="width: 100px">分类</th>
                <th style="width: 150px;">名称</th>
                <th style="width: 100px;">价格</th>
                <th style="width: 400px">描述</th>
                <th style="width: 100px">操作</th>
            </tr>
            </thead>
            <c:forEach items="${pageModel.pageData}" var="painting">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${painting.category == 1}">现实主义</c:when>
                            <c:when test="${painting.category == 2}">抽象主义</c:when>
                        </c:choose>
                    </td>
                    <td>${painting.pname}</td>
                    <td><fmt:formatNumber pattern="￥0.00" value="${painting.price}"/></td>
                    <td>${painting.description}</td>
                    <td><a class="oplink" data-preview="${painting.preview}" data-pname="${painting.pname}"
                           href="javascript:void(0)" onclick="showPreview(this)">预览</a>

                        <a class="oplink" href="/management?method=show_update&id=${painting.id}">修改</a>

                        <a class="oplink" data-id="${painting.id}" data-pname="${painting.pname}"
                           data-preview="${painting.preview}"
                           href="javascript:void(0)" onclick="dealDelete(this)">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!-- 分页组件 -->
        <ul class="page">
            <li><a href="/management?method=list&p=1">首页</a></li>
            <li><a href="/management?method=list&p=${pageModel.hasPreviousPage ? pageModel.page - 1 : 1}">上页</a></li>

            <c:forEach begin="1" end="${pageModel.totalPages}" var="pno" step="1">
                <li ${pno == pageModel.page ? "class='active'" : ""}>
                    <a href="/management?method=list&p=${pno}">${pno}</a>
                </li>
            </c:forEach>

            <li>
                <a href="/management?method=list&p=${pageModel.hasNextPage ? pageModel.page + 1 : pageModel.totalPages}">下页</a>
            </li>
            <li><a href="/management?method=list&p=${pageModel.totalPages}">尾页</a></li>
        </ul>
    </fieldset>
</div>

</body>
</html>
