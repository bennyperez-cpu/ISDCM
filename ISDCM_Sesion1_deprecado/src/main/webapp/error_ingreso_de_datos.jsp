<%-- 
    Document   : Error Ingreso de Datos
    Created on : 25-feb-2022, 4:20:52
    Author     : Benny Hammer Pérez Vásquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error en el ingreso de datos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link href="./css/main.css" rel="stylesheet">
    
        <link rel="icon" type="image/png" href="./images/favicon.png">
        
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    </head>
    <body>
        <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
            <div class="wrapper wrapper--w680">
                <div class="card card-4">
                    <div class="card-body">
                        <h2 class="title">Ingreso de Datos Incorrectos</h2>
                        <div>
                            <p>Los datos de video que ha ingresado, son incorrectos o ya existen... Por favor... vuelva a intentarlo...</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="p-t-15">
            <button class="btn btn--radius-2 btn--blue m-b-15" onclick="location.href = './registroUsu.jsp'">Registro de Usuario</button>
        </div>
    </body>
</html>