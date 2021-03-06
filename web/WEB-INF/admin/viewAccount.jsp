<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>View Accounts</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <%@include file="../jspf/adminMenu.jspf" %>
            <div id="content">
                <c:if test="${fn:length(accs) eq 0}">
                    <p>No accounts available</p>
                    </c:if>
                    <p>There are ${numOfAccs} accounts</p>
                <table class="main">
                    <tr><th>Type</th><th>Status</th><th>Account Number</th><th>Balance</th><th>Owner</th><th>Date Opened</th><th>Description</th></tr>
                <c:forEach var="acc" items="${accs}" varStatus="loopStatus">
                        <tr class="${loopStatus.index % 2 == 0 ? "even" : "odd"}"><td>${acc.type}</td><td>${acc.status}</td><td><a href="/bank/transactions?accId=${acc.id}">${acc.accNum}</a></td>
                        <td>${acc.balance}</td><td>${acc.custId.toString()}</td><td>${acc.timeStamp}</td><td>${acc.description}</td></tr>
                    
                </c:forEach>
                </table>
                     <table class="pagination">
    <tr>
    <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${curPage != 1}">
            <td><a href="/bank/viewAccounts?page=${curPage - 1}">Prev</a></td>
        </c:if>

        <%--For displaying Page numbers. 
        The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${numOfPages}" var="i">
            <c:choose>
                <c:when test="${curPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/bank/viewAccounts?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${curPage lt numOfPages}">
            <td><a href="/bank/viewAccounts?page=${curPage + 1}">Next</a></td>
        </c:if>
    </tr>
</table>
            </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>
