<%-- 
    Document   : Logout
    Created on : 25-feb-2022, 4:21:12
    Author     : Benny Hammer Pérez Vásquez
--%>

<%
    session.invalidate();
    response.sendRedirect("login.jsp");
%>

