<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page isErrorPage="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>500 Error</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>500 SERVER ERROR</h1>
            </div>
            <c:choose>
                <c:when test="${sessionScope.cust.role=='customer'} && ${sessionScope.cust!=null}">
                
                    <%@include file="/WEB-INF/jspf/menuAcc.jspf" %>
            </c:when>
                <c:otherwise>
                    <%@include file="/WEB-INF/jspf/adminMenu.jspf" %>
                </c:otherwise>
            
            </c:choose>
            <div id="content">
                <p>The server encountered a problem so it was unable to complete your request</p>
            </div>
            
        </div>
    </body>
</html>