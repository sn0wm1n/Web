<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>王汉桑商城会员注册页面</title>

    <%--    静态包含base标签，css样式，jquery--%>
    <%@ include file="/pages/common/head.jsp" %>


    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>

    <script type="text/javascript">
        //页面加载后
        $(function () {
            $("#username").blur(function () {
                var username = this.value;
                $.getJSON("<%=request.getContextPath()%>" + "/userServlet", "action=ajaxExistsUsername&username=" + username,
                    function (data) {
                        if (data.existsUsername) {
                            $(".errorMsg").text("用户名不重复");
                        } else {
                            $(".errorMsg").text("用户名重复了~");
                        }
                    });
            });


            //给验证码的图片绑定单机事件
            $("#code_img").click(function () {
                // this.src="${basePath}kaptcha.jpg";
                this.src = this.src + "?d=" + new Date();
            });
            $("#sub_btn").click(function () {
                //验证用户名
                var usernameval = $("#username").val();
                var usernamepatt = /^\w{5,12}/;
                if (!usernamepatt.test(usernameval)) {
                    $(".errorMsg").text("用户名不合法！");
                    return false;
                }
                //验证密码是否合法
                var password = $("#password").val();
                if (!usernamepatt.test(password)) {
                    $(".errorMsg").text("密码不合法！");
                    return false;
                }
                //重复确认密码
                var repass = $("#repwd").val();
                if (repass != password) {
                    $(".errorMsg").text("密码不一致！");
                    return false;
                }
                //email验证
                var emailpatt = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
                var eemail = $("#email").val();
                if (!emailpatt.test(eemail)) {
                    $(".errorMsg").text("邮箱不合法！");
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册王汉桑商城会员</h1>
                    <span class="errorMsg">${empty requestScope.msg ?"请输入信息":requestScope.msg}</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <label>用户名称：</label>
                        <input type="hidden" name="action" value="regist">
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${requestScope.username}"/>

                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="${empty requestScope.email ? "hajizu@126.com":requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 80px;" name="code" id="code" value="bnbn"/>
                        <img id="code_img" alt="" src="kaptcha.jpg"
                             style=" float: left; margin-left: 40px ; margin-right: 20px ;width:100px;height: 45px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>