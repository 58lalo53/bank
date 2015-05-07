<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>404 Error</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <c:when test="${sessionScope.cust.username!=null}">
                <c:choose>
                    <%@include file="loginMenu.jspf" %>
                </c:choose>
                <c:otherwise>
                    <%@include file="noLoginMenu.jspf" %>
                </c:otherwise>
            </c:when>
            <div id="content">
                <p>The server encountered a problem so it was unable to complete your request</p>
            </div>
            
        </div>
    </body>
</html>