<%-- 
    Document   : index
    Created on : Mar 31, 2015, 3:43:44 PM
    Author     : 58lalo53
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>... Bank</title>
    </head>
    <body>
        <p>
            <li>
                <ul><a href="/bank/home">Home</a></ul>
                <ul><a href="/bank/login">Login</a></ul>
                <ul><a href="/bank/register">Register</a></ul>
            </li>
        </p>
        <h3>${flash}</h3>
        <form method="POST" action="/bank/">
                
                <table>
                    <tr>
                        <td>User Name: </td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Login"/></td>
                    </tr>
                </table>
            </form>
    </body>
</html>
