<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
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
                <p>You successfully deposited <fmt:formatNumber value="${trans.amount}" type="currency"/>
                    To make another deposit, click <a href="/bank/doDeposit">here</a></p>
            </c:if>
            <c:if test="${trans.type == 'withdraw'}">
            <p>You successfully withdrawn <fmt:formatNumber value="${trans.amount}" type="currency"/><br/>
                    To make another withdraw, click <a href="/bank/doWithdraw">here</a></p>
            </c:if>
            <c:if test="${trans1.type == 'transfer'}">
                <p>You have successfully transfered <fmt:formatNumber value="${trans1.amount}" type="currency"/> from acc #<c:out value="${facc.accNum}"/> 
                    to acc #<c:out value="${tacc.accNum}"/><br/>
                    To make another transfer, click <a href="/bank/doTransfer">here</a></p>
            </c:if>
                <br/>
                <br/>
            <p>Click <a href="/bank/home">here</a> to go home.</p>
            </div>
        <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>
