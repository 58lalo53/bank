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
            <%@include file="adminMenu.jspf" %>
            <div id="content">
                <h4 class="flash">${flash}</h4>
                
                <a href="/bank/viewCust?order=fname">Order By First Name</a> | <a href="/bank/viewCust?order=lname">Order By Last Name</a>
                <table class="main">
                    <tr><th>Role</th><th>First Name</th><th>Last Name</th><th>Middle Name</th>
                        <th>Street</th><th>City</th><th>State</th><th>Zip</th><th>Email</th><th>Phone</th></tr>
                    
                    <c:forEach var="cust" items="${custs}" varStatus="loopStatus">
                        <tr class="${loopStatus.index % 2 == 0 ? "even" : "odd"}"><td>${cust.role}</td><td>${cust.fname}</td><td>${cust.lname}</td><td>${cust.mname}</td><td class="date">${cust.street}</td><td>${cust.city}</td><td>${cust.state}</td>
                            <td>${cust.zip}</td><td>${cust.email}</td><td>${cust.phone}</td></tr>
                   </c:forEach>
                </table>
                
                <c:if test="${curPage !=1}">
                    <td><a href="/bank/viewCust?page=${curPage - 1}">Previous</a></td>
                    
                                    <%--Display page numbs --%>
                    <table>
                        <tr>
                            <c:forEach begin="1" end="${numOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${curPage eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="/bank/viewCust?page=${curPage + 1}">${i}</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>
                </c:if>
                                    <%--Display next link --%>
                                    <c:if test="${curPage lt numOfPages}">
                                        <td><a href="/bank/viewCust?page=${curPage + 1}">Next</a></td>
                                    </c:if>
                There are ${numOfCusts} customers.
            </div>
                <%@include file="../footer.jspf" %>
        </div>
    </body>
</html>
