<%--
  Created by IntelliJ IDEA.
  User: Ha
  Date: 2021/5/30
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临王汉桑商城</span>
    <a href="pages/order/order.jsp">我的订单</a>&nbsp;
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>