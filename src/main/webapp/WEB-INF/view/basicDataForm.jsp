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
                <h2 class="form-title">Infill your basic data</h2>
                <form:form method="POST" class="register-form" id="register-form" action="/basicdata/add"
                    modelAttribute="cv">
                    <div class="form-group">
                        <label for="firstName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="text" id="firstName" placeholder="First Name" path="firstName"/>
                    </div>
                    <div class="form-group">
                        <label for="lastName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="text" id="lastName" placeholder="Last Name" path="lastName"/>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="tel" id="phoneNumber" placeholder="Phone Number" path="phoneNumber" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}"/>
                    </div>
                    <div class="form-group">
                        <label for="email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="email" id="email" path="email" value="${user}"/>
                    </div>
                    <div class="form-group">
                        <p>Choose City</p>
                        <label for="city"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:select id="city" path="city" >
                            <form:option value="-" label="--Please Select"/>
                            <form:options itemValue="id" itemLabel="name" items="${cities}"/>
                        </form:select>
                    </div>
                    <div class="form-group form-button">
                        <input type="submit" name="signup" id="signup" class="form-submit" value="Next"/>
                    </div>
                </form:form>


            </div>
        </div>
    </div>
</section>
    <%@ include file="jspf/footer.jsp"%>
</div>
</body>
</html>

