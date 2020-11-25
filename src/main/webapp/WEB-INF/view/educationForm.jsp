<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<%@ include file="jspf/header.jsp" %>
<body>
<section class="signup">
    <div class="container">
        <div class="signup-content">
            <div class="signup-form">
                <h2 class="form-title">Your Education</h2>
                <c:if test="${educationList.size()>0}">
                <h4 class="form-title" style="margin-bottom: 5px">Current added</h4>
                <table>
                    <tr>
                        <th>University name</th>
                        <th>Degree</th>
                        <th>Study start</th>
                        <th>Study end</th>
                    </tr>
                    <c:forEach items="${educationList}" var="education">
                        <tr>
                            <td>${education.university.name}</td>
                            <td>${education.degree}</td>
                            <td>${education.start}</td>
                            <td>${education.end}</td>
                            <td><a href="<c:url value="/education/edit/${education.id}"/>">Edit</a></td>
                            <td><a href="<c:url value="/education/delete/${education.id}"/>">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                </c:if>
                <form:form method="POST" class="register-form" id="register-form" action="/education/university">
                <div class="form-group">
                    <p>Choose City</p>
                    <label for="city"><i class="zmdi zmdi-account material-icons-name"></i></label>
                    <select id="city" name="cityId" onchange="this.form.submit()">
                        <c:choose>
                            <c:when test="${not empty selectedCity}">
                                <option>${selectedCity.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="-" label="--Please Select--"/>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${cities}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                    </form:form>
                </div>
                <div>
                    <c:if test="${universitiesPerCity.size()>0}">
                    <p>Choose University</p>
                    <form:form method="post" class="register-form" id="register-form" action="/education/add"
                               modelAttribute="educationDetails">
                    <div>
                        <label for="university"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:select id="university" path="university">
                            <form:option value="${null}" label="--Please Select--"/>
                            <form:options itemLabel="name" itemValue="id" items="${universitiesPerCity}"/>
                        </form:select>
                        <form:errors path="university" cssClass="errors"/>
                    </div>
                        <div class="form-group">
                            <label for="degree"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <form:input type="text" id="degree" placeholder="Degree" path="degree"/>
                            <form:errors path="degree" cssClass="errors"/>
                        </div>
                    <div class="form-group">
                        <p>Start date</p>
                        <label for="startDate"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="month" id="startDate" placeholder="Since" path="start"/>
                        <form:errors path="start" cssClass="errors"/>
                    </div>
                    <div class="form-group">
                        <p>End date (If still, leave empty)</p>
                        <label for="endDate"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <form:input type="month" id="endDate" placeholder="Since" path="end"/>
                    </div>
                    <input type="hidden" name="cityId" value="${selectedCity.id}">
                </div>
                <div class="form-group form-button" style="float: left">
                    <input type="submit" name="add" id="add" class="form-submit" value="Add"/>
                </div>
                </form:form>
                </c:if>
                <c:if test="${not empty showNextButton}">
                    <form style="float: left">
                        <input type="submit" formaction="/image/show" name="add" class="form-submit" value="Next"/>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</section>
<%@ include file="jspf/footer.jsp" %>
</div>
</body>
</html>

