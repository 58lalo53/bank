<%
    session.removeAttribute("cust");
    session.invalidate();
    response.sendRedirect("/bank/");
%>
