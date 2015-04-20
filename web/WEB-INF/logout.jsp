<%
    session.removeAttribute("cust");
    session.invalidate();
    response.sendRedirect("eindex");
%>
