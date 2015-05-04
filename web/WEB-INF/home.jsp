
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
        <title>... Bank</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Welcome to ... Bank</h1>
            </div>
        
        
           
                    
                <c:choose>
                    <c:when test="${sessionScope.cust.username != null}">
                        <div id="nav">
                        <%@include file ="loginMenu.jspf" %>
                        </div>
                        <div id="content">
                            <h5 class="flash">${flash}</h5>
                            <h3>Welcome ${sessionScope.cust.toString()}</h3>
                            <a href="/bank/editCust">Edit personal info.</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="nav">
                        <c:import url="noLoginMenu.jspf"/>
                        </div>
                        <div id="content">
                            <h3>Welcome to ... Bank</h3>
                            <p>To start using our amazing bank, please <a href="/bank/login">login</a> or <a href="/bank/register">register</a></p>
                        </div>
                    </c:otherwise>
                </c:choose>

    </div>
    </body>
</html>
