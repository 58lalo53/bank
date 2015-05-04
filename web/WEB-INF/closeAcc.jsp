<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>...Bank</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <div id="nav">
                <%@include file="/WEB-INF/loginMenu.jspf" %>
            </div>
            <div id="content">
                <h4 class="flash">${flash}</h4>
                <form method="POST" action="/bank/closeAcc" onsubmit="return confirm('Are you sure you want to close the selected accounts?')">
                    <c:forEach var="accs" items="${accounts}">
                        <input type="checkbox" name="accId" value="${accs.id}"><c:out value="${accs.type}"/>:<br/>
                        ${accs.accNum}<br/>
                        $${accs.balance}<br/>
                        ${accs.description}<br/><hr>
                    </c:forEach>
                        <span><input type="submit" value="Close Account"/></span>
                </form>
            </div>
        </div>
    </body>
</html>
