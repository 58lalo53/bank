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
        <h1>... Bank</h1>
        <h4>${flash}
        <h3>Thank You</h3>
        <c:import url="/WEB-INF/loginMenu.jspf"/>
        <p>You have successfully transfered <c:out value="${trans1.amount}"/> from acc #<c:out value="${facc.accNum}"/> to acc #<c:out value="${tacc.accNum}"/></p>
    </body>
</html>
