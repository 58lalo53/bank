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
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            <div id="nav">
                <c:choose>
                    <c:when test="${sessionScope.cust.username!=null}">
                        <%@include file="../jspf/menuAcc.jspf" %>
                    </c:when>
                </c:choose>
            </div>
            <div id="content">
        <h3>${flash}</h3>
            <c:if test="${trans.type == 'deposit'}">
                You successfully deposited $<c:out value="${trans.getAmount()}"/><br/>
                To make another deposit, click <a href="/bank/doDeposit">here</a>
            </c:if>
            <c:if test="${trans.type == 'withdraw'}">
                You successfully withdrawn $<c:out value="${trans.getAmount()}"/><br/>
                To make another withdraw, click <a href="/bank/doWithdraw">here</a>
            </c:if>
            <c:if test="${trans.type == 'transfer'}">
                <p>You have successfully transfered $<c:out value="${trans1.amount}"/> from acc #<c:out value="${facc.accNum}"/> to acc #<c:out value="${tacc.accNum}"/><br/>
                <hr/>
                Click <a href="/bank/home">here</a> to go home or <a href="/bank/doTransfer">here to make another transfer.
            </c:if>

            </div>
        <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>
