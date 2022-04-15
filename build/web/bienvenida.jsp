<%-- 
    Document   : bienvenida
    Created on : 15-abr-2022, 16:06:24
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session ="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            HttpSession ses = request.getSession();
            if (ses.getAttribute("s1")!=null){
                out.print("Bienvenid@" + ses.getAttribute("s1"));
            } else {
                response.sendRedirect("login.jsp");
            }
            out.print("<br><a href=bienvenida.jsp?c=1>Cerrar</a>");
            if (request.getParameter("c")!=null) {
                ses.invalidate();
                response.sendRedirect("login.jsp");
            }
        %>
    </body>
</html>
