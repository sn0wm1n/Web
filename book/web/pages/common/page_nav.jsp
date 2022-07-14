<%--
  Created by IntelliJ IDEA.
  User: Ha
  Date: 2021/6/2
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--如果是首页则不显示首页和上一页标签--%>
    <c:if test="${requestScope.page.pageNo!=1}">
        <a href="${requestScope.page.url}">首页</a>
        <a href="${requestScope.page.url}
			&pageNo=${requestScope.page.pageNo==1?"":requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <span>&nbsp; </span>
    <%--页码输出的开始--%>
    <%--页码输出逻辑，定当前显示五个页码：--%>
    <c:choose>
        <%--总页码小于五的时候页码总数就是总页码数--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--总页码大于五 的时候，分两种情况一种是总中间游走一种是收尾的情况--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--第一种情况是pageNo居中，-2和+2在两边，--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--第二种情况是pageNo>总页码-2--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-2}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <c:otherwise >
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--页码输出的结束--%>

    <%--如果当前页是末尾页就不显示下一页和末页--%>
    <span>&nbsp; </span>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}
			&pageNo=${requestScope.page.pageNo==requestScope.page.pageTotal?"":requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，
    ${requestScope.page.pageTotalCount}条记录
    到第<input name="pn" value="${requestScope.page.pageNo}" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

<%--    <script type="text/javascript">--%>
<%--        $(function () {--%>
<%--            $("#searchPageBtn").click(function () {--%>
<%--                var pageNo = $("#pn_input").val()--%>
<%--                if (pageNo >= 1 && pageNo <=${requestScope.page.pageTotal}) {--%>
<%--                    location.href = "<%=basepath%>" + "${requestScope.page.url}&pageNo=" + pageNo;--%>
<%--                }--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>
</div>
<%--分页条的结束--%>
