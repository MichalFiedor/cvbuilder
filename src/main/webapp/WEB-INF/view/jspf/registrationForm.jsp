<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="<c:url value="/colorlib-regform-7/css/style.css" />">
</head>
<section class="signup">
    <div class="container">
        <div class="signup-content">
            <div class="signup-form">
                <h2 class="form-title">Sign up</h2>
                <form:form method="POST" class="register-form" id="register-form"
                           action="/registration" modelAttribute="user">
                    <div class="form-group">
                        <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="text" id="login" placeholder="Login" path="login"/>
                    </div>
                    <div class="form-group">
                        <label for="email"><i class="zmdi zmdi-email"></i></label>
                        <form:input type="email" id="email" placeholder="Your Email"  path="email"/>
                    </div>
                    <div class="form-group">
                        <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                        <form:input type="password" id="pass" placeholder="Password" path="password"/>
                    </div>

<%--                    <div class="form-group">--%>
<%--                        <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>--%>
<%--                        <input type="password" name="re_pass" id="re_pass" placeholder="Repeat your password"/>--%>
<%--                    </div>--%>
                    <div class="form-group">
                        <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                        <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                    </div>
                </form:form>
            </div>
            <div class="signup-image">
                <figure><img src="/colorlib-regform-7/images/signup-image.jpg" alt="sing up image"></figure>
                <a href="${pageContext.request.contextPath}/login" class="signup-image-link">I am already member</a>
            </div>
        </div>
    </div>
</section>
</html>
