<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="/colorlib-regform-7/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="<c:url value="/colorlib-regform-7/css/style.css" />">
</head>

<section class="sign-in">
    <div class="container">
        <div class="signin-content">
            <div class="signin-image">
                <figure><img src="colorlib-regform-7/images/signin-image.jpg" alt="sing up image"></figure>
                <a href="${pageContext.request.contextPath}/registration" class="signup-image-link">Create an account</a>
            </div>

            <div class="signin-form">
                <h2 class="form-title">Sign up</h2>
                <form method="POST" class="register-form" id="login-form" action="/log">
                    <div class="form-group">
                        <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="login" id="login" placeholder="Login"/>
                    </div>
                    <div class="form-group">
                        <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                        <input type="password" name="password" id="your_pass" placeholder="Password"/>
                    </div>
<%--                    <div class="form-group">--%>
<%--                        <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />--%>
<%--                        <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>--%>
<%--                    </div>--%>
                    <div class="form-group form-button">
                        <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</html>
