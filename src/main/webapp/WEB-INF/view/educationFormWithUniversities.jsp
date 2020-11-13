<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
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
<%@ include file="jspf/header.jsp"%>
<body>
<section class="signup">
    <div class="container">
        <div class="signup-content">
            <div class="signup-form">
                <h2 class="form-title">Your Education</h2>
                <form:form method="POST" class="register-form" id="register-form" action="/education/university"
                modelAttribute="university">
                    <div class="form-group">
                        <p>Choose City</p>
                        <label for="city"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:select id="city" path="city" itemLabel="name" itemValue="id" items="${cities}" onchange="this.form.submit()"/>
                    </div>
                <div>
                    <c:if test="${universitiesForCity.size()>0}">
                        <p>Choose University</p>
                        <label for="university"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:select id="university" path="name">
                        </form:select>
                        </div>
                        <div class="form-group form-button" style="float: left">
                            <input type="submit" name="add" id="add" class="form-submit" value="Add"/>
                        </div>
                    </c:if>
                </form:form>
            </div>
        </div>
    </div>
</section>
    <%@ include file="jspf/footer.jsp"%>
</div>
</body>
</html>

