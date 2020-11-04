<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <title>Cv generator</title>
    <meta name="description" content="website description" />
    <meta name="keywords" content="website keywords, website keywords" />
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link href='<c:url value="/css/style.css" />' rel="stylesheet">
</head>

<div id="header">
    <div id="logo">
        <div id="logo_text">
            <!-- class="logo_colour", allows you to change the colour of the text -->
            <h1><a href="${pageContext.request.contextPath}">CV<span class="logo_colour">generator</span></a></h1>
            <h2>Create your own CV</h2>
        </div>
    </div>
    <div id="menubar">
        <ul id="menu">
            <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
            <li class="selected"><a href="index.html">Home</a></li>
            <li><a href="examples.html">Examples</a></li>
            <li><a href="page.html">A Page</a></li>
            <li><a href="another_page.html">Another Page</a></li>
            <li><a href="contact.html">Contact Us</a></li>
        </ul>
    </div>
</div>
