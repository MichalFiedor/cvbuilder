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
                <h2 class="form-title">Your Experience</h2>
                <c:if test="${experiences.size()>0}">
                <h4 class="form-title" style="margin-bottom: 5px">Current added</h4>
                    <table>
                        <tr>
                            <th>Company name</th>
                            <th>Job name</th>
                            <th>Job start</th>
                            <th>Job end</th>
                        </tr>
                </c:if>
                <c:forEach items="${experiences}" var="experience">
                    <tr>
                        <td>${experience.companyName}</td>
                        <td>${experience.position}</td>
                        <td>${experience.start}</td>
                        <td>${experience.end}</td>
                        <td><a href="<c:url value="/experience/edit/${experience.id}"/>">Edit</a></td>
                        <td><a href="<c:url value="/experience/delete/${experience.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
                    </table>
                <form:form method="POST" class="register-form" id="register-form" action="/experience/edit"
                    modelAttribute="experience">
                    <div class="form-group">
                        <label for="companyName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="text" id="companyName" itemLabel="companyName" itemValue="companyName" path="companyName"/>
                        <form:errors path="companyName" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="position"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="text" id="position" itemLabel="position" itemValue="position" path="position"/>
                        <form:errors path="position" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <p>Start date</p>
                        <label for="startDate"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="month" id="startDate" itemLabel="start" itemValue="start" path="start"/>
                        <form:errors path="start" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <p>End date (If still, leave empty)</p>
                        <label for="endDate"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="month" id="endDate" itemLabel="end" itemValue="end" path="end"/>
                    </div>
                    <div class="form-group">
                        <label for="endDate"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:textarea rows="6" cols="40" id="description"
                                    itemLabel="description" itemValue="description" path="description"/>
                        <form:errors path="description" cssClass="error"/>
                    </div>
                    <form:hidden path="id" itemValue="id"/>
                    <div class="form-group form-button" style="float: left">
                    <input type="submit" name="add" id="add" class="form-submit" value="Edit"/>
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

