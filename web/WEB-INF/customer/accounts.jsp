<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
        <title>... Bank</title>
    </head>
    <body>
        <div id="wrapper">
            <div id ="header">
                <h1>... Bank</h1>
            </div>
        
        <c:choose>
            <c:when test = "${sessionScope.cust.username!= null}">
                <%@include file="../jspf/menuAcc.jspf"%>
            </c:when>
        </c:choose>
        
            
        <div id="content">
            <h3 class="flash">${flash}</h3>
        <p>To open a new Account <a href="/bank/newAcc">click here</a>.</p>
                    <c:forEach var="acc" items="${accounts}">
                        <div class="account">
                        
                            <c:choose>
                            <c:when test= "${sessionScope.cust.username!= null}">
                                <a href="/bank/transactions?accId=${acc.id}">
                                    <span class="accNum">Account Number:&nbsp<c:out value="${acc.accNum}"></c:out></span></a><br/>
                                <span class="accType">${acc.type}</span><br/>
                                <span class="balance">Balance:&nbsp$${acc.balance}</span><br/>
                                <span class="joinDate">Opened on:&nbsp<fmt:formatDate type ="both" dateStyle="long" timeStyle="long" value="${acc.timeStamp}"/></span>
                                
                            </c:when>    
                            
                        <c:otherwise>
            
        You don't have any opened accounts. To open one <a href="/bank/newAcc">click here</a>.
                        </c:otherwise>
                    </c:choose>
            
                        </div>
                </c:forEach>
        <table class="pagination">
    <tr>
    <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${curPage != 1}">
            <td><a href="/bank/accounts?page=${curPage - 1}">Prev</a></td>
        </c:if>

        <%--For displaying Page numbers. 
        The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${numOfPages}" var="i">
            <c:choose>
                <c:when test="${curPage == i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/bank/accounts?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${curPage < numOfPages}">
            <td><a href="/bank/accounts?page=${curPage + 1}">Next</a></td>
        </c:if>
    </tr>
</table>  
        </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>
                    </body>
</html>
