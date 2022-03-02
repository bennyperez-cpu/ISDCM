<%-- 
    Document   : listadoVId.jsp
    Created on : 22-feb-2022, 1:55:32
    Author     : Benny Hammer Pérez Vásquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Página de Búsqueda de Videos</title>
        <link href="css/Estilos_listadoVid.css" rel="stylesheet" type="text/css"/> 

        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        <%--  <style>
                    body {
                font-family: 'Poppins', sans-serif;
                background: #fafafa;
            }
            /* De aquí para abajo son cosas de la sidebar: https://bootstrapious.com/p/bootstrap-sidebar*/
            .wrapper {
                display: flex;
                width: 100%;
                align-items: stretch;
            }
            #sidebar.closed {
                margin-left: -250px;
            }
            a[data-toggle="collapse"] {
                position: relative;
            }
            .dropdown-toggle::after {
                display: block;
                position: absolute;
                top: 50%;
                right: 20px;
                transform: translateY(-50%);
            }
            p {
                font-family: 'Poppins', sans-serif;
                font-size: 1.1em;
                font-weight: 300;
                line-height: 1.7em;
                color: #999;
            }
            a, a:hover, a:focus {
                color: inherit;
                text-decoration: none;
                transition: all 0.3s;
            }
            #sidebar {
                background: #7386D5;
                color: #fff;
                transition: all 0.3s;
                min-width: 250px;
                max-width: 250px;
                min-height: 100vh;
                /*margin-right: 5%;*/
            }
            #sidebar .sidebar-header {
                padding: 20px;
                background: #6d7fcc;
            }
            #sidebar ul.components {
                padding: 20px 0;
                border-bottom: 1px solid #47748b;
            }
            #sidebar ul p {
                color: #fff;
                padding: 10px;
            }
            #sidebar ul li a {
                padding: 10px;
                font-size: 1.1em;
                display: block;
            }
            #sidebar ul li a:hover {
                color: #7386D5;
                background: #fff;
            }
            #sidebar ul li.active > a, a[aria-expanded="true"] {
                color: #fff;
                background: #6d7fcc;
            }
            ul ul a {
                font-size: 0.9em !important;
                padding-left: 30px !important;
                background: #6d7fcc;
            }
            .list_title_header{
                text-align: center;
                align-items: center;
                font-size: 40px;
                width: 100%;
            }
        </style>  --%> 
        
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>${empty userSurnames ? '' : userSurnames} ${empty userName ? '' : userName}</h3>
                    <h5>Email: ${empty userEmail ? '' : userEmail}</h5>
                    <h5>Username: ${empty username ? '' : username}</h5>
                </div>

                <ul class="list-unstyled components">

                    <li>
                        <a href="videoSearch.jsp">Buscar Videos</a>
                    </li>
                </ul>
                <ul class='list-unstyled CTAs'>
                    <li>
                        <a href="logout.jsp">Salir</a>
                    </li> 
                </ul>
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
                <div name="BODY" style="width:100%;" class="col-12">
                    <div>
                        <div class='list_title_header'>Remote Videos</div>
                        
                        <% if (session.getAttribute("remoteVideos") == null) { %>
                            <div class='row row justify-content-md-center'>
                            <div class='col-3 border border-primary m-1'>
                                <form action="${pageContext.request.contextPath}/servletSearch" method="POST">
                                    <label for="author" >Autor</label>
                                    <input name="author" type="text" class="form-control" id="author" placeholder="John Doe" required>
                                    <button name="action" value="search-author" type="submit" class="btn btn-primary btn-sm m-2">Search</button>
                                </form>
                            </div>
                            <div class='col-3 border border-primary m-1'>
                                <form action="${pageContext.request.contextPath}/servletSearch" method="POST">
                                    <label for="title" >Título</label>
                                    <input name="title" type="text" class="form-control" id="title" placeholder="A Random Movie 2" required>
                                    <button name="action" value="search-title" type="submit" class="btn btn-primary btn-sm m-2">Search</button>
                                </form>
                            </div>
                            <div class='col-3 border border-primary m-1'>
                                <form action="${pageContext.request.contextPath}/servletSearch" method="POST">
                                    <label for="date" style="float: left">Fecha de Creación</label>
                                    <input name="date" type="date" class="form-control" id="start" value ="2018-07-22" min= "1900-01-01">                                   
                                    <button name="action" value="search-date" type="submit" class="btn btn-primary btn-sm m-2">Search</button>
                                </form>
                            </div>
                        </div>
                        <% }else{ %>
                            <form action="${pageContext.request.contextPath}/servletSearch" method="post">
                                <button name="action" value="change-search" type="submit" class="btn btn-primary btn-sm m-2">Change Video Search</button>
                            </form>
                            <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Title</th>
                                    <th scope="col">Author</th>
                                    <th scope="col">Duration</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Visualizations</th>
                                    <th scope="col">Format</th>
                                    <th scope="col">Uploaded</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="video" items="${remoteVideos}">
                                    <tr>
                                        <td>${video.getTitle()}</td> 
                                        <td>${video.getAuthor()}</td>
                                        <td>${video.getDuration()}</td> 
                                        <td>${video.getDescription()}</td>
                                        <td>${video.getVisualizations()}</td>
                                        <td>${video.getFormat()}</td>
                                        <td>${video.getCreatedAt()}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${video.getPath()!='N/A'}">
                                                    <form action="${pageContext.request.contextPath}/servletVideos" method="post">
                                                        <button name="action" value="play-video" data-toggle="tooltip" title="${video.getPath()}" type="submit" class="btn btn-primary btn-sm m-2">Play video</button>
                                                        <input type="hidden" name="videoPath" value="${video.getPath()}" />
                                                        <input type="hidden" name="videoFormat" value="${video.getFormat()}" />
                                                        <input type="hidden" name="videoTitle" value="${video.getTitle()}" />
                                                        <input type="hidden" name="location" value="remote" />
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-secondary btn-sm m-2" disabled>Path not defined</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr> 
                                </c:forEach>
                            </tbody>
                        </table>
                        <%} %>
                    </div>
                </div>
            </div>
        </div>       


        <!-- jQuery CDN - Slim version (=without AJAX) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
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


        });
    </body>
</html>