<%-- 
    Document   : registroUsu.jsp
    Created on : 22-feb-2022, 1:54:36
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
        <title>Login Page</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta charset="UTF-8"> 
        <meta name="description" content="Questionari per l'espectacle"> 
        
 
        <!-- Define own styles -->
        <link href="css/Estilos.css" rel="stylesheet" type="text/css"/>
    </head>

    <body> 
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-auto px-0 h-100">
                    <!-- LEFT PART WITH TTILE -->
                    <div class="jumbotron jumbotron-title fill-height"> 
                        <div class="container">
                            <div class="title_header">Sube tus Videos Ya! </div>
                            <hr class="my-4"> <!-- Line separating text from answers -->

                            <div class="project-made-text">Proyecto hecho por:</div>
                            <div class="project-names-text">Victor Carhuaricra</div>
                            <div class="project-names-text">Benny Pérez</div>
                            <div class="project-end-text">Laboratorio ISDCM Feb 2022</div>
                        </div>
                    </div>            
                </div>
                
                <div class="col pt-3 login-column">
                    <!-- RIGHT PART WITH FORM -->
                    <div class='container ml-2'>
                        <div class="login-header">Log In</div>
                        <form  action="${pageContext.request.contextPath}/servletUsers" method="post">
                            <div class="form-group">
                                <label for="usernameInput" style="float: left">Username or e-mail</label>
                                <input type="text" name="username_or_email" class="form-control" id="usernameInput" placeholder="myusername or name@example.com" required>
                            </div>
                            <div class="form-group">
                                <label for="passwordInput" style="float: left">Password</label>
                                <input type="password" name="password" class="form-control" id="passwordInput" placeholder="************" required>
                            </div>
                            <button name="action" value="login" type="submit" class="btn btn-primary mb-2">Log In!</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/servletUsers" method="get">
                            <button type="submit" class="btn btn-link mb-2">Not registrated yet?</button>
                        </form>
                         <label style="margin-top: 10px; font-size: 20px; background: transparent; border: 0px; text-align: center; color: ${empty labelColor ? "red" : labelColor};">${empty infoLabel ? '' : infoLabel}</label>
                    </div>
                </div>
            </div>         
        </div> 
                    
        <!-- For Scripting -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>