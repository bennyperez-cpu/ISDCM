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

        <title>Página de Encriptación y Desencriptación de XML</title>
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
            .container{
                background-color:silver;
                border-radius: 10px;
                padding:25px;
                margin-top: 20px
            }
            .container1{
                background-color:silver;
                width: 3000px;
                border-radius: 20px;
                padding:25px;
                margin-top: 20px
            }
            .container2{
                background-color:silver;
                width: 3000px;
                border-radius: 20px;
                padding:25px;
                margin-top: 20px
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
                        <a href="security.jsp">Encriptación/Desencriptación</a>
                    </li>
                </ul>
                <ul class='list-unstyled CTAs'>
                    <li>
                        <a href="servletUsuarios?accion=Salir">Salir</a>
                    </li> 
            </nav>
                
            <!-- Page Content -->
            <div id="content text-center">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <div class="container-fluid">
                        <button type="button" id="sidebarCollapse" class="btn btn-info">
                            <i class="fas fa-align-left"></i>
                            <span>User profile</span>
                        </button>
                    </div>
                </nav>
                <div name="BODY" style="width:100%;" class="col-12">
                    <div class="jumbtron-fluid">
                       <h1 display-4 title text-center style="color:blue;" >Encriptación y Desencriptación de Documentos XML</h1>
                       <hr class="my-3">
               
                    </div>
                    <div class='container1 col-lg-3 text-center'>
                        <p style="color:black;" style="font-size:20px">Por favor, indica la ruta dónde se encuentra el archivo que se encriptará</p>
                        <form action="${pageContext.request.contextPath}/servletEncriptacion" method="POST">
                            <div class = "form-group">
                                <label for="File_source" style="text-align:left">XML Original: </label> <br/>
                                <input name="File_source" type="text" id="File_source" style="width:60%; margin-left:50px;" required placeholder="/home/alumne/Documentos/Documentos/didlFilm1.xml">
                            </div>
                            <div class = "form-group">
                                <label for="File_Destin" >XML Encriptado: </label> <br/>
                                <input name="File_Destin" type="text" id="File_Destin" style="width:60%; margin-left:50px;" required placeholder="/home/alumne/Documentos/Encrypted/Encrypted.xml">
                            </div> 
                            <button name="action" value="encriptacion" type="submit" class="button">XML Encriptado</button>         
                        </form>
                    </div>
                    <hr class="my-3">
                    <div class='container2 col-lg-3 text-center'>
                        <p style="color:black;" style="font-size:30px">Indica la ruta de tu documento Encriptado</p>
                        <form action="${pageContext.request.contextPath}/servletEncriptacion" method="GET">
                            <div class = "form-group">
                                <label for="File_Destin" >XML Encriptado</label> <br/>
                                <input name="File_Destin" type="text" id="destFilename" style="width:60%; margin-left:50px;" required placeholder="/home/alumne/Documentos/Encrypted/Encrypted.xml">
                            </div> 
                            <div class = "form-group">
                                <label for="File_Destin_2" >XML DesEncriptado: </label> <br/>
                                <input name="File_Destin_2" type="text" id="destFilename" style="width:60%; margin-left:50px;" required placeholder="/home/alumne/Documentos/Desencrypted/DesEncrypted.xml">
                            </div>
                            <button name="action" value="desencriptacion" type="submit" class="button" >XML Desencriptado</button>
                        </form> 
                    </div>
                  
                </div>
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
