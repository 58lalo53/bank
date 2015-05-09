<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <%@include file="adminMenu.jspf" %>
            <div id="content">
                <c:set var="counter" value="0"/>
                <table class="main">
                    <tr><th>Type</th><th>Account Number</th><th>Balance</th><th>Owner</th><th>Date Opened</th><th>Description</th></tr>
                <c:forEach var="acc" items="${accs}" varStatus="loopStatus">
                    <tr><td>${acc.type}</td><td><a href="/bank/transactions?accId=${acc.id}">${acc.accNum}</a></td><td>${acc.balance}</td><td>${acc.custId.toString()}</td><td>${acc.timeStamp}</td><td>${acc.description}</td></tr>
                    <c:set var="counter" value="${counter+1}"/>  
                </c:forEach>
                </table>
            </div>
            <%@include file="footer.jspf" %>
        </div>
    </body>
</html>
