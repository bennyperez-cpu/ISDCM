<%-- 
    Document   : login
    Created on : 15-abr-2022, 16:06:13
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Control" method="post">
            Login<input type="text" name="login"><br>
            Password<input type="Password" name="password"><br>
            <input type="submit"name="b1">            
        </form>
        
        <%
            if(request.getAttribute("v")!=null){
                if(request.getAttribute("v")=="1"){
                    HttpSession ses = request.getSession();
                    ses.setAttribute("s1", request.getAttribute("log"));
                    response.sendRedirect("bienvenida.jsp");
                } else {
                    out.print("<br>Credenciales incorrectas");
                }
            }
            %>
        
    </body>
</html>
