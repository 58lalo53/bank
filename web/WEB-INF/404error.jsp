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
            <c:choose>
            <c:when test="${sessionScope.cust.username!=null}">
                
                    <%@include file="/WEB-INF/jspf/errorMenu.jspf" %>
            </c:when>
                <c:otherwise>
                    <%@include file="/WEB-INF/jspf/noLoginMenu.jspf" %>
                </c:otherwise>
            </c:choose>
            <div id="content">
                <p>Oops! The resource you were looking for was not found</p>
            </div>
            
        </div>
    </body>
</html>
