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
 
    </head>
    <body>
        <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
            <div class="wrapper wrapper--w680">
                <div class="card card-4">
                    <div class="card-body">
                        <h2 class="title">¡Usuario ya existe!</h2>
                        <div>
                            <p>El nombre de usuario ya existe, vuelva a intentarlo con otro nombre de usuairo...</p>
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