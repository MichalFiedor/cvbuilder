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
                <h2 class="form-title">Your Image</h2>
                <c:if test="${not empty imgName}">
                    <p>Your added image</p>
                    <a href="${imgPath}">${imgName}</a>
                </c:if>
                <form method="POST" class="register-form" id="register-form" action="/image/add"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="image"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="file" id="image" name="image" accept="image/png, image/jpeg" />
                    </div>
                    <div class="form-group form-button" style="float: left">
                    <input type="submit" name="add" id="add" class="form-submit" value="Add"/>
                    </div>
                </form>
                <div class="form-group form-button">
                    <c:if test="${imgName.length()>0}">
                        <form>
                            <button formaction="/pdf/print">Generate Cv</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
    <%@ include file="jspf/footer.jsp"%>
</div>
</body>
</html>

