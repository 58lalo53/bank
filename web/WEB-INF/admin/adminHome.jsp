<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>Admin Home</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <%@include file="../jspf/adminMenu.jspf" %>
            <div id="content">
                <h4 class="flash">${flash}</h4>
                <h3>Welcome <c:out value="${cust.toString()}"/></h3>
                <p>There are ${custs} customers registered and <br/>
                    ${accs} accounts(including inactive).</p>
            </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>
