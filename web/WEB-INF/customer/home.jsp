<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <h1>...Bank</h1>
            </div>
                <c:choose>
                    <c:when test="${sessionScope.cust.username == null}">
                        <%@include file="../jspf/noLoginMenu.jspf" %>
                        <div id="content">
                            <h3>Welcome to ... Bank</h3>
                            <p>To start using our amazing bank, please <a href="/bank/login">login</a> or <a href="/bank/register">register</a></p>
                        </div>
                    </c:when>
                    <c:otherwise>
            <%@include file="../jspf/menuAcc.jspf" %>
                        <div id="content">
                            <h5 class="flash">${flash}</h5>
                            <marquee><h3>Welcome to ...Bank</h3></marquee>
                            <a href="/bank/editCust">Edit personal info.</a>
                            <table class="center">
                                <tr><td rowspan="3">
                                        <%@include file="../jspf/image.jspf" %>
                                    </td></tr>
                                <tr><td>Welcome <c:out value="${sessionScope.cust.toString()}"/></td></tr>
                                <c:set var="count" value="0"/>
                                <c:forEach var="acc" items="${accounts}">
                                    <c:set var="count" value="${count+1}"/>
                                </c:forEach>
                                <tr><td>Number of opened accounts:&nbsp;${count}</td></tr>
                                  
                </table>
                        </div>
                            </c:otherwise>
                </c:choose>
                        <%@include file = "../jspf/footer.jspf" %>
    </div>
    </body>
</html>
