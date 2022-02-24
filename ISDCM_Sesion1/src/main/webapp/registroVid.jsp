<%-- 
    Document   : registroVid.jsp
    Created on : 22-feb-2022, 1:54:59
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>


<%-- 
    Document   : login
    Created on : 21-feb-2022, 23:19:39
    Author     : Benny Hammer Pérez Vásquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>JSP Page</title>
        <link href="css/Estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div class="container col-lg-3">
            <form action = "Principal">
                <div class = "form-group text-center">
                    <div class="title_header">Sube tus Videos Ya! </div>
                    <img src="img/usuario.jpg" height="80" width="80" alt=""/>
                    <p><strong>Bienvenidos al sistema de Login</strong></p>

                </div>
                
                <div class = "form-group">
                    
                    <label>Usuario o Email</label>
                    <input class="form-control text-center" type="text" name="txtnom" placeholder="Ingrese usuario o email">
                    
                </div>
                <div class = "form-groupr">
                     <label for="passwordInput" style="float: left">Password</label>
                     <input type="password" name="password" class="form-control text-center" id="passwordInput" placeholder="************" required>
                </div>
                <div class = "form-group">
                    
                    
                </div>
                <input type="submit" name="txtcorreo" name="accion" value ="Ingresar" class="form-control text-center">
                
             
                
            </form>
                
        </div>
            
            
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


    </body>
</html>