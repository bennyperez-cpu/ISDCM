<%-- 
    Document   : Seguridad
    Created on : 18/04/2022
    Author     : Benny Hammer Pérez Vásquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Página de Búsqueda de Videos</title>
        <link href="css/Estilos_listadoVid.css" rel="stylesheet" type="text/css"/>
        <link href="css/style_button.css" rel="stylesheet" media="all"/>
        <script src="https://kit.fontawesome.com/4e646a13f9.js" crossorigin="anonymous"></script>

        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        
        <style>
            .fondo{
                margin: 0;
                padding: 0;     
                background-image: url("");
                background-size: auto;
                background-position: center;
                font-family: sans-serif;    
                background-color: #32baf3;
               
                
            }
            
            .container1{
                background-color:silver;
                border-radius: 10px;
                padding:25px;
                margin-top: 80px
            }
            .container2{
                background-color:silver;
                
                border-radius: 10px;
                padding:25px;
                margin-top: 80px
            }
            .container3{
                background-color:silver;
                border-radius: 10px;
                padding:25px;
                margin-top: 80px
            }

        </style>
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h5>Usuario: ${empty userName ? '' : userName}</h5>
                </div>

                <ul class="list-unstyled components">
                    
                    <li>
                        <a href="listadoVid.jsp">Buscar Videos</a>
                    </li>

                    <li>
                        <a href="registroVid.jsp">Subir Videos</a>
                    </li>
                    <li>
                        <a href="security.jsp">XML Document Encryption/Decryption</a>
                    </li>
                </ul>
                <ul class='list-unstyled CTAs'>
                    <li>
                        <a href="servletUsuarios?accion=Salir">Salir</a>
                    </li> 
            </nav>
            <!-- Page Content -->
            <div id="content">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <div class="container-fluid">
                        <button type="button" id="sidebarCollapse" class="btn btn-info">
                            <i class="fas fa-align-left"></i>
                            <span>User profile</span>
                        </button>
                    </div>
                </nav>
                <div name="BODY" class="col-12">
                    <div class="jumbtron-fluid">
                       <h1 class="display-4">Encriptación y Desencriptación de Documentos XML</h1>
                       <p class="lead">Encriptar o desencriptar documentos XML</p>
                       <hr class="my-4">
                       <p>POr favor, indica la ruta dónde se encuentra el archivo que se encriptará</p>
                    </div>
                    <form action="${pageContext.request.contextPath}/servletEncriptacion" method="POST">
                        <div>
                            <label for="srcFilename" >Documento Original (No Encriptado): </label>
                            <input name="srcFilename" type="text" id="srcFilename" style="width:60%" required placeholder="C:\Users\user1\Docs\didlFilm1.xml">
                        </div>
                        <div>
                            <label for="destFilename" >Nuevo documento XML (Encriptado): </label>
                            <input name="destFilename" type="text" id="destFilename" style="width:60%; margin-left:50px;" required placeholder="C:\Users\user1\Docs\didlFilm1Encrypted.xml">
                        </div> 
                        <button name="action" value="encrypt" type="submit" class="btn btn-primary btn-sm m-2">Create new encrypted file</button>         
                    </form>
                    <hr class="my-4">
                    <p>Indica la ruta de tu documento encriptado</p>
                    <form action="${pageContext.request.contextPath}/servletEncriptacion" method="GET">
                        <div >
                            <label for="destFilename" >Documento Encriptado</label>
                            <input name="destFilename" type="text" id="destFilename" style="width:60%; margin-left:50px;" required placeholder="C:\Users\user1\Docs\encrypted.xml">
                        </div> 
                        <button name="action" value="decrypt" type="submit" class="btn btn-primary btn-sm m-2" >Archivo desencriptado</button>
                    </form> 
                    
                    <label style="margin-top: 10px; font-size: 20px; background: transparent; border: 0px; text-align: center; color: red">${empty infoLabel ? '' : infoLabel}</label>
                </div>
        </div>     
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <!-- Popper.JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function () {
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').toggleClass('closed');
                });
                
            }); 
        </script>
    </body>
</html>
