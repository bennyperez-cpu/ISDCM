<%-- 
    Document   : Seguridad
    Created on : 18/04/2022
    Author     : Benny Hammer Pérez Vásquez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XML Document Encryption / Decryption</title>
        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        <style>
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
        </style>
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h3>${empty userSurnames ? '' : userSurnames}, ${empty userName ? '' : userName}</h3>
                    <h5>Email: ${empty userEmail ? '' : userEmail}</h5>
                    <h5>Username: ${empty username ? '' : username}</h5>
                </div>

                <ul class="list-unstyled components">

                    <li>
                        <a href="videoSearch.jsp">Search Remote Videos</a>
                    </li>
                    <li>
                        <a href="logged.jsp">Local videos</a>
                    </li>
                    <li>
                        <a href="security.jsp">XML Document Encryption/Decryption</a>
                    </li>
                </ul>
                <ul class='list-unstyled CTAs'>
                    <li>
                        <a href="logout.jsp">Log out</a>
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
                <div name="BODY" class="col-12">
                    <div class="jumbtron-fluid">
                       <h1 class="display-4">XML Document Encryption/Decryption</h1>
                       <p class="lead">You can encrypt and decrypt your XML document from this page.</p>
                       <hr class="my-4">
                       <p>Please indicate below the fullpath where your document is located at as well as, the fullpath where the encrypted document is going to be saved.</p>
                    </div>
                    <form action="${pageContext.request.contextPath}/servletEncryption" method="POST">
                        <div>
                            <label for="srcFilename" >Original XML Document (not encrypted): </label>
                            <input name="srcFilename" type="text" id="srcFilename" style="width:60%" required placeholder="C:\Users\user1\Docs\didlFilm1.xml">
                        </div>
                        <div>
                            <label for="destFilename" >New XML Document (encrypted): </label>
                            <input name="destFilename" type="text" id="destFilename" style="width:60%; margin-left:50px;" required placeholder="C:\Users\user1\Docs\didlFilm1Encrypted.xml">
                        </div> 
                        <button name="action" value="encrypt" type="submit" class="btn btn-primary btn-sm m-2">Create new encrypted file</button>         
                    </form>
                    <hr class="my-4">
                    <p>Please indicate below the fullpath where your encrypted document is located at.</p>
                    <form action="${pageContext.request.contextPath}/servletEncryption" method="GET">
                        <div >
                            <label for="destFilename" >Encrypted XML Document: </label>
                            <input name="destFilename" type="text" id="destFilename" style="width:60%; margin-left:50px;" required placeholder="C:\Users\user1\Docs\encrypted.xml">
                        </div> 
                        <button name="action" value="decrypt" type="submit" class="btn btn-primary btn-sm m-2" >Decrypt file</button>
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
