<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>... Bank</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <div id="nav">
                <%@include file="loginMenu.jspf"%>
            </div>
            <div id="content">
                <h3>Thank You</h3>
                <div class="flash">${flash}</div>
            <p>You have successfully transfered $<c:out value="${trans1.amount}"/> from acc #<c:out value="${facc.accNum}"/> to acc #<c:out value="${tacc.accNum}"/><br/>
            <hr/>
                Click <a href="/bank/home">here</a> to go home.
                
            </div>
        </div>
    </body>
</html>
