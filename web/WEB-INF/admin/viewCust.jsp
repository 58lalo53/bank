<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>View Customers</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <%@include file="../jspf/adminMenu.jspf" %>
            <div id="content">
                <h4 class="flash">${flash}</h4>
                <p>There are ${numOfCusts} customers.</p>
                <a href="/bank/viewCust?order=fname">Order By First Name</a> | <a href="/bank/viewCust?order=lname">Order By Last Name</a>
                <table class="main">
                    <tr><th>First Name</th><th>Last Name</th><th>Middle Name</th>
                        <th>Street</th><th>City</th><th>State</th><th>Zip</th><th>Email</th><th>Phone</th></tr>
                    
                    <c:forEach var="cust" items="${custs}" varStatus="loopStatus">
                        <tr class="${loopStatus.index % 2 == 0 ? "even" : "odd"}"><td>${cust.fname}</td><td>${cust.lname}</td><td>${cust.mname}</td><td class="date">${cust.street}</td><td>${cust.city}</td><td>${cust.state}</td>
                            <td>${cust.zip}</td><td>${cust.email}</td><td>${cust.phone}</td></tr>
                   </c:forEach>
                </table>
                <table class="pagination">
    <tr>
    <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${curPage != 1}">
            <td><a href="/bank/viewCust?page=${curPage - 1}">Prev</a></td>
        </c:if>

        <%--For displaying Page numbers. 
        The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${numOfPages}" var="i">
            <c:choose>
                <c:when test="${curPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/bank/viewCust?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${curPage lt numOfPages}">
            <td><a href="/bank/viewCust?page=${curPage + 1}">Next</a></td>
        </c:if>
    </tr>
</table>  
            </div>
                <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>
