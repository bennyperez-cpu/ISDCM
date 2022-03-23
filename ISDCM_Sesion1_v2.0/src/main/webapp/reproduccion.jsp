<%-- 
    Document   : reproduccion
    Created on : 22-mar-2022, 3:39:22
    Author     : alumne
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://vjs.zencdn.net/7.7.5/video-js.css" rel="stylesheet" />
        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        
        <title>VideoPlayer</title>
        
        <style>
            body{
                background-color: #262626;
                text-align: center;
            }
            .title{
                color: #fafafa
            }
            .jumbotron{
                background-color: #262626;
                align-items: center;
            }
            .back{
                font-size: 30px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="display-4 title">Playing: ${videoTitle}</h1>
                <hr class="my-4">
                <% if (session.getAttribute("videoPath") == null) { %>
                <label style="color: white;">There was an error loading the video. Please check its path and contact the moderators.</h4>
                <% }else{ %>
                <video
                    id="my-video"
                    class="video-js"
                    controls
                    preload="auto"
                    width="100%"
                    height="100%"
                    poster="MY_VIDEO_POSTER.jpg"
                    data-setup="{}"
                    >
                    <source src='${videoPath}' type='video/${videoFormat}'>
                    <p class="vjs-no-js">
                        To view this video please enable JavaScript, and consider upgrading to a
                        web browser that
                        <a href="https://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
                    </p>
                </video>
                <%} %>
                
                <p class="lead">
                    <a class="btn btn-link btn-lg back" href="logged.jsp" role="button">Back to list</a>
                </p>
            </div>
        </div>
    </body>
</html>