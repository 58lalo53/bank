<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
            
           <%@include file="../noLoginMenu.jspf"%>
           
            <div id="content">
                <div class="flash">
                <h4>${flash}</h4>
            </div>
        <form method="POST" action="/bank/register">
            <table class="center">
                <tr>
                    <td>First Name: </td><td><input type="text" name="fname" value="<c:out value="${param.fname}"/>"/></td>
                </tr>
                <tr>
                    <td>Middle Name: </td><td><input type="text" name="mname" value="<c:out value="${param.mname}"/>"/></td>
                </tr>
                <tr>
                    <td>Last Name: </td><td><input type="text" name="lname" value="<c:out value="${param.lname}"/>"/></td>
                </tr>                
                <tr>
                    <td>Street: </td><td><input type="text" name="street" value="<c:out value="${param.street}"/>"/></td>
                </tr>                
                <tr>
                    <td>City: </td><td><input type="text" name="city" value="<c:out value="${param.city}"/>"/></td>
                </tr>                
                <tr>
                    <td>State: </td><td><select name="state">
		<option value="AL">Alabama</option>
		<option value="AK">Alaska</option>
		<option value="AZ">Arizona</option>
		<option value="AR">Arkansas</option>
		<option value="CA">California</option>
		<option value="CO">Colorado</option>
		<option value="CT">Connecticut</option>
		<option value="DE">Delaware</option>
		<option value="DC">District Of Columbia</option>
		<option value="FL">Florida</option>
		<option value="GA">Georgia</option>
		<option value="HI">Hawaii</option>
		<option value="ID">Idaho</option>
		<option value="IL">Illinois</option>
		<option value="IN">Indiana</option>
		<option value="IA">Iowa</option>
		<option value="KS">Kansas</option>
		<option value="KY">Kentucky</option>
		<option value="LA">Louisiana</option>
		<option value="ME">Maine</option>
		<option value="MD">Maryland</option>
		<option value="MA">Massachusetts</option>
		<option value="MI">Michigan</option>
		<option value="MN">Minnesota</option>
		<option value="MS">Mississippi</option>
		<option value="MO">Missouri</option>
		<option value="MT">Montana</option>
		<option value="NE">Nebraska</option>
		<option value="NV">Nevada</option>
		<option value="NH">New Hampshire</option>
		<option value="NJ">New Jersey</option>
		<option value="NM">New Mexico</option>
		<option value="NY">New York</option>
		<option value="NC">North Carolina</option>
		<option value="ND">North Dakota</option>
		<option value="OH">Ohio</option>
		<option value="OK">Oklahoma</option>
		<option value="OR">Oregon</option>
		<option value="PA">Pennsylvania</option>
		<option value="RI">Rhode Island</option>
		<option value="SC">South Carolina</option>
		<option value="SD">South Dakota</option>
		<option value="TN">Tennessee</option>
		<option value="TX">Texas</option>
		<option value="UT">Utah</option>
		<option value="VT">Vermont</option>
		<option value="VA">Virginia</option>
		<option value="WA">Washington</option>
		<option value="WV">West Virginia</option>
		<option value="WI">Wisconsin</option>
		<option value="WY">Wyoming</option>
                <option value="${param.state}" selected><c:out value="${param.state}"/></option>
		</select></td>
                </tr>
                <tr>
                    <td>Zip:  </td><td><input type="text" name="zip" value="<c:out value="${param.zip}"/>"/></td>
                </tr>
                <tr>
                    <td>Phone </td><td><input type="text" name="phone" value="<c:out value="${param.phone}"/>"/></td>
                </tr>                
                <tr>
                    <td>Email </td><td><input type="email" name="email" value="<c:out value="${param.email}"/>"/></td>
                </tr>                
                <tr>
                    <td>Username: </td><td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>Password: </td><td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td>Confirm Password: </td><td><input type="password" name="password2" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" /></td>
                </tr>
            </table>
        </form>
            </div>
                <%@include file="../footer.jspf" %>
        </div>
    </body>
</html>
