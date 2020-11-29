<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<div id="main">
    <h3>Your current Cv's</h3>
    <table>
        <tr>
            <th>Download</th>
        </tr>
    <c:forEach items="${cvs}" var="cv">
        <tr>
            <td>
                <a href="http://localhost:8080/pdf/get-file/${cv.id}">Cv ${cv.id}</a>
            </td>
            <td><a href="http://localhost:8080/cv/delete/${cv.id}">Delete</a></td>
        </tr>

    </c:forEach>
    </table>
    <form action="/basicdata/show" method="get">
        <div class="form-group form-button" style="float: left">
            <input type="submit" name="add" id="add" class="form-submit" value="Create new Cv" />
        </div>
    </form>
</div>
</div>
</body>
</html>

