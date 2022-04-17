<%-- 
    Document   : registroUsu.jsp
    Created on : 22-feb-2022, 1:54:36
    Author     : alumne
--%>
<%-- 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    </body>
</html>
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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> 
        <meta charset="UTF-8"> 
        <meta name="description" content="Questionari per l'espectacle"> 
        <style>
            body{
                /*background: url('https://www.conclusion.com.ar/wp-content/uploads/2015/10/espectaculos.jpg') no-repeat center center fixed;*/
                background-size: cover; /*for IE9+, Safari 4.1+, Chrome 3.0+, Firefox 3.6+ */
                -webkit-background-size: cover; /* for Safari 3.0 - 4.0 , Chrome 1.0 - 3.0 */
                -moz-background-size: cover; /* optional for Firefox 3.6 */
                -o-background-size: cover; /* for Opera 9.5 */
                margin: 0; /* to remove the default white margin of body */
                padding: 0; /* to remove the default white margin of body */
                overflow: hidden;
                background: linear-gradient(to bottom right, rgba(163,194,194,0.5),rgba(0,0,0,0) ) !important;
                padding-bottom: 99999px;
                min-height: 100%;
            }
            .title_header{
                font-size: 40px;
                text-align: center;
            }
            .all{
                margin-left: 20%;
                margin-right: 20%;
            }
            .fill-height{
                padding-bottom: 99999px;
                min-height: 100%;
            }
            .container-fluid{
                text-align: center
            }
        </style>
    </head>
    <body>
        <div class="title_header">Register a new user</div>
        <div class="container-fluid">
            <form  action="${pageContext.request.contextPath}/servletUsers" method="post">
                <div class="form-group all ">
                    <label for="nameRegisterInput" style="float: left">Input your name</label>
                    <input name="name" type="text" class="form-control" id="nameRegisterInput" placeholder="name" required>
                    <label for="surnamesRegisterInput" style="float: left">Input your surnames</label>
                    <input name="surname" type="text" class="form-control" id="surnamesRegisterInput" placeholder="surnames" required>
                    <label for="emailRegisterInput" style="float: left">Enter your e-mail</label>
                    <input name="email" type="email" class="form-control" id="emailRegisterInput" placeholder="name@example.com" required>
                </div>
                <div class="form-group all">
                    <label for="usernameRegisterInput" style="float: left">Please input a username</label>
                    <input name="username" type="text" class="form-control" id="usernameRegisterInput" placeholder="myCreativeUsername" required>
                    <label for="passwordInput" style="float: left">Password</label>
                    <input name="password" type="password" class="form-control" id="passwordRegisterInput" placeholder="************" required oninput="check()">
                    <label for="passwordRepeatRegisterInput" style="float: left">Repeat Password</label>
                    <input name="password2" type="password" class="form-control" id="passwordRepeatRegisterInput" placeholder="************"  required oninput="check()">
                </div>
                <div>
                    <button name="action" value="register" type="submit" class="btn btn-primary btn-lg">Register</button>
                </div>
                <div class="form-group all">
                    <label style="margin-top: 10px; font-size: 20px; background: transparent; border: 0px; text-align: center; color: red;">${empty infoLabel ? '' : infoLabel}</label>
                </div>
            </form>
        </div>
    </body>
    <script language='javascript' type='text/javascript'>
    function check() {
        var pass1 = document.getElementsByName('password')[0];
        var pass2 = document.getElementsByName('password2')[0];
        if (pass1.value !== pass2.value) {
            pass1.style.borderColor = "red";
            pass2.style.borderColor = "red";
            pass1.style.borderWidth = "3px";
            pass2.style.borderWidth = "3px";
            pass1.setCustomValidity('Passwords must match.');
            pass2.setCustomValidity('Passwords must match.');
        } else {
            // input is valid -- reset the error message
            pass1.setCustomValidity('');
            pass2.setCustomValidity('');
            pass1.style.borderColor = "transparent";
            pass2.style.borderColor = "transparent";
        }
    }
</script>
</html>