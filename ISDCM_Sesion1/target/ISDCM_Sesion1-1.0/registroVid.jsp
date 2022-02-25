<%-- 
    Document   : registroVid.jsp
    Created on : 22-feb-2022, 1:54:59
    Author     : Benny Hammer Pérez Vásquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Ingresa tus Videos</title>
        <link href="css/Estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div class="title_header">Añade tus Videos</div>
        <div class="container col-lg-3">
            <form>
                <div class="form-group all ">
                    <label for="title" style="float: left">Título</label>
                    <input name="title" type="text" class="form-control" id="title" placeholder="Tu Video" required>
                    <label for="author" style="float: left">Autor</label>
                    <input name="author" type="text" class="form-control" id="author" placeholder="Steven Spielberg" required>
                    <label for="date" style="float: left">Fecha de Creación</label>
                    <input name="date" type="date" class="form-control" id="start" value ="2018-07-22" min= "1900-01-01">                   
                    <label for="duration" style="float: left">Duración</label>
                    <input name="duration" type="text" class="form-control" id="duration" required title="hh:mm:ss" pattern="^(?:(?:([01]?\d|2[0-3]):)([0-5]?\d):)([0-5]?\d)$" placeholder="2:35:00">
                    <label for="description" style="float: left">Descripción</label>
                    <input name="description" type="text" class="form-control" id="description" required placeholder="Avengers Era de Ultrón, El increible Hulk... ">
                    <label for="format" style="float: left">Formato</label>
                    <input name="format" type="text" class="form-control" required id="duration" placeholder="MOV" pattern=".{0,5}" title="No más de 5 caractéres">

                </div>
                <div>
                    <button name="action" value="add-video" type="submit" class="btn btn-primary btn-lg m-2">Subir Video</button>
                </div>
                <div>
                    <label style="margin-top: 10px; font-size: 20px; background: transparent; border: 0px; text-align: center; color: red;">${empty infoLabel ? '' : infoLabel}</label>
                </div>    
            </form>
        </div>
        

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>