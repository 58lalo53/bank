<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href='${pageContext.request.contextPath}/style.css'/>
        <title>...EditInfo</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>... Bank</h1>
            </div>
            
                <%@include file="../jspf/menuAcc.jspf" %>
            
            <div id="content">
                <h4 class="flash">${flash}</h4>
                   
        
                <form method="POST" action="/bank/editCust" enctype="multipart/form-data">
            <table class="center">
                <tr><td rowspan="11"><%@include file="../jspf/image.jspf"%></td></tr>
                <tr>
                <td>Customer: </td><td><input type="text" value="<c:out value="${sessionScope.cust.toString()}"/>" readonly></td></tr>
                <tr>
                    <td>Street: </td><td><input type="text" name="street" value="<c:out value="${sessionScope.cust.getStreet()}"/>"></td>
                </tr>                
                <tr>
                    <td>City: </td><td><input type="text" name="city" value="<c:out value="${sessionScope.cust.getCity()}"/>"></td>
                </tr>                
                <tr>
                    <td>State: </td><td><select name="state">
                            <option value="${sessionScope.cust.getState()}" selected><c:out value="${sessionScope.cust.getState()}"/></option>
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
		</select></td>
                </tr>
                <tr>
                    <td>Zip:  </td><td><input type="text" name="zip" value="<c:out value="${sessionScope.cust.getZip()}"/>"></td>
                </tr>
                <tr>
                    <td>Phone </td><td><input type="text" name="phone" value="<c:out value="${sessionScope.cust.getPhone()}"/>"></td>
                </tr>                
                <tr>
                    <td>Email </td><td><input type="email" name="email" value="<c:out value="${sessionScope.cust.getEmail()}"/>"></td>
                </tr>                
                <tr>
                    <td>Username: </td><td><input type="text" name="username" value="<c:out value="${sessionScope.cust.getUsername()}"/>"></td>
                </tr>
                <tr>
                    <td>Password: </td><td><input type="password" name="password" value="<c:out value="${sessionScope.cust.getPassword()}"/>"></td>
                </tr>
                <tr>
                    <td>Confirm Password: </td><td><input type="password" name="password2" value="<c:out value="${sessionScope.cust.getPassword()}"/>"></td>
                </tr>
                <tr><td>Change account pic: </td><td><input type="file" name="pic" id="pic"/></td></tr>
                                 
                <tr>
                <input type="hidden" name="fname" value="<c:out value="${sessionScope.cust.getFname()}"/>">
                <input type="hidden" name="lname" value="<c:out value="${sessionScope.cust.getLname()}"/>">
                <input type="hidden" name="mname" value="<c:out value="${sessionScope.cust.getMname()}"/>">
                <td></td><td><input type="submit" value="Make Changes"/></td>
                </tr>
                
            </table>
            
        </form>
            </div>
                <%@include file="../jspf/footer.jspf" %>
        </div>
        </body>
</html>
