<%-- 
    Document   : registroUsu.jsp
    Created on : 22-feb-2022, 1:54:36
    Author     : Benny Hammer Pérez Vásquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Ingresa tus Datos</title>
        <link href="css/Estilos_registroUsu_Vid.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div class="container col-lg-3">
            
            <form action= "servletUsuarios">
                <div class="form-group all ">
                    <label for="nameRegisterInput" style="float: left">Ingresa tu Nombre</label>
                    <input name="name" type="text" class="form-control" id="nameRegisterInput" placeholder="nombre" required>
                    <label for="surnamesRegisterInput" style="float: left">Ingresa tus Apellidos</label>
                    <input name="surname" type="text" class="form-control" id="surnamesRegisterInput" placeholder="apellidos" required>
                    <label for="emailRegisterInput" style="float: left">Ingresa tu Correo Electrónicol</label>
                    <input name="email" type="email" class="form-control" id="emailRegisterInput" placeholder="nombre@example.com" required>
                </div>
                <div class="form-group all">
                    <label for="usernameRegisterInput" style="float: left">Ingresa tu Usuario</label>
                    <input name="username" type="text" class="form-control" id="usernameRegisterInput" placeholder="usuario" required>
                    <label for="passwordInput" style="float: left">Contraseña</label>
                    <input name="password" type="password" class="form-control" id="passwordRegisterInput" placeholder="************" required oninput="check()">
                    <label for="passwordRepeatRegisterInput" style="float: left">Repite la Contraseña</label>
                    <input name="password2" type="password" class="form-control" id="passwordRepeatRegisterInput" placeholder="************"  required oninput="check()">
                </div>
                <div class="form-group text-center">
                    <button name="action" value="register" type="submit" class="btn btn-primary btn-lg">Register</button>
                </div>
                <div class="form-group all">
                    <label style="margin-top: 10px; font-size: 20px; background: transparent; border: 0px; text-align: center; color: red;">${empty infoLabel ? '' : infoLabel}</label>
                </div>
            </form>
 
                 
        </div>
        

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>