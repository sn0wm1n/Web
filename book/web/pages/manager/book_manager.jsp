<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--    静态包含base标签，css样式，jquery--%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteClass").click(function () {
                return confirm("确认要删除" + $(this).parent().parent().find("td:first").text() + "吗？");
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--抽取包含manager管理模块--%>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>id</td>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBean&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a>
                </td>

                <td><a class="deleteClass"
                       href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
        </tr>
    </table>

    <%--静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp"%>

</div>

<%--静态包含页脚内容--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>
<%--修改功能
				首先需要跳转就让表单填充好这个一项原始的信息，这就需要获得当前列的book的id，
				1.id存到get请求参数  回传给servlet。
				2.这个servlet负责通过ID获得book对象的所有信息跳转到book_edit页面,
				3.edit页面manager修改好内容之后点击提交之后我们就通过一个接收到这些信息的servlet方法去保存好这些数据然后回传新的页面
				但是有一个问题：之前的新增页面也使用了这个book_edit页面，我们要判断是哪一个方法。有三个方法
				1.动态选择action，我们点击不同的（add或者update）按钮时分别把对应的action回传，
				在需要用到action值得时候直接通过这个变量获取就可以，因为都是请求转发所以不怕值会丢失
				--%>
<%--分页功能
				分页功能
--%>