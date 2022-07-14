<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--    静态包含base标签，css样式，jquery--%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("a.deleteItem").click(function () {
                alert("好兄弟，咱们坐下来好好谈一谈，删除一件商品，你失去的不是购买他的机会，每一件商品都是一个梦想，哪怕他被时代淘汰或者是" +
                    "因为自己的原因没办法与他相遇，这虽然都不怨你，但是你要知道的是，当你与他遇见，你的人生轨迹就会出现一点点小的不同，你看动物园中的老虎" +
                    "就算他是百兽之王，但是他也只能接收 日复一日毫无变化的日子，多活一天和少活一天没有什么区别，" +
                    "remeber！money in your hand ,it`s alwways just money" +
                    "给自己一个机会去见识不同的人生？如何？---");
                return (confirm("请问亲一定必须要删除《" + $(this).parent().parent().find("td:first").text() + "》吗"))
            });
            $("a.clean").click(function () {
                alert("好兄弟，咱们坐下来好好谈一谈，兄弟咱清空购物车可不是这么个清空法，你没看见下面有一个结账按钮吗？" +
                    "每一件商品都是一个梦想，哪怕他被时代淘汰或者是" +
                    "因为自己的原因没办法与他相遇，这虽然都不怨你，但是你要知道的是，当你与他遇见，你的人生轨迹就会出现一点点小的不同，你看动物园中的老虎" +
                    "就算他是百兽之王，但是他也只能接收 日复一日毫无变化的日子，多活一天和少活一天没有什么区别，" +
                    "remeber！money in your hand ,it`s alwways just money" +
                    "给自己一个机会去见识不同的人生？如何？---");
                return (confirm("请问亲一定必须要清空购物车吗"))
            });
            $(".updateCount").change(function () {
              var name = $(this).parent().parent().find("td:first").text();
              var count = this.value;
              var bookId=$(this).attr("bookId");
              if(confirm("你确定要将"+name+"的商品数量更改为"+count+"吗？")){
                  location.href="<%=request.getContextPath()%>"
                      +"/cartServlet?action=updateCount&count="+count+"&id="+bookId;
              }else{
                  this.value=this.defaultValue;
              }

            });
        })
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--			静态包含登录成功后的菜单--%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">
    ${sessionScope.cart}
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="index.jsp" style="font-size: 33px">你搁这儿看尼玛呢，给老娘上货去！</a></td>

            </tr>


        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">

                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                        <input class="updateCount" style ="width: 10px"
                               bookId="${entry.value.id}"
                               type="text" value="${entry.value.count}">
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a class ="clean" href="cartServlet?action=clean">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>

</div>

<%--静态包含页脚内容--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>