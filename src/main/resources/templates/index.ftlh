<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <@header.all/>
    <link href="css/registration_form.css" rel="stylesheet">
    <script type="text/javascript" src="js/registration_action.js"></script>
    <script type="text/javascript" src="js/registration_form.js"></script>
    <script>
        $(document).ready ( function() {
            $('#login-form-link').addClass('active');
            $("#login-form").fadeIn(100);
            $("#register-form").fadeOut(100);
            e.preventDefault();
        });
    </script>
</head>
<body>
<@navbar.navbar role = "guest"/>
<div class="col-md-8 col-md-offset-4">
    <h4>Please login or register</h4>
</div>
<@form.login_form/>
</body>
</html>