

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>... Bank</title>
    </head>
    <body>
        <h1>... Bank</h1>
        <c:choose>
            <c:when test = "${sessionScope.cust.username!= null}">
                                    <li>
        <ul><a href="eindex?action=accounts">Accounts</a></ul>
        <ul><a href="eindex?action=transactions">Transactions</a></ul>
        <ul><a href="eindex?action=logout">Logout</a></ul>
                    </li>
            </c:when>
        </c:choose>
        <h3>Hello ${sessionScope.cust.toString()}</h3>
        <p>Your Account Number is ${sessionScope.account.getAccNum()}</p>
        <a href="eindex?action=home">Return to Home Page</a>
    </body>
</html>
