<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"/>
        <title>... Bank Transactions</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
        
        <div id="nav">
            
                    <%@include file = "../jspf/menuAcc.jspf"%>
                
        </div>
        <h5>${flash}</h5>
        <div id="content">
            <h3>Transactions</h3>
        <table class="main">
            <th>Date</th><th>Type</th><th>Amount</th><th>Balance</th><th>Description</th>
        <c:forEach var="trans" items="${trans}" varStatus="loopStatus">    <tr class="${loopStatus.index % 2 == 0 ? "even" : "odd"}">
                <td class="date">${trans.getTimeStamp()}</td><td>${trans.type}</td><td class="money">${trans.amount}</td><td class="money">${trans.balance}</td><td>${trans.description}</td>
            </tr>
        </c:forEach>
        </table>
            <table class="pagination">
    <tr>
    <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${curPage != 1}">
            <td><a href="/bank/transactions?accId=${accId}&page=${curPage - 1}">Prev</a></td>
        </c:if>

        <%--For displaying Page numbers. 
        The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${numOfPages}" var="i">
            <c:choose>
                <c:when test="${curPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/bank/transactions?accId=${accId}&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${curPage < numOfPages}">
            <td><a href="/bank/transactions?accId=${accId}&page=${curPage + 1}">Next</a></td>
        </c:if>
    </tr>
</table>  
        </div>
        <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>
